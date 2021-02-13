package kr.hs.entrydsm.admin.usecase;

import kr.hs.entrydsm.admin.domain.entity.Admin;
import kr.hs.entrydsm.admin.domain.entity.Applicant;
import kr.hs.entrydsm.admin.domain.entity.enums.Permission;
import kr.hs.entrydsm.admin.domain.repository.AdminRepository;
import kr.hs.entrydsm.admin.integrate.user.ApplicantRepository;
import kr.hs.entrydsm.admin.security.auth.AuthenticationFacade;
import kr.hs.entrydsm.admin.usecase.dto.*;
import kr.hs.entrydsm.admin.usecase.dto.request.RouteGuidanceRequest;
import kr.hs.entrydsm.admin.usecase.dto.response.*;
import kr.hs.entrydsm.admin.usecase.exception.AdminNotFoundException;
import kr.hs.entrydsm.admin.usecase.exception.ApplicantNotFoundException;
import kr.hs.entrydsm.admin.usecase.exception.UserNotAccessibleException;
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

    private final AuthenticationFacade authenticationFacade;

    private final double endX = 36.39181879;
    private final double endY = 127.36332440;

    @Value("${tmap.app.key}")
    private String appKey;

    @Override
    public void updateStatus(Integer recieptCode, boolean isPrintedArrived, boolean isPaid, boolean isSubmit) {
        Admin admin = adminRepository.findById(authenticationFacade.getUserId())
                .orElseThrow(AdminNotFoundException::new);

        Applicant applicant = applicantRepository.findByReceiptCode(recieptCode);

        if(applicant != null) {
            if(admin.getPermission().equals(Permission.TEACHER)) { //행정실은 원서료 납부만 수정 가능
                applicant.updateIsPaid(isPaid);
            }
            else { //교무실은 모든 권한 有
                applicant.updateStatus(isPrintedArrived, isPaid, isSubmit);
            }
        }
        else {
            throw new ApplicantNotFoundException();
        }
        //공지메세지에서 보내주기
    }

    @Override
    public ApplicantsResponse getApplicants(Pageable page, boolean isDaejeon, boolean isNationwide,
                                            boolean isPrintedArrived, boolean isPaid, boolean isCommon,
                                            boolean isMeister, boolean isSocial, Integer recieptCode,
                                            String schoolName, String telephoneNumber, String name) {
        if(!authenticationFacade.isLogin()) {
            throw new UserNotAccessibleException();
        }

        Page<Applicant> applicants = applicantRepository.findAll(page);
        List<ApplicantsInformationResponse> applicantsInformationResponses= new ArrayList<>();

        
        for (Applicant applicant : applicants) {
            applicantsInformationResponses.add(
                    ApplicantsInformationResponse.builder()
                            .receiptCode(applicant.getReceiptCode())
                            .name(applicant.getName())
                            .isDaejeon(applicant.isDaejeon())
                            .applicationType(applicant.getApplicationType())
                            .isPrintedArrived(applicant.isPrintedArrived())
                            .isPaid(applicant.isPaid())
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

    @Override
    public ApplicantDetailResponse getDetail(Integer recieptCode) {
        if(!authenticationFacade.isLogin()) {
            throw new UserNotAccessibleException();
        }

        Applicant applicant = applicantRepository.findByReceiptCode(recieptCode);
        if(applicant == null) {
            throw new ApplicantNotFoundException();
        }
        if(applicant.isSubmit() == false) {
            //최종 제출하지 않은 사용자는 지원자의 전화번호, 부모님의 전화번호, 집 전화번호, (학교 전화번호)만 보여줘야 한다.
            ApplicantInfo applicantInfo = new ApplicantInfo().builder()
                    .applicantTel(applicant.getTelephoneNumber())
                    .homeTel(applicant.getHomeTel())
                    .parentTel(applicant.getParentTel())
                    .schoolTel(applicant.getSchoolTel())
                    .build();

            return ApplicantDetailResponse.builder()
                    .applicantInfo(applicantInfo)
                    .build();
            //throw new ApplicantNotFinalSubmitted();
        }

        Status status = new Status().builder()
                .isPaid(applicant.isPaid())
                .isPrintedArrived(applicant.isPrintedArrived())
                .isSubmit(applicant.isSubmit())
                .build();

        Evaluation evaluation;
        evaluation = new Evaluation().builder()
                .selfIntroduce(applicant.getSelfIntroduce())
                .studyPlan(applicant.getStudyPlan())
                .build();

        PersonalData personalData = new PersonalData().builder()
                .name(applicant.getName())
                .photoFileName(applicant.getPhotoFileName())
                .birthDate(applicant.getBirthDate())
                .schoolName(applicant.getSchoolName())
                .educationalStatus(applicant.getEducationalStatus())
                .applicationType(applicant.getApplicationType())
                .telephoneNumber(applicant.getTelephoneNumber())
                .parentTel(applicant.getParentTel())
                .schoolTel(applicant.getSchoolTel())
                .homeTel(applicant.getHomeTel())
                .build();

        if(applicant.getEducationalStatus() == "QUALIFICATION_EXAM") {
            evaluation = Evaluation.builder()
                    .averageScore(applicant.getAverageScore())
                    .build();
        }else {
            evaluation = Evaluation.builder()
                    .volunteerTime(applicant.getVolunteerTime())
                    .conversionScore(applicant.getConversionScore())
                    .dayAbsenceCount(applicant.getDayAbsenceCount())
                    .latenessCount(applicant.getLatenessCount())
                    .earlyLeaveCount(applicant.getEarlyLeaveCount())
                    .lectureAbsenceCount(applicant.getLectureAbsenceCount())
                    .build();
        }

        return ApplicantDetailResponse.builder()
                .status(status)
                .evaluation(evaluation)
                .personalData(personalData)
                .build();
    }

    @Override
    public void saveExamCode() throws Exception {
        List<Applicant> applicants = applicantRepository.findAllIsSubmitTrue();
        List<Applicant> applicantSort = applicants;

        //첫번째, 두번째 자리 채우기
        for(Applicant applicant : applicants) {
            String first = applicant.getApplicationType().equals("COMMON")?"1":applicant.getApplicationType().equals("MEISTER")?"2":"3";
            String second = applicant.isDaejeon()?"1":"2";

            applicant.updateExamCode(first + second);
        }

        for(Applicant applicant : applicants) {
            CoordinateResponse coordinateResponse = coordinate(applicant.getAddress());
            RouteGuidanceRequest request = new RouteGuidanceRequest().builder()
                    .endX(endX)
                    .endY(endY)
                    .startX(coordinateResponse.getLat())
                    .startY(coordinateResponse.getLon())
                    .build();
            RouteGuidanceResponse distance = routeGuidance(request);
            applicant.updateDistance(distance.getTotalDistance());
        }

        //앞 자리로 분류해서 거리가 먼 순서대로 정렬

        /*Collections.sort(applicantSort, (o1, o2) -> {
            if(o1.getDistance()>o2.getDistance()) {
                return -1; //음수일 때 자리를 바꾸지 않는다.
            }else if(o1.getDistance()<o2.getDistance()) {
                return 1; // 양수일 때 자리를 바꾸고
            }else {
                return 0;
            }
        });

        int i = 0;
        for(Applicant applicant : applicantSort) {
            applicant.updateExamCode(examCode + String.format("%03d",i++));
            applicantRepository.changeExamCode(applicant.getReceiptCode(), applicant.getExamCode());
        }*/
    }

    private CoordinateResponse coordinate(String address) throws UnsupportedEncodingException, URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        String query = "&addressFlag=F00" + "&format=json";

        headers.add("Accept-Language", "ko");
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        URI url = new URI("https://apis.openapi.sk.com/tmap/geo/postcode?version=1&appKey=" + appKey + "&addr=" + URLEncoder.encode(address,"UTF-8") + query);

        RequestEntity<String> rq = new RequestEntity<>(headers, HttpMethod.GET, url);
        ResponseEntity<CoordinateResponse> responseEntity = restTemplate.exchange(rq, CoordinateResponse.class);

        return responseEntity.getBody();
    }

    private RouteGuidanceResponse routeGuidance(RouteGuidanceRequest request) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        String body = "endX=" + request.getEndX()
                + "&endY=" + request.getEndY()
                + "&startX=" + request.getStartX()
                + "&startY=" + request.getStartY()
                + "&totalValue=" + 2;

        headers.add("Accept-Language", "ko");
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        URI url = URI.create("https://apis.openapi.sk.com/tmap/routes?version=1&format=json");
        HttpEntity entity = new HttpEntity(body, headers);

        ResponseEntity<RouteGuidanceResponse> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, RouteGuidanceResponse.class);

        return responseEntity.getBody();
    }
}