package kr.hs.entrydsm.admin.usecase;

import kr.hs.entrydsm.admin.domain.entity.Admin;
import kr.hs.entrydsm.admin.domain.entity.Applicant;
import kr.hs.entrydsm.admin.domain.entity.enums.Permission;
import kr.hs.entrydsm.admin.domain.repository.AdminRepository;
import kr.hs.entrydsm.admin.integrate.user.ApplicantRepository;
import kr.hs.entrydsm.admin.security.auth.AuthenticationFacade;
import kr.hs.entrydsm.admin.usecase.dto.*;
import kr.hs.entrydsm.admin.usecase.exception.AdminNotFoundException;
import kr.hs.entrydsm.admin.usecase.exception.ApplicantNotFinalSubmitted;
import kr.hs.entrydsm.admin.usecase.exception.ApplicantNotFoundException;
import kr.hs.entrydsm.admin.usecase.exception.UserNotAccessibleException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ApplicantServiceManager implements ApplicantService {

    private final ApplicantRepository applicantRepository;
    private final AdminRepository adminRepository;

    private final AuthenticationFacade authenticationFacade;

    @Override
    public void updateStatus(Integer recieptCode, boolean isPrintedArrived, boolean isPaid, boolean isSubmit) {
        Admin admin = adminRepository.findById(authenticationFacade.getUserId())
                .orElseThrow(AdminNotFoundException::new);

        Applicant applicant = applicantRepository.findByReceiptCode(recieptCode);

        if(applicant != null) {
            if(admin.getPermission() == Permission.OFFICE) { //행정실은 원서료 납부만 수정 가능
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
    public ApplicantsResponse getApplicants(Integer size, Integer page, boolean isDaejeon, boolean isNationwide,
                                                  boolean isPrintedArrived, boolean isPaid, boolean isCommon,
                                                  boolean isMeiseter, boolean isSocial, Integer recieptCode,
                                                  String schoolName, String telephoneNumber, String name) {
        if(!authenticationFacade.isLogin()) {
            throw new UserNotAccessibleException();
        }

        Page<Applicant> applicants = applicantRepository.findAll();
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
            throw new ApplicantNotFinalSubmitted();
        }

        Status status = new Status().builder()
                .isPaid(applicant.isPaid())
                .isPrintedArrived(applicant.isPrintedArrived())
                .isSubmit(applicant.isSubmit())
                .build();

        Evaluation evaluation = new Evaluation().builder()
                .volunteerTime(applicant.getVolunteerTime())
                .conversionScore(applicant.getConversionScore())
                .dayAbsenceCount(applicant.getDayAbsenceCount())
                .latenessCount(applicant.getLatenessCount())
                .earlyLeaveCount(applicant.getEarlyLeaveCount())
                .lectureAbsenceCount(applicant.getLectureAbsenceCount())
                .selfIntroduce(applicant.getSelfIntroduce())
                .studyPlan(applicant.getStudyPlan())
                .build();

        PersonalData personalData = new PersonalData().builder()
                .name(applicant.getName())
                .photoFileName(applicant.getPhotoFileName())
                .address(applicant.getAddress())
                .detailAddress(applicant.getDetailAddress())
                .birthDate(applicant.getBirthDate())
                .schoolName(applicant.getSchoolName())
                .educationalStatus(applicant.getEducationalStatus())
                .applicationType(applicant.getApplicationType())
                .telephoneNumber(applicant.getTelephoneNumber())
                .parentTel(applicant.getParentTel())
                .schoolTel(applicant.getSchoolTel())
                .homeTel(applicant.getHomeTel())
                .build();

        return ApplicantDetailResponse.builder()
                .status(status)
                .evaluation(evaluation)
                .personalData(personalData)
                .build();
    }
}
