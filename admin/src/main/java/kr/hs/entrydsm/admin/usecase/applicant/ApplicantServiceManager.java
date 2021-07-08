package kr.hs.entrydsm.admin.usecase.applicant;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.hs.entrydsm.admin.integrate.applicaton.ApplicationRepository;
import kr.hs.entrydsm.admin.usecase.dto.applicant.*;
import kr.hs.entrydsm.admin.integrate.user.UserRepository;
import kr.hs.entrydsm.admin.usecase.dto.tmap.RouteGuidanceRequest;
import kr.hs.entrydsm.admin.usecase.dto.tmap.Coordinate;
import kr.hs.entrydsm.admin.usecase.dto.tmap.RouteBody;
import kr.hs.entrydsm.admin.usecase.dto.tmap.RouteGuidanceResponse;
import kr.hs.entrydsm.common.context.sender.ContentSender;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@RequiredArgsConstructor
@Service
public class ApplicantServiceManager implements ApplicantService {

    private final UserRepository userRepository;
    private final ApplicationRepository applicationRepository;

    private final ContentSender contentSender;

    private static final double SCHOOL_LATITUDE = 36.391636;
    private static final double SCHOOL_LONGITUDE = 127.363585;

    private static final String ROUTE_URL = "https://apis.openapi.sk.com/tmap/routes?version=1";
    private static final String GEO_BASIC_URL="https://apis.openapi.sk.com/tmap/geo/postcode?version=1&appKey=";

    @Value("${tmap.app.key}")
    private String appKey;

    @Override
    public void changePrintArrivedOrNot(long receiptCode, boolean isPrintedArrived) {
        userRepository.changeIsPrintedArrived(receiptCode, isPrintedArrived);

        UserNameAndEmail applicant = userRepository.getUserNameAndEmail(receiptCode);
        String template;
        if(isPrintedArrived) template = "PRINTED_ARRIVED";
        else template = "PRINTED_NOT_ARRIVED";
        Map<String, String> params = new HashMap<>();
        params.put("name", applicant.getName());

        contentSender.sendMessage(applicant.getEmail(), template, params);
    }

    @Override
    public void changeIsSubmitFalse(long receiptCode) {
        userRepository.changeIsSubmitFalse(receiptCode);

        UserNameAndEmail applicant = userRepository.getUserNameAndEmail(receiptCode);
        String template = "SUBMIT_FALSE";
        Map<String, String> params = new HashMap<>();
        params.put("name", applicant.getName());

        contentSender.sendMessage(applicant.getEmail(), template, params);
    }

    @Override
    public ApplicantsResponse getApplicants(Pageable page, Long receiptCode,
                                            boolean isDaejeon, boolean isNationwide,
                                            String telephoneNumber, String name,
                                            boolean isCommon, boolean isMeister, boolean isSocial,
                                            Boolean isPrintedArrived) {
        if(!isDaejeon && !isNationwide) {
            isDaejeon = true;
            isNationwide = true;
        }
        if(!isCommon && !isMeister && !isSocial) {
            isCommon = true;
            isMeister = true;
            isSocial = true;
        }

        Page<ApplicantsInformationResponse> applicants = userRepository.findAll(page, receiptCode, isDaejeon, isNationwide, telephoneNumber, name, isCommon, isMeister, isSocial, isPrintedArrived);
        List<ApplicantsInformationResponse> applicantsInformationResponses= new ArrayList<>();
        
        for (ApplicantsInformationResponse applicant : applicants) {
            applicantsInformationResponses.add(
                    ApplicantsInformationResponse.builder()
                            .receiptCode(applicant.getReceiptCode())
                            .name(applicant.getName())
                            .isDaejeon(applicant.getIsDaejeon())
                            .applicationType(applicant.getApplicationType())
                            .isPrintedArrived(applicant.getIsPrintedArrived())
                            .isSubmit(applicant.getIsSubmit())
                            .build()
            );
        }

        return  ApplicantsResponse.builder()
                .totalElements(applicants.getTotalElements())
                .totalPages(applicants.getTotalPages())
                .applicantsInformationResponses(applicantsInformationResponses)
                .build();
    }

    @Override
    public ResponseEntity getDetailApplicantInfo(int receiptCode) {
        UserInfo userInfo = userRepository.getUserInfo(receiptCode);
        ApplicantInfo applicantInfo = applicationRepository.getApplicantInfo(receiptCode);

        if(!userInfo.getIsSubmit()) {
            NotSubmitApplicant notSubmitApplicant
                    = new NotSubmitApplicant(userInfo.getTelephoneNumber(), userInfo.getParentTel(), userInfo.getHomeTel(), applicantInfo.getSchoolTel());
            return new ResponseEntity<>(notSubmitApplicant, HttpStatus.LOCKED);
        }

        Status status = Status.builder()
                .isPrintedArrived(userInfo.getIsPrintedArrived())
                .isSubmit(userInfo.getIsSubmit())
                .build();

        PersonalData personalData = PersonalData.builder()
                .photoFileName(userInfo.getPhotoFileName())
                .name(userInfo.getName())
                .birthDate(userInfo.getBirthDate())
                .schoolName(applicantInfo.getSchoolName())
                .email(userInfo.getEmail())
                .isGraduated(applicantInfo.getIsGraduated())
                .educationalStatus(userInfo.getEducationalStatus())
                .applicationType(userInfo.getApplicationType())
                .address(userInfo.getAddress())
                .detailAddress(userInfo.getDetailAddress())
                .telephoneNumber(userInfo.getTelephoneNumber())
                .parentTel(userInfo.getParentTel())
                .schoolTel(applicantInfo.getSchoolTel())
                .homeTel(userInfo.getHomeTel())
                .build();

        Evaluation evaluation = Evaluation.builder()
                .volunteerTime(applicantInfo.getVolunteerTime())
                .conversionScore(applicantInfo.getAverageScore())
                .dayAbsenceCount(applicantInfo.getDayAbsenceCount())
                .lectureAbsenceCount(applicantInfo.getLectureAbsenceCount())
                .earlyLeaveCount(applicantInfo.getEarlyLeaveCount())
                .latenessCount(applicantInfo.getLatenessCount())
                .averageScore(applicantInfo.getAverageScore())
                .selfIntroduce(userInfo.getSelfIntroduce())
                .studyPlan(userInfo.getStudyPlan())
                .build();

        ApplicantDetailResponse applicantDetailResponse = ApplicantDetailResponse.builder()
                .status(status)
                .personalData(personalData)
                .evaluation(evaluation)
                .build();
        return new ResponseEntity<>(applicantDetailResponse, HttpStatus.OK);
    }

    @Override
    public void saveAllApplicantsExamCode() throws Exception {
        List<SaveExamCodeUserResponse> applicants = userRepository.findAllIsSubmitTrue();
        List<SaveExamCodeUserResponse> applicantSort = new ArrayList<>(applicants);
        int commonDaejeon = 1, commonNationwide = 1, meisterDaejeon = 1,
                meisterNationwide = 1, socialDaejeon = 1, socialNationwide = 1;

        for (SaveExamCodeUserResponse applicant : applicants) {
            StringBuilder examCode = new StringBuilder();
            switch (applicant.getApplicationType()) {
                case "COMMON":
                    examCode.append(1);
                    break;
                case "MEISTER":
                    examCode.append(2);
                    break;
                default:
                    examCode.append(3);
            }
            if (applicant.isDaejeon()) examCode.append(1);
            else examCode.append(2);

            applicant.updateExamCode(examCode.toString());
        }

        for (SaveExamCodeUserResponse applicant : applicants) {
            Coordinate coordinate = getCoordinate(applicant.getAddress());
            RouteGuidanceRequest request = RouteGuidanceRequest.builder()
                    .lng(SCHOOL_LONGITUDE)
                    .lat(SCHOOL_LATITUDE)
                    .startX(Double.parseDouble(coordinate.getLon()))
                    .startY(Double.parseDouble(coordinate.getLat()))
                    .build();
            RouteGuidanceResponse distance = routeGuidance(request);
            applicant.updateDistance(distance.getProperties().getTotalDistance());
        }

        applicantSort.sort((o1, o2) -> Double.compare(o2.getDistance(), o1.getDistance()));

        for(SaveExamCodeUserResponse applicant : applicantSort) {
            int examOrder = 0;
            String examCode = applicant.getExamCode();
            if(examCode.startsWith("11")) { 
                examOrder = commonDaejeon++;
            } else if(examCode.startsWith("12")) {
                examOrder = commonNationwide++;
            } else if(examCode.startsWith("21")) {
                examOrder = meisterDaejeon++;
            } else if(examCode.startsWith("22")) {
                examOrder = meisterNationwide++;
            } else if(examCode.startsWith("31")) {
                examOrder = socialDaejeon++;
            } else if(examCode.startsWith("32")) {
                examOrder = socialNationwide++;
            }

            applicant.updateExamCode(applicant.getExamCode() + String.format("%03d", examOrder));
            userRepository.changeExamCode(applicant.getReceiptCode(), applicant.getExamCode());
        }
    }

    private Coordinate getCoordinate(String address) throws URISyntaxException, JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.add("Accept-Language", "ko");
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE
                + ";charset=UTF-8");

        URI url = new URI(GEO_BASIC_URL + appKey + "&addr=" + URLEncoder.encode(address, StandardCharsets.UTF_8) + "&addressFlag=F00&format=json");

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Map> response = restTemplate.getForObject(url, Map.class);

        Map<String, Map> coordinate =
                mapper.readValue(mapper.writeValueAsString(response.get("coordinateInfo")), Map.class);
        List<Coordinate> info = Arrays.asList(mapper.readValue(mapper.writeValueAsString(coordinate.get("coordinate")), Coordinate[].class));

        return info.get(0);
    }

    private RouteGuidanceResponse routeGuidance(RouteGuidanceRequest request) throws URISyntaxException, JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        RouteBody routeBody = RouteBody.builder()
                .endX(request.getLng())
                .endY(request.getLat())
                .startX(request.getStartX())
                .startY(request.getStartY())
                .totalValue(2)
                .build();

        headers.add("Accept-Language", "ko");
        headers.add("appKey",appKey);
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);

        URI url = new URI(ROUTE_URL);

        ObjectMapper mapper = new ObjectMapper();
        HttpEntity<RouteBody> rq = new HttpEntity<>(routeBody, headers);
        Map<String, Map> response = restTemplate.postForObject(url, rq, Map.class);

        List<RouteGuidanceResponse> map =
                Arrays.asList(mapper.readValue(mapper.writeValueAsString(response.get("features")), RouteGuidanceResponse[].class));
        return map.get(0);
    }
    
}