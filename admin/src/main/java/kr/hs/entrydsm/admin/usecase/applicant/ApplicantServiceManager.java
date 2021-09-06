package kr.hs.entrydsm.admin.usecase.applicant;

import kr.hs.entrydsm.admin.integrate.applicaton.ApplicationRepository;
import kr.hs.entrydsm.admin.usecase.dto.applicant.*;
import kr.hs.entrydsm.admin.integrate.user.UserRepository;
import kr.hs.entrydsm.common.context.sender.ContentSender;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ApplicantServiceManager implements ApplicantService {

    private final UserRepository userRepository;
    private final ApplicationRepository applicationRepository;

    private final ContentSender contentSender;

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

        return  ApplicantsResponse.builder()
                .totalElements(applicants.getTotalElements())
                .totalPages(applicants.getTotalPages())
                .applicantsInformationResponses(
                        applicants.stream().map(
                                applicant -> ApplicantsInformationResponse.builder()
                                        .receiptCode(applicant.getReceiptCode())
                                        .name(applicant.getName())
                                        .isDaejeon(applicant.getIsDaejeon())
                                        .applicationType(applicant.getApplicationType())
                                        .isPrintedArrived(applicant.getIsPrintedArrived())
                                        .isSubmit(applicant.getIsSubmit())
                                        .build()
                        ).collect(Collectors.toList()))
                .build();
    }

    @Override
    public ResponseEntity getDetailApplicantInfo(int receiptCode) {
        UserInfo userInfo = userRepository.getUserInfo(receiptCode);
        ApplicantInfo applicantInfo = applicationRepository.getApplicantInfo(receiptCode);

        if(!userInfo.getIsSubmit()) {
            NotSubmitApplicantResponse notSubmitApplicant
                    = new NotSubmitApplicantResponse(NotSubmitApplicantDto.builder()
                    .email(userInfo.getEmail())
                    .schoolTel(applicantInfo.getSchoolTel())
                    .applicantTel(userInfo.getTelephoneNumber())
                    .parentTel(userInfo.getParentTel())
                    .homeTel(userInfo.getHomeTel())
                    .build());
            return new ResponseEntity<>(notSubmitApplicant, HttpStatus.LOCKED);
        }

        Status status = Status.builder()
                .isPrintedArrived(userInfo.getIsPrintedArrived())
                .isSubmit(userInfo.getIsSubmit())
                .build();

        PersonalData personalData = PersonalData.builder()
                .photoFileName(userInfo.getPhotoFileName())
                .name(userInfo.getName())
                .birthDate(userInfo.getBirthDate().toString())
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

        ApplicantDetailResponse applicantDetailResponse = new ApplicantDetailResponse(ApplicantDetailDto.builder()
                .personalData(personalData)
                .status(status)
                .evaluation(evaluation)
                .build());
        
        return new ResponseEntity<>(applicantDetailResponse, HttpStatus.OK);
    }
    
}