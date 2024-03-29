package kr.hs.entrydsm.main.integrate.admin;

import kr.hs.entrydsm.admin.integrate.user.UserRepository;
import kr.hs.entrydsm.admin.usecase.dto.applicant.*;
import kr.hs.entrydsm.admin.usecase.dto.excel.ExcelUser;
import kr.hs.entrydsm.user.entity.user.User;
import kr.hs.entrydsm.user.entity.user.enumeration.ApplicationRemark;
import kr.hs.entrydsm.user.entity.user.enumeration.ApplicationType;
import kr.hs.entrydsm.user.entity.user.enumeration.EducationalStatus;
import kr.hs.entrydsm.user.entity.user.enumeration.Sex;
import kr.hs.entrydsm.user.integrate.admin.UserExportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class AdminIntegrateUserService implements UserRepository {

    private final UserExportRepository userExportRepository;

    @Override
    public Page<ApplicantsInformationResponse> findAll(Pageable page, Long receiptCode,
                                                       boolean isDaejeon, boolean isNationwide,
                                                       String telephoneNumber, String name,
                                                       boolean isCommon, boolean isMeister, boolean isSocial,
                                                       boolean inOfHeadcount, boolean outOfHeadcount,
                                                       Boolean isPrintedArrived) {
        Page<User> users = userExportRepository.findAll(page, receiptCode, isDaejeon, isNationwide, telephoneNumber, name, isCommon, isMeister, isSocial, inOfHeadcount, outOfHeadcount, isPrintedArrived);
        long totalElements = users.getTotalElements();
        List<ApplicantsInformationResponse> applicants = new ArrayList<>();
        for (User user : users) {
            applicants.add(
                    ApplicantsInformationResponse.builder()
                            .receiptCode(user.getReceiptCode())
                            .name(user.getName())
                            .email(user.getEmail())
                            .isDaejeon(user.getIsDaejeon())
                            .applicationType(String.valueOf(user.getApplicationType()))
                            .isPrintedArrived(user.getStatus().isPrintedArrived())
                            .isSubmit(user.getStatus().getIsSubmit())
                            .build()
            );
        }
        return new PageImpl<>(applicants, page, totalElements);
    }

    @Override
    public void changeExamCode(long receiptCode, String examCode) {
        User user = userExportRepository.findByReceiptCode(receiptCode);
        userExportRepository.changeExamCode(user.getReceiptCode(), examCode);
    }

    @Override
    public void changeIsSubmitFalse(long receiptCode) {
        userExportRepository.changeIsSubmitFalse(receiptCode);
    }

    @Override
    public List<SaveExamCodeUserResponse> findAllIsFirstRoundPassTrue() {
        List<User> users = userExportRepository.findAllPassStatusTrue();
        List<SaveExamCodeUserResponse> applicants = new ArrayList<>();
        for (User user : users) {
            applicants.add(
                    SaveExamCodeUserResponse.builder()
                            .applicationType(user.getApplicationType().toString())
                            .isDaejeon(user.getIsDaejeon())
                            .address(user.getAddress())
                            .receiptCode(user.getReceiptCode())
                            .build()
            );
        }

        return applicants;
    }

    @Override
    public List<Long> findAllReceiptCodeIsFirstRoundPassTrue() {
        List<User> users = userExportRepository.findAllPassStatusTrue();
        List<Long> applicants = new ArrayList<>();
        for (User user : users) {
            applicants.add(user.getReceiptCode());
        }

        return applicants;
    }

    @Override
    public UserInfo getUserInfo(long receiptCode) {
        User user = userExportRepository.findByReceiptCode(receiptCode);

        return UserInfo.builder()
                .receiptCode(user.getReceiptCode())
                .examCode(user.getExamCode())
                .name(user.getName())
                .email(user.getEmail())
                .applicationType(String.valueOf(user.getApplicationType()))
                .address(user.getAddress())
                .detailAddress(user.getDetailAddress())
                .birthDate(user.getBirthday())
                .isDaejeon(user.getIsDaejeon())
                .isPrintedArrived(user.getStatus().isPrintedArrived())
                .isSubmit(user.getStatus().getIsSubmit())
                .telephoneNumber(user.getTelephoneNumber())
                .parentTel(user.getParentTel())
                .homeTel(user.getHomeTel())
                .selfIntroduce(user.getSelfIntroduce())
                .photoFileName(user.getPhotoFileName())
                .studyPlan(user.getStudyPlan())
                .educationalStatus(getEducationalStatus(user))
                .applicationRemark(user.getApplicationRemark() == null ? null : user.getApplicationRemark().toString())
                .headcount(user.getHeadcount() == null ? null : user.getHeadcount().toString())
                .build();
    }

    @Override
    public void changeIsPrintedArrived(long receiptCode, boolean isPrintedArrived) {
        userExportRepository.changeIsPrintedArrived(receiptCode, isPrintedArrived);
    }

    @Override
    public UserNameAndEmail getUserNameAndEmail(long receiptCode) {
        User user = userExportRepository.findByReceiptCode(receiptCode);
        return new UserNameAndEmail(user.getName(), user.getEmail());
    }

    @Override
    public List<ExcelUser> findAllForExcel() {
        List<User> users = userExportRepository.findAllIsSubmitTrue();

        List<ExcelUser> excelUsers = new ArrayList<>();
        for (User user : users) {
            String area = getArea(user.getIsDaejeon());
            String sex = getSex(user.getSex());
            String educationalStatus = getEducationalStatus(user.getEducationalStatus());
            String applicationRemark = getApplicationRemark(user.getApplicationRemark());
            String applicationType = getApplicationType(user.getApplicationType());

            excelUsers.add(
                    ExcelUser.builder()
                            .examCode(user.getStatus().getExamCode())
                            .receiptCode(user.getReceiptCode())
                            .applicationType(applicationType)
                            .applicationRemark(applicationRemark)
                            .area(area)
                            .name(user.getName())
                            .birthDay(user.getBirthday().toString())
                            .sex(sex)
                            .address(user.getAddress())
                            .educationalStatus(educationalStatus)
                            .telephoneNumber(user.getTelephoneNumber())
                            .studyPlan(user.getStudyPlan())
                            .selfIntroduce(user.getSelfIntroduce())
                            .parentName(user.getParentName())
                            .parentTel(user.getParentTel())
                    .build()
            );
        }

        return excelUsers;
    }

    @Override
    public void changeIsFirstRoundPass(List<Long> applicationReceiptCode) {
        userExportRepository.changeIsFirstRoundPass(applicationReceiptCode);
    }

    @Override
    public int countSubmittedApplicantCount() {
        return userExportRepository.findAllIsSubmitTrue().size();
    }

    private String getApplicationType(ApplicationType applicationType) {
        switch (applicationType) {
            case COMMON:
                return "일반전형";
            case MEISTER:
                return "마이스터전형";
            case SOCIAL:
                return "사회통합전형";
            default:
                return null;
        }
    }

    private String getEducationalStatus(User user) {
        EducationalStatus status = user.getEducationalStatus();
        if (status == null) return "";
        return status.toString();
    }

    private String getArea(Boolean isDaejeon) {
        if(isDaejeon) {
            return "대전";
        } else if(!isDaejeon) {
            return "전국";
        }
        return null;
    }

    private String getSex(Sex sex) {
        if(sex.equals(Sex.MALE)) {
            return "남자";
        } else if(sex.equals(Sex.FEMALE)) {
            return "여자";
        }
        return null;
    }

    private String getEducationalStatus(EducationalStatus educationalStatus) {
        switch (educationalStatus) {
            case PROSPECTIVE_GRADUATE :
                return "졸업예정자";
            case GRADUATE :
                return "졸업자";
            case QUALIFICATION_EXAM :
                return "검정고시합격자";
            default:
                return null;
        }
    }

    private String getApplicationRemark(ApplicationRemark applicationRemark) {
        if (applicationRemark == null) {
            return "일반";
        }
        switch (applicationRemark) {
            case ONE_PARENT :
                return "한부모가족";
            case FROM_NORTH:
                return "북한이탈주민";
            case MULTICULTURAL:
                return "다문화가정";
            case BASIC_LIVING:
                return "기초생활수급자";
            case LOWEST_INCOME:
                return "차상위계층";
            case TEEN_HOUSEHOLDER:
                return "소년소녀가장";
            case PRIVILEGED_ADMISSION:
                return "특례입학대상자";
            case NATIONAL_MERIT:
                return "국가유공자";
        }
        return null;
    }

}