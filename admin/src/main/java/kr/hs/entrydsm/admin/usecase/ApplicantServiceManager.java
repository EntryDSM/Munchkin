package kr.hs.entrydsm.admin.usecase;

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
import kr.hs.entrydsm.common.context.sms.MessageSender;
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
import java.util.*;

@RequiredArgsConstructor
@Service
public class ApplicantServiceManager implements ApplicantService {

    private final ApplicantRepository applicantRepository;
    private final AdminRepository adminRepository;

    private final AuthenticationManager authenticationManager;

    private final MessageSender messageSender;

    private static final double endX = 127.363585;
    private static final double endY = 36.391636;

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
        String message;
        if(isPrintedArrived) message = PRINTED_ARRIVED;
        else message = PRINTED_NOT_ARRIVED;

        messageSender.sendMessage(applicant.getTelephoneNumber(), message);
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
        String examCode = null;
        List<SaveExamCodeUserResponse> applicantSort = new ArrayList<>();
        applicantSort.addAll(applicants);
        int commonDaejeon = 0, commonNationwide = 0, meisterDaejeon = 0, meisterNationwide = 0, socialDaejeon = 0, socialNationwide = 0;

        //첫번째, 두번째 자리 채우기
        for (SaveExamCodeUserResponse applicant : applicants) {
            String first = applicant.getApplicationType().equals("COMMON") ? "1" : applicant.getApplicationType().equals("MEISTER") ? "2" : "3";
            String second = applicant.isDaejeon() ? "1" : "2";

            examCode = first + second;
            applicant.updateExamCode(examCode);
        }

        for (SaveExamCodeUserResponse applicant : applicants) {
            Coordinate coordinate = getCoordinate(applicant.getAddress());
            RouteGuidanceRequest request = new RouteGuidanceRequest().builder()
                    .endX(endX)
                    .endY(endY)
                    .startX(Double.parseDouble(coordinate.getLon()))
                    .startY(Double.parseDouble(coordinate.getLat()))
                    .build();
            RouteGuidanceResponse distance = routeGuidance(request);
            applicant.updateDistance(distance.getProperties().getTotalDistance());
        }

        Collections.sort(applicantSort, (o1, o2) -> {
            if (o1.getDistance() > o2.getDistance()) {
                return -1; //음수일 때 자리를 바꾸지 않는다.
            } else if (o1.getDistance() < o2.getDistance()) {
                return 1; // 양수일 때 자리를 바꾸고
            } else {
                return 0;
            }
        });

        for(SaveExamCodeUserResponse applicant : applicantSort) {
            int i = 0;
            if(applicant.getExamCode().startsWith("11")) {
                commonDaejeon++;
                i = commonDaejeon;
            } else if(applicant.getExamCode().startsWith("12")) {
                commonNationwide++;
                i = commonNationwide;
            } else if(applicant.getExamCode().startsWith("21")) {
                meisterDaejeon++;
                i = meisterDaejeon;
            } else if(applicant.getExamCode().startsWith("22")) {
                meisterNationwide++;
                i = meisterNationwide;
            } else if(applicant.getExamCode().startsWith("31")) {
                socialDaejeon++;
                i = socialDaejeon;
            } else if(applicant.getExamCode().startsWith("32")) {
                socialNationwide++;
                i = socialNationwide;
            }

            applicant.updateExamCode(examCode + String.format("%03d", i));
            applicantRepository.changeExamCode(applicant.getReceiptCode(), applicant.getExamCode());
        }
    }

    private Coordinate getCoordinate (String address) throws URISyntaxException, UnsupportedEncodingException, JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.add("Accept-Language", "ko");
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE
                + ";charset=UTF-8");

        URI url = new URI(GEO_BASIC_URL + appKey + "&addr=" + URLEncoder.encode(address,"UTF-8") + "&addressFlag=F00&format=json");

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
                .endX(request.getEndX())
                .endY(request.getEndY())
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