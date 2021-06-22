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

    private boolean isDaejeon;

    private String name;

    private String sex;

    private LocalDate birthday;

    private String parentName;

    private String parentTel;

    private String address;

    private String postCode;

    private String photoFileName;

    private String homeTel;

    private String selfIntroduce;

    private String studyPlan;

    public boolean isMale() {
        return sex.equals("MALE");
    }

    public boolean isFemale() {
        return sex.equals("FEMALE");
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
        return educationalStatus.equals(EducationalStatus.QUALIFICATION_EXAM);
    }

    public boolean isGraduate() {
        return educationalStatus.equals(EducationalStatus.GRADUATE);
    }

    public boolean isProspectiveGraduate() {
        return educationalStatus.equals(EducationalStatus.PROSPECTIVE_GRADUATE);
    }

    public boolean isBasicLiving() {
        return applicationRemark.equals(ApplicationRemark.BASIC_LIVING);
    }

    public boolean isFromNorth() {
        return applicationRemark.equals(ApplicationRemark.FROM_NORTH);
    }

    public boolean isLowestIncome() {
        return applicationRemark.equals(ApplicationRemark.LOWEST_INCOME);
    }

    public boolean isMulticultural() {
        return applicationRemark.equals(ApplicationRemark.MULTICULTURAL);
    }

    public boolean isOneParent() {
        return applicationRemark.equals(ApplicationRemark.ONE_PARENT);
    }

    public boolean isTeenHouseholder() {
        return applicationRemark.equals(ApplicationRemark.TEEN_HOUSEHOLDER);
    }

    public boolean isCommonApplicationType() {
        return applicationType.equals(ApplicationType.COMMON);
    }

    public boolean isMeisterApplicationType() {
        return applicationType.equals(ApplicationType.MEISTER);
    }

    public boolean isSocialApplicationType() {
        return applicationType.equals(ApplicationType.SOCIAL);
    }

    public boolean isRecommendationsRequired() {
        return !isEducationalStatusEmpty() && !isCommonApplicationType() && !isProspectiveGraduate();
    }
}
