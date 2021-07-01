package kr.hs.entrydsm.main.integrate.admin;

import kr.hs.entrydsm.admin.integrate.user.ApplicantRepository;
import kr.hs.entrydsm.admin.usecase.dto.Applicant;
import kr.hs.entrydsm.admin.usecase.dto.ExcelUser;
import kr.hs.entrydsm.admin.usecase.dto.SaveExamCodeUserResponse;
import kr.hs.entrydsm.application.integrate.admin.ApplicationExportAdminRepository;
import kr.hs.entrydsm.application.usecase.dto.ReportCard;
import kr.hs.entrydsm.user.entity.user.User;
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
public class AdminIntegrateUserService implements ApplicantRepository {

    private final UserExportRepository userExportRepository;
    private final ApplicationExportAdminRepository applicationExportRepository;

    @Override
    public Page<Applicant> findAll(Pageable page, Long receiptCode,
                                   boolean isDaejeon, boolean isNationwide,
                                   String telephoneNumber, String name,
                                   boolean isCommon, boolean isMeister, boolean isSocial,
                                   Boolean isPrintedArrived) {
        Page<User> users = userExportRepository.findAll(page, receiptCode, isDaejeon, isNationwide, telephoneNumber, name, isCommon, isMeister, isSocial, isPrintedArrived);
        long totalElements = users.getTotalElements();
        List<Applicant> applicants = new ArrayList<>();
        for (User user : users) {
            applicants.add(
                    Applicant.builder()
                            .receiptCode(user.getReceiptCode())
                            .name(user.getName())
                            .isDaejeon(user.isDaejeon())
                            .applicationType(String.valueOf(user.getApplicationType()))
                            .isPrintedArrived(user.getStatus().isPrintedArrived())
                            .isSubmit(user.getStatus().isSubmit())
                            .build()
            );
        }
        return new PageImpl<>(applicants, page, totalElements);

    }

    @Override
    public void changeExamCode(long receiptCode, String examCode) {
        User user = userExportRepository.findByReceiptCode((int)receiptCode);
        userExportRepository.changeExamCode(user.getReceiptCode(), examCode);
    }

    @Override
    public List<SaveExamCodeUserResponse> findAllIsSubmitTrue() {
        List<User> users = userExportRepository.findAllIsSubmitTrue();
        List<SaveExamCodeUserResponse> applicants = new ArrayList<>();
        for (User user : users) {
            applicants.add(
                    SaveExamCodeUserResponse.builder()
                            .applicationType(user.getApplicationType().toString())
                            .isDaejeon(user.isDaejeon())
                            .address(user.getAddress())
                            .receiptCode(user.getReceiptCode())
                            .build()
            );
        }

        return applicants;
    }

    @Override
    public Applicant getUserInfo(long receiptCode) {
        User user = userExportRepository.findByReceiptCode((int) receiptCode);

        ReportCard reportCard = applicationExportRepository.getReportCard(user.getReceiptCode());

        return Applicant.builder()
                .receiptCode(user.getReceiptCode())
                .name(user.getName())
                .photoFileName(user.getPhotoFileName())
                .email(user.getEmail())
                .applicationType(String.valueOf(user.getApplicationType()))
                .address(user.getAddress())
                .detailAddress(user.getDetailAddress())
                .birthDate(user.getBirthday())
                .isPrintedArrived(user.getStatus().isPrintedArrived())
                .isSubmit(user.getStatus().isSubmit())
                .telephoneNumber(user.getTelephoneNumber())
                .parentTel(user.getParentTel())
                .homeTel(user.getHomeTel())
                .selfIntroduce(user.getSelfIntroduce())
                .photoFileName(user.getPhotoFileName())
                .studyPlan(user.getStudyPlan())
                .averageScore(reportCard.getAverageScore())
                .isGraduated(reportCard.getIsGraduated())
                .volunteerTime(reportCard.getVolunteerTime())
                .schoolTel(reportCard.getSchoolTel())
                .schoolName(reportCard.getSchoolName())
                .latenessCount(reportCard.getLatenessCount())
                .earlyLeaveCount(reportCard.getEarlyLeaveCount())
                .lectureAbsenceCount(reportCard.getLectureAbsenceCount())
                .dayAbsenceCount(reportCard.getDayAbsenceCount())
                .conversionScore(reportCard.getTotalScore())
                .build();
    }

    @Override
    public void changeIsPrintedArrived(long receiptCode, boolean isPrintedArrived) {
        userExportRepository.changeIsPrintedArrived(receiptCode, isPrintedArrived);
    }

    @Override
    public List<ExcelUser> findAllForExcel() {
        List<User> users = userExportRepository.findAllForExcel();

        List<ExcelUser> excelUsers = new ArrayList<>();
        for (User user : users) {
            String area;
            String sex;
            String educationalStatus;
            String applicationRemark;
            String applicationType;

            if(user.getSex().equals(Sex.MALE)) {
                sex = "남자";
            } else {
                sex = "여자";
            }

            if(user.isDaejeon()) {
                area = "대전";
            } else {
                area = "전국";
            }

            switch (user.getApplicationType()) {
                case COMMON:
                    applicationType = "일반전형";
                    break;
                case MEISTER:
                    applicationType = "마이스터전형";
                    break;
                case SOCIAL:
                    applicationType = "사회통합전형";
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + user.getApplicationType());
            }

            switch (user.getEducationalStatus()) {
                case PROSPECTIVE_GRADUATE :
                    educationalStatus = "졸업예정자";
                    break;
                case GRADUATE :
                    educationalStatus = "졸업자";
                    break;
                case QUALIFICATION_EXAM :
                    educationalStatus = "검정고시합격자";
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + user.getEducationalStatus());
            }

            switch (user.getApplicationRemark()) {
                case ONE_PARENT :
                    applicationRemark = "한부모가족";
                    break;
                case FROM_NORTH:
                    applicationRemark = "북한이탈주민";
                    break;
                case MULTICULTURAL:
                    applicationRemark = "다문화가정";
                    break;
                case BASIC_LIVING:
                    applicationRemark = "기초생활수급자";
                    break;
                case LOWEST_INCOME:
                    applicationRemark = "차상위계층";
                    break;
                case TEEN_HOUSEHOLDER:
                    applicationRemark = "소년소녀가장";
                    break;
                case PRIVILEGED_ADMISSION:
                    applicationRemark = "특례입학대상자";
                    break;
                case NATIONAL_MERIT:
                    applicationRemark = "국가유공자";
                    break;
                default:
                    applicationRemark = "일반";
            }

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
    public List<Long> getUserReceiptCodes() {
        return userExportRepository.findAllReceiptCode();
    }

}