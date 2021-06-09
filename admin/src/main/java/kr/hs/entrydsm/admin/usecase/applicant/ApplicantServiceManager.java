package kr.hs.entrydsm.admin.usecase.applicant;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.hs.entrydsm.admin.entity.admin.Admin;
import kr.hs.entrydsm.admin.entity.admin.Permission;
import kr.hs.entrydsm.admin.usecase.dto.Applicant;
import kr.hs.entrydsm.admin.entity.admin.AdminRepository;
import kr.hs.entrydsm.admin.integrate.user.ApplicantRepository;
import kr.hs.entrydsm.admin.usecase.dto.*;
import kr.hs.entrydsm.admin.usecase.dto.request.RouteGuidanceRequest;
import kr.hs.entrydsm.admin.usecase.dto.response.*;
import kr.hs.entrydsm.admin.usecase.exception.AdminNotFoundException;
import kr.hs.entrydsm.admin.usecase.exception.UserNotAccessibleException;
import kr.hs.entrydsm.common.context.auth.manager.AuthenticationManager;
import kr.hs.entrydsm.common.context.sender.ContentSender;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

@RequiredArgsConstructor
@Service
public class ApplicantServiceManager implements ApplicantService {

    private final ApplicantRepository applicantRepository;
    private final AdminRepository adminRepository;

    private final AuthenticationManager authenticationManager;

    private final ContentSender contentSender;

    private static final double SCHOOL_LAT = 36.391636; // 위도
    private static final double SCHOOL_LNG = 127.363585; // 경도

    private static final String ROUTE_URL = "https://apis.openapi.sk.com/tmap/routes?version=1";
    private static final String GEO_BASIC_URL="https://apis.openapi.sk.com/tmap/geo/postcode?version=1&appKey=";

    private static final String PRINTED_ARRIVED = "원서가 도착했음을 알려드립니다.";
    private static final String PRINTED_NOT_ARRIVED = "원서가 아직 도착하지 않았음을 알려드립니다.";

    @Value("${tmap.app.key}")
    private String appKey;

    @Override //지원자 원서 도착 여부 변경
    public void changeIsPrintedArrived(int receiptCode, boolean isPrintedArrived) {
        Admin admin = adminRepository.findById(authenticationManager.getAdminId())
                .orElseThrow(AdminNotFoundException::new);
        if(admin.getPermission().equals(Permission.TEACHER)) {
            applicantRepository.changeIsPrintedArrived(receiptCode, isPrintedArrived);
        }
        else {
            throw new UserNotAccessibleException();
        }

        Applicant applicant = applicantRepository.getUserInfo(receiptCode);
        String template;
        if(isPrintedArrived) template = "PRINTED_ARRIVED";
        else template = "PRINTED_NOT_ARRIVED";
        Map<String, String> params = new HashMap<>();
        params.put("name", applicant.getName());

        contentSender.sendMessage(applicant.getTelephoneNumber(), template, params);
    }

    @Override //지원자 목록
    public ApplicantsResponse getApplicants(Pageable page, Long receiptCode,
                                            boolean isDaejeon, boolean isNationwide,
                                            String telephoneNumber, String name,
                                            boolean isCommon, boolean isMeister, boolean isSocial,
                                            boolean isPrintedArrived) {
        adminRepository.findById(authenticationManager.getAdminId())
                .orElseThrow(AdminNotFoundException::new);

        if(!isDaejeon && !isNationwide) {
            isDaejeon = true;
            isNationwide = true;
        }
        if(!isCommon && !isMeister && !isSocial) {
            isCommon = true;
            isMeister = true;
            isSocial = true;
        }

        Page<Applicant> applicants = applicantRepository.findAll(page, receiptCode, isDaejeon, isNationwide, telephoneNumber, name, isCommon, isMeister, isSocial, isPrintedArrived);
        List<ApplicantsInformationResponse> applicantsInformationResponses= new ArrayList<>();
        
        for (Applicant applicant : applicants) {
            applicantsInformationResponses.add(
                    ApplicantsInformationResponse.builder()
                            .receiptCode(applicant.getReceiptCode())
                            .name(applicant.getName())
                            .isDaejeon(applicant.isDaejeon())
                            .applicationType(applicant.getApplicationType())
                            .isPrintedArrived(applicant.isPrintedArrived())
                            .isSubmit(applicant.isSubmit())
                            .build()
            );
        }

        return  ApplicantsResponse.builder()
                .totalElements((int)applicants.getTotalElements())
                .totalPages(applicants.getTotalPages())
                .applicantsInformationResponses(applicantsInformationResponses)
                .build();
    }

    @Override //지원자 상세보기
    public Object getDetail(int receiptCode) {
        adminRepository.findById(authenticationManager.getAdminId())
                .orElseThrow(AdminNotFoundException::new);

        Applicant applicant = applicantRepository.getUserInfo(receiptCode);
        if(!applicant.isSubmit()) {
            NotSubmitApplicant notSubmitApplicant
                    = new NotSubmitApplicant(applicant.getTelephoneNumber(), applicant.getParentTel(), applicant.getHomeTel(), applicant.getSchoolTel());
            return new ResponseEntity<>(notSubmitApplicant, HttpStatus.LOCKED);
        }

        Status status = Status.builder()
                .isPrintedArrived(applicant.isPrintedArrived())
                .isSubmit(applicant.isSubmit())
                .build();

        PersonalData personalData = PersonalData.builder()
                .photoFileName(applicant.getPhotoFileName())
                .name(applicant.getName())
                .birthDate(applicant.getBirthDate())
                .schoolName(applicant.getSchoolName())
                .email(applicant.getEmail())
                .isGraduated(applicant.isGraduated())
                .educationalStatus(applicant.getEducationalStatus())
                .applicationType(applicant.getApplicationType())
                .address(applicant.getAddress())
                .detailAddress(applicant.getDetailAddress())
                .telephoneNumber(applicant.getTelephoneNumber())
                .parentTel(applicant.getParentTel())
                .schoolTel(applicant.getSchoolTel())
                .homeTel(applicant.getHomeTel())
                .build();

        Evaluation evaluation = Evaluation.builder()
                .volunteerTime(applicant.getVolunteerTime())
                .conversionScore(applicant.getAverageScore())
                .dayAbsenceCount(applicant.getDayAbsenceCount())
                .lectureAbsenceCount(applicant.getLectureAbsenceCount())
                .earlyLeaveCount(applicant.getEarlyLeaveCount())
                .latenessCount(applicant.getLatenessCount())
                .averageScore(applicant.getAverageScore())
                .selfIntroduce(applicant.getSelfIntroduce())
                .studyPlan(applicant.getStudyPlan())
                .build();

        return ApplicantDetailResponse.builder()
                .status(status)
                .personalData(personalData)
                .evaluation(evaluation)
                .build();
    }

    @Override //지원자 수험번호 저장
    public void saveExamCode() throws Exception {
        List<SaveExamCodeUserResponse> applicants = applicantRepository.findAllIsSubmitTrue();
        List<SaveExamCodeUserResponse> applicantSort = new ArrayList<>(applicants);
        int commonDaejeon = 1, commonNationwide = 1, meisterDaejeon = 1,
                meisterNationwide = 1, socialDaejeon = 1, socialNationwide = 1;

        //첫번째, 두번째 자리 채우기
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
                    .lng(SCHOOL_LNG)
                    .lat(SCHOOL_LAT)
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
            if(examCode.startsWith("11")) { ;
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
            applicantRepository.changeExamCode(applicant.getReceiptCode(), applicant.getExamCode());
        }
    }

    private Coordinate getCoordinate(String address) throws URISyntaxException, UnsupportedEncodingException, JsonProcessingException {
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