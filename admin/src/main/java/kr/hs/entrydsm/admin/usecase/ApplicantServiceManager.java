package kr.hs.entrydsm.admin.usecase;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.hs.entrydsm.admin.domain.entity.Admin;
import kr.hs.entrydsm.admin.domain.entity.Applicant;
import kr.hs.entrydsm.admin.domain.repository.AdminRepository;
import kr.hs.entrydsm.admin.integrate.user.ApplicantRepository;
import kr.hs.entrydsm.admin.usecase.dto.*;
import kr.hs.entrydsm.admin.usecase.dto.request.RouteGuidanceRequest;
import kr.hs.entrydsm.admin.usecase.dto.response.*;
import kr.hs.entrydsm.admin.usecase.exception.AdminNotFoundException;
import kr.hs.entrydsm.admin.usecase.exception.UserNotAccessibleException;
import kr.hs.entrydsm.common.context.auth.manager.AuthenticationManager;
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

    private final double endX = 127.363585;
    private final double endY = 36.391636;

    @Value("${tmap.app.key}")
    private String appKey;

    @Override
    public void updateStatus(int receiptCode, boolean isPrintedArrived, boolean isPaid, boolean isSubmit) {
        Admin admin = adminRepository.findById(authenticationManager.getAdminId())
                .orElseThrow(AdminNotFoundException::new);

        if(admin.getPermission().equals("TEACHER")) {
            applicantRepository.changeStatus(receiptCode, isPrintedArrived, isPaid, isSubmit);
        }
        else {
            throw new UserNotAccessibleException();
        }

        //공지메세지에서 보내주기
    }

    @Override
    public ApplicantsResponse getApplicants(Pageable page, boolean isDaejeon, boolean isNationwide,
                                            boolean isPrintedArrived, boolean isPaid, boolean isCommon,
                                            boolean isMeister, boolean isSocial, int receiptCode,
                                            String schoolName, String telephoneNumber, String name) {
        adminRepository.findById(authenticationManager.getAdminId())
                .orElseThrow(AdminNotFoundException::new);

        Page<Applicant> applicants = applicantRepository.findAll(page, isDaejeon, isNationwide, isPrintedArrived, isPaid, isCommon, isMeister, isSocial, receiptCode, schoolName, telephoneNumber, name);
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
    public ApplicantDetailResponse getDetail(int receiptCode) {
        adminRepository.findById(authenticationManager.getAdminId())
                .orElseThrow(AdminNotFoundException::new);

        Applicant applicant = applicantRepository.getUserInfo(receiptCode);

        Status status = Status.builder()
                .isPaid(applicant.isPaid())
                .isPrintedArrived(applicant.isPrintedArrived())
                .isSubmit(applicant.isSubmit())
                .build();

        PersonalData personalData = PersonalData.builder()
                .name(applicant.getName())
                .birthDate(applicant.getBirthDate())
                .isGraduated(applicant.isGraduated())
                .applicationType(applicant.getApplicationType())
                .photoFileName(applicant.getPhotoFileName())
                .schoolName(applicant.getSchoolName())
                .schoolTel(applicant.getSchoolTel())
                .address(applicant.getAddress())
                .detailAddress(applicant.getDetailAddress())
                .educationalStatus(applicant.getEducationalStatus())
                .telephoneNumber(applicant.getTelephoneNumber())
                .homeTel(applicant.getHomeTel())
                .parentTel(applicant.getParentTel())
                .build();

        Evaluation evaluation = Evaluation.builder()
                .studyPlan(applicant.getStudyPlan())
                .selfIntroduce(applicant.getSelfIntroduce())
                .averageScore(applicant.getAverageScore())
                .volunteerTime(applicant.getVolunteerTime())
                .conversionScore(applicant.getAverageScore())
                .lectureAbsenceCount(applicant.getLectureAbsenceCount())
                .earlyLeaveCount(applicant.getEarlyLeaveCount())
                .latenessCount(applicant.getLatenessCount())
                .dayAbsenceCount(applicant.getDayAbsenceCount())
                .build();

        return ApplicantDetailResponse.builder()
                .status(status)
                .personalData(personalData)
                .evaluation(evaluation)
                .build();
    }

    @Override
    public void saveExamCode() throws Exception {
        List<Applicant> applicants = applicantRepository.findAllIsSubmitTrue();
        String examCode = null;
        List<Applicant> applicantSort = new ArrayList<>();
        applicantSort.addAll(applicants);
        int commonDaejeon = 0, commonNationwide = 0, meisterDaejeon = 0, meisterNationwide = 0, socialDaejeon = 0, socialNationwide = 0;

        //첫번째, 두번째 자리 채우기
        for (Applicant applicant : applicants) {
            String first = applicant.getApplicationType().equals("COMMON") ? "1" : applicant.getApplicationType().equals("MEISTER") ? "2" : "3";
            String second = applicant.isDaejeon() ? "1" : "2";

            examCode = first + second;
        }

        for (Applicant applicant : applicants) {
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

        for(Applicant applicant : applicantSort) {
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

        URI url = new URI("https://apis.openapi.sk.com/tmap/geo/postcode?version=1&appKey="
                + appKey + "&addr=" + URLEncoder.encode(address,"UTF-8") + "&addressFlag=F00&format=json");

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

        URI url = new URI("https://apis.openapi.sk.com/tmap/routes?version=1");

        ObjectMapper mapper = new ObjectMapper();
        HttpEntity<RouteBody> rq = new HttpEntity<>(routeBody, headers);
        Map<String, Map> response = restTemplate.postForObject(url, rq, Map.class);

        List<RouteGuidanceResponse> map =
                Arrays.asList(mapper.readValue(mapper.writeValueAsString(response.get("features")), RouteGuidanceResponse[].class));
        return map.get(0);
    }
}