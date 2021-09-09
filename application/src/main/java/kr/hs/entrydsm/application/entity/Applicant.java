package kr.hs.entrydsm.application.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Applicant {

    private long receiptCode;

    private String telephoneNumber;

    private String applicationType;

    private String applicationRemark;

    private String educationalStatus;

    private Boolean isDaejeon;

    private String name;

    private String sex;

    private LocalDate birthday;

    private String parentName;

    private String parentTel;

    private String address;

    private String detailAddress;

    private String postCode;

    private String photoFileName;

    private String homeTel;

    private String selfIntroduce;

    private String studyPlan;

    public boolean isMale() {
        return getFalseIfNull(sex, "MALE");
    }

    public boolean isFemale() {
        return getFalseIfNull(sex, "FEMALE");
    }

    public boolean hasSchoolInfo() {
        return !isEducationalStatusEmpty() && !isQualificationExam();
    }

    public boolean isEducationalStatusEmpty() {
        return educationalStatus == null;
    }

    public boolean isHomeTelEmpty() {
        return homeTel == null;
    }

    public boolean isQualificationExam() {
        return getFalseIfNull(educationalStatus, EducationalStatus.QUALIFICATION_EXAM);
    }

    public boolean isGraduate() {
        return getFalseIfNull(educationalStatus, EducationalStatus.GRADUATE);
    }

    public boolean isProspectiveGraduate() {
        return getFalseIfNull(educationalStatus, EducationalStatus.PROSPECTIVE_GRADUATE);
    }

    public boolean isBasicLiving() {
        return getFalseIfNull(applicationRemark, ApplicationRemark.BASIC_LIVING);
    }

    public boolean isFromNorth() {
        return getFalseIfNull(applicationRemark, ApplicationRemark.FROM_NORTH);
    }

    public boolean isLowestIncome() {
        return getFalseIfNull(applicationRemark, ApplicationRemark.LOWEST_INCOME);
    }

    public boolean isMulticultural() {
        return getFalseIfNull(applicationRemark, ApplicationRemark.MULTICULTURAL);
    }

    public boolean isOneParent() {
        return getFalseIfNull(applicationRemark, ApplicationRemark.ONE_PARENT);
    }

    public boolean isTeenHouseholder() {
        return getFalseIfNull(applicationRemark, ApplicationRemark.TEEN_HOUSEHOLDER);
    }

    public boolean isPrivilegedAdmission() {
        return getFalseIfNull(applicationRemark, ApplicationRemark.PRIVILEGED_ADMISSION);
    }

    public boolean isNationalMerit() {
        return getFalseIfNull(applicationRemark, ApplicationRemark.NATIONAL_MERIT);
    }

    public boolean isCommonApplicationType() {
        return getFalseIfNull(applicationType, ApplicationType.COMMON);
    }

    public boolean isMeisterApplicationType() {
        return getFalseIfNull(applicationType, ApplicationType.MEISTER);
    }

    public boolean isSocialApplicationType() {
        return getFalseIfNull(applicationType, ApplicationType.SOCIAL);
    }

    public boolean isRecommendationsRequired() {
        return !isEducationalStatusEmpty() && !isCommonApplicationType() && !isProspectiveGraduate();
    }

    public boolean isFilledInfo() {
        return isExists(name) && sex != null && birthday != null && isExists(telephoneNumber) && isExists(parentTel)
                && isExists(parentName) && isExists(address) && isExists(detailAddress) && isExists(postCode)
                && photoFileName != null;
    }

    public boolean isGraduation() {
        return isGraduate() || isProspectiveGraduate();
    }

    private <T> boolean getFalseIfNull(T object, T comparison) {
        return object != null && object.equals(comparison);
    }

    private boolean isExists(String target) {
        return target != null && !target.isBlank();
    }
}
