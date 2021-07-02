package kr.hs.entrydsm.application.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ApplicantTest extends EntityTest{

    @Test
    public void isGraduate() {
        assertTrue(graduateCommonMale.isGraduate());
        assertTrue(graduateCommonFemale.isGraduate());
        assertTrue(graduateMeisterMale.isGraduate());
        assertTrue(graduateMeisterFemale.isGraduate());
        assertTrue(graduateSocialMale.isGraduate());
        assertTrue(graduateSocialFemale.isGraduate());

        assertFalse(prospectiveGraduateCommonMale.isGraduate());
        assertFalse(prospectiveGraduateCommonFemale.isGraduate());
        assertFalse(prospectiveGraduateMeisterMale.isGraduate());
        assertFalse(prospectiveGraduateMeisterFemale.isGraduate());
        assertFalse(prospectiveGraduateSocialMale.isGraduate());
        assertFalse(prospectiveGraduateSocialFemale.isGraduate());

        assertFalse(qualificationExamCommonMale.isGraduate());
        assertFalse(qualificationExamCommonFemale.isGraduate());
        assertFalse(qualificationExamMeisterMale.isGraduate());
        assertFalse(qualificationExamMeisterFemale.isGraduate());
        assertFalse(qualificationExamSocialMale.isGraduate());
        assertFalse(qualificationExamSocialFemale.isGraduate());
    }

    @Test
    public void isProspectiveGraduate() {
        assertFalse(graduateCommonMale.isProspectiveGraduate());
        assertFalse(graduateCommonFemale.isProspectiveGraduate());
        assertFalse(graduateMeisterMale.isProspectiveGraduate());
        assertFalse(graduateMeisterFemale.isProspectiveGraduate());
        assertFalse(graduateSocialMale.isProspectiveGraduate());
        assertFalse(graduateSocialFemale.isProspectiveGraduate());

        assertTrue(prospectiveGraduateCommonMale.isProspectiveGraduate());
        assertTrue(prospectiveGraduateCommonFemale.isProspectiveGraduate());
        assertTrue(prospectiveGraduateMeisterMale.isProspectiveGraduate());
        assertTrue(prospectiveGraduateMeisterFemale.isProspectiveGraduate());
        assertTrue(prospectiveGraduateSocialMale.isProspectiveGraduate());
        assertTrue(prospectiveGraduateSocialFemale.isProspectiveGraduate());

        assertFalse(qualificationExamCommonMale.isProspectiveGraduate());
        assertFalse(qualificationExamCommonFemale.isProspectiveGraduate());
        assertFalse(qualificationExamMeisterMale.isProspectiveGraduate());
        assertFalse(qualificationExamMeisterFemale.isProspectiveGraduate());
        assertFalse(qualificationExamSocialMale.isProspectiveGraduate());
        assertFalse(qualificationExamSocialFemale.isProspectiveGraduate());
    }

    @Test
    public void isQualificationExam() {
        assertFalse(graduateCommonMale.isQualificationExam());
        assertFalse(graduateCommonFemale.isQualificationExam());
        assertFalse(graduateMeisterMale.isQualificationExam());
        assertFalse(graduateMeisterFemale.isQualificationExam());
        assertFalse(graduateSocialMale.isQualificationExam());
        assertFalse(graduateSocialFemale.isQualificationExam());

        assertFalse(prospectiveGraduateCommonMale.isQualificationExam());
        assertFalse(prospectiveGraduateCommonFemale.isQualificationExam());
        assertFalse(prospectiveGraduateMeisterMale.isQualificationExam());
        assertFalse(prospectiveGraduateMeisterFemale.isQualificationExam());
        assertFalse(prospectiveGraduateSocialMale.isQualificationExam());
        assertFalse(prospectiveGraduateSocialFemale.isQualificationExam());

        assertTrue(qualificationExamCommonMale.isQualificationExam());
        assertTrue(qualificationExamCommonFemale.isQualificationExam());
        assertTrue(qualificationExamMeisterMale.isQualificationExam());
        assertTrue(qualificationExamMeisterFemale.isQualificationExam());
        assertTrue(qualificationExamSocialMale.isQualificationExam());
        assertTrue(qualificationExamSocialFemale.isQualificationExam());
    }

    @Test
    public void isCommonApplicationType() {
        assertTrue(graduateCommonMale.isCommonApplicationType());
        assertTrue(graduateCommonFemale.isCommonApplicationType());
        assertFalse(graduateMeisterMale.isCommonApplicationType());
        assertFalse(graduateMeisterFemale.isCommonApplicationType());
        assertFalse(graduateSocialMale.isCommonApplicationType());
        assertFalse(graduateSocialFemale.isCommonApplicationType());

        assertTrue(prospectiveGraduateCommonMale.isCommonApplicationType());
        assertTrue(prospectiveGraduateCommonFemale.isCommonApplicationType());
        assertFalse(prospectiveGraduateMeisterMale.isCommonApplicationType());
        assertFalse(prospectiveGraduateMeisterFemale.isCommonApplicationType());
        assertFalse(prospectiveGraduateSocialMale.isCommonApplicationType());
        assertFalse(prospectiveGraduateSocialFemale.isCommonApplicationType());

        assertTrue(qualificationExamCommonMale.isCommonApplicationType());
        assertTrue(qualificationExamCommonFemale.isCommonApplicationType());
        assertFalse(qualificationExamMeisterMale.isCommonApplicationType());
        assertFalse(qualificationExamMeisterFemale.isCommonApplicationType());
        assertFalse(qualificationExamSocialMale.isCommonApplicationType());
        assertFalse(qualificationExamSocialFemale.isCommonApplicationType());
    }

    @Test
    public void isMeisterApplicationType() {
        assertFalse(graduateCommonMale.isMeisterApplicationType());
        assertFalse(graduateCommonFemale.isMeisterApplicationType());
        assertTrue(graduateMeisterMale.isMeisterApplicationType());
        assertTrue(graduateMeisterFemale.isMeisterApplicationType());
        assertFalse(graduateSocialMale.isMeisterApplicationType());
        assertFalse(graduateSocialFemale.isMeisterApplicationType());

        assertFalse(prospectiveGraduateCommonMale.isMeisterApplicationType());
        assertFalse(prospectiveGraduateCommonFemale.isMeisterApplicationType());
        assertTrue(prospectiveGraduateMeisterMale.isMeisterApplicationType());
        assertTrue(prospectiveGraduateMeisterFemale.isMeisterApplicationType());
        assertFalse(prospectiveGraduateSocialMale.isMeisterApplicationType());
        assertFalse(prospectiveGraduateSocialFemale.isMeisterApplicationType());

        assertFalse(qualificationExamCommonMale.isMeisterApplicationType());
        assertFalse(qualificationExamCommonFemale.isMeisterApplicationType());
        assertTrue(qualificationExamMeisterMale.isMeisterApplicationType());
        assertTrue(qualificationExamMeisterFemale.isMeisterApplicationType());
        assertFalse(qualificationExamSocialMale.isMeisterApplicationType());
        assertFalse(qualificationExamSocialFemale.isMeisterApplicationType());
    }

    @Test
    public void isSocialApplicationType() {
        assertFalse(graduateCommonMale.isSocialApplicationType());
        assertFalse(graduateCommonFemale.isSocialApplicationType());
        assertFalse(graduateMeisterMale.isSocialApplicationType());
        assertFalse(graduateMeisterFemale.isSocialApplicationType());
        assertTrue(graduateSocialMale.isSocialApplicationType());
        assertTrue(graduateSocialFemale.isSocialApplicationType());

        assertFalse(prospectiveGraduateCommonMale.isSocialApplicationType());
        assertFalse(prospectiveGraduateCommonFemale.isSocialApplicationType());
        assertFalse(prospectiveGraduateMeisterMale.isSocialApplicationType());
        assertFalse(prospectiveGraduateMeisterFemale.isSocialApplicationType());
        assertTrue(prospectiveGraduateSocialMale.isSocialApplicationType());
        assertTrue(prospectiveGraduateSocialFemale.isSocialApplicationType());

        assertFalse(qualificationExamCommonMale.isSocialApplicationType());
        assertFalse(qualificationExamCommonFemale.isSocialApplicationType());
        assertFalse(qualificationExamMeisterMale.isSocialApplicationType());
        assertFalse(qualificationExamMeisterFemale.isSocialApplicationType());
        assertTrue(qualificationExamSocialMale.isSocialApplicationType());
        assertTrue(qualificationExamSocialFemale.isSocialApplicationType());
    }

    @Test
    public void isMale() {
        assertTrue(graduateCommonMale.isMale());
        assertFalse(graduateCommonFemale.isMale());
        assertTrue(graduateMeisterMale.isMale());
        assertFalse(graduateMeisterFemale.isMale());
        assertTrue(graduateSocialMale.isMale());
        assertFalse(graduateSocialFemale.isMale());

        assertTrue(prospectiveGraduateCommonMale.isMale());
        assertFalse(prospectiveGraduateCommonFemale.isMale());
        assertTrue(prospectiveGraduateMeisterMale.isMale());
        assertFalse(prospectiveGraduateMeisterFemale.isMale());
        assertTrue(prospectiveGraduateSocialMale.isMale());
        assertFalse(prospectiveGraduateSocialFemale.isMale());

        assertTrue(qualificationExamCommonMale.isMale());
        assertFalse(qualificationExamCommonFemale.isMale());
        assertTrue(qualificationExamMeisterMale.isMale());
        assertFalse(qualificationExamMeisterFemale.isMale());
        assertTrue(qualificationExamSocialMale.isMale());
        assertFalse(qualificationExamSocialFemale.isMale());
    }

    @Test
    public void isFemale() {
        assertFalse(graduateCommonMale.isFemale());
        assertTrue(graduateCommonFemale.isFemale());
        assertFalse(graduateMeisterMale.isFemale());
        assertTrue(graduateMeisterFemale.isFemale());
        assertFalse(graduateSocialMale.isFemale());
        assertTrue(graduateSocialFemale.isFemale());

        assertFalse(prospectiveGraduateCommonMale.isFemale());
        assertTrue(prospectiveGraduateCommonFemale.isFemale());
        assertFalse(prospectiveGraduateMeisterMale.isFemale());
        assertTrue(prospectiveGraduateMeisterFemale.isFemale());
        assertFalse(prospectiveGraduateSocialMale.isFemale());
        assertTrue(prospectiveGraduateSocialFemale.isFemale());

        assertFalse(qualificationExamCommonMale.isFemale());
        assertTrue(qualificationExamCommonFemale.isFemale());
        assertFalse(qualificationExamMeisterMale.isFemale());
        assertTrue(qualificationExamMeisterFemale.isFemale());
        assertFalse(qualificationExamSocialMale.isFemale());
        assertTrue(qualificationExamSocialFemale.isFemale());
    }

    @Test
    public void isBasicLiving() {
        assertTrue(basicLiving.isBasicLiving());
        assertFalse(fromNorth.isBasicLiving());
        assertFalse(lowestIncome.isBasicLiving());
        assertFalse(multicultural.isBasicLiving());
        assertFalse(oneParent.isBasicLiving());
        assertFalse(teenHouseholder.isBasicLiving());
        assertFalse(privilegedAdmission.isBasicLiving());
        assertFalse(nationalMerit.isBasicLiving());
    }

    @Test
    public void isFromNorth() {
        assertFalse(basicLiving.isFromNorth());
        assertTrue(fromNorth.isFromNorth());
        assertFalse(lowestIncome.isFromNorth());
        assertFalse(multicultural.isFromNorth());
        assertFalse(oneParent.isFromNorth());
        assertFalse(teenHouseholder.isFromNorth());
        assertFalse(privilegedAdmission.isFromNorth());
        assertFalse(nationalMerit.isFromNorth());
    }

    @Test
    public void isLowestIncome() {
        assertFalse(basicLiving.isLowestIncome());
        assertFalse(fromNorth.isLowestIncome());
        assertTrue(lowestIncome.isLowestIncome());
        assertFalse(multicultural.isLowestIncome());
        assertFalse(oneParent.isLowestIncome());
        assertFalse(teenHouseholder.isLowestIncome());
        assertFalse(privilegedAdmission.isLowestIncome());
        assertFalse(nationalMerit.isLowestIncome());
    }

    @Test
    public void isMulticultural() {
        assertFalse(basicLiving.isMulticultural());
        assertFalse(fromNorth.isMulticultural());
        assertFalse(lowestIncome.isMulticultural());
        assertTrue(multicultural.isMulticultural());
        assertFalse(oneParent.isMulticultural());
        assertFalse(teenHouseholder.isMulticultural());
        assertFalse(privilegedAdmission.isMulticultural());
        assertFalse(nationalMerit.isMulticultural());
    }

    @Test
    public void isOneParent() {
        assertFalse(basicLiving.isOneParent());
        assertFalse(fromNorth.isOneParent());
        assertFalse(lowestIncome.isOneParent());
        assertFalse(multicultural.isOneParent());
        assertTrue(oneParent.isOneParent());
        assertFalse(teenHouseholder.isOneParent());
        assertFalse(privilegedAdmission.isOneParent());
        assertFalse(nationalMerit.isOneParent());
    }

    @Test
    public void isTeenHouseholder() {
        assertFalse(basicLiving.isTeenHouseholder());
        assertFalse(fromNorth.isTeenHouseholder());
        assertFalse(lowestIncome.isTeenHouseholder());
        assertFalse(multicultural.isTeenHouseholder());
        assertFalse(oneParent.isTeenHouseholder());
        assertTrue(teenHouseholder.isTeenHouseholder());
        assertFalse(privilegedAdmission.isTeenHouseholder());
        assertFalse(nationalMerit.isTeenHouseholder());
    }

    @Test
    public void isPrivilegedAdmission() {
        assertFalse(basicLiving.isPrivilegedAdmission());
        assertFalse(fromNorth.isPrivilegedAdmission());
        assertFalse(lowestIncome.isPrivilegedAdmission());
        assertFalse(multicultural.isPrivilegedAdmission());
        assertFalse(oneParent.isPrivilegedAdmission());
        assertFalse(teenHouseholder.isPrivilegedAdmission());
        assertTrue(privilegedAdmission.isPrivilegedAdmission());
        assertFalse(nationalMerit.isPrivilegedAdmission());
    }

    @Test
    public void isNationalMerit() {
        assertFalse(basicLiving.isNationalMerit());
        assertFalse(fromNorth.isNationalMerit());
        assertFalse(lowestIncome.isNationalMerit());
        assertFalse(multicultural.isNationalMerit());
        assertFalse(oneParent.isNationalMerit());
        assertFalse(teenHouseholder.isNationalMerit());
        assertFalse(privilegedAdmission.isNationalMerit());
        assertTrue(nationalMerit.isNationalMerit());
    }

    @Test
    public void isHomeTelEmpty() {
        assertFalse(graduateCommonMale.isHomeTelEmpty());
        assertFalse(graduateCommonFemale.isHomeTelEmpty());
        assertFalse(graduateMeisterMale.isHomeTelEmpty());
        assertFalse(graduateMeisterFemale.isHomeTelEmpty());
        assertFalse(graduateSocialMale.isHomeTelEmpty());
        assertFalse(graduateSocialFemale.isHomeTelEmpty());

        assertFalse(prospectiveGraduateCommonMale.isHomeTelEmpty());
        assertFalse(prospectiveGraduateCommonFemale.isHomeTelEmpty());
        assertFalse(prospectiveGraduateMeisterMale.isHomeTelEmpty());
        assertFalse(prospectiveGraduateMeisterFemale.isHomeTelEmpty());
        assertFalse(prospectiveGraduateSocialMale.isHomeTelEmpty());
        assertFalse(prospectiveGraduateSocialFemale.isHomeTelEmpty());

        assertFalse(qualificationExamCommonMale.isHomeTelEmpty());
        assertFalse(qualificationExamCommonFemale.isHomeTelEmpty());
        assertFalse(qualificationExamMeisterMale.isHomeTelEmpty());
        assertFalse(qualificationExamMeisterFemale.isHomeTelEmpty());
        assertFalse(qualificationExamSocialMale.isHomeTelEmpty());
        assertFalse(qualificationExamSocialFemale.isHomeTelEmpty());

        assertTrue(basicLiving.isHomeTelEmpty());
        assertTrue(fromNorth.isHomeTelEmpty());
        assertTrue(lowestIncome.isHomeTelEmpty());
        assertTrue(multicultural.isHomeTelEmpty());
        assertTrue(oneParent.isHomeTelEmpty());
        assertTrue(teenHouseholder.isHomeTelEmpty());
        assertTrue(privilegedAdmission.isHomeTelEmpty());
        assertTrue(nationalMerit.isHomeTelEmpty());
    }

    @Test
    public void isEducationalStatusEmpty() {
        assertFalse(graduateCommonMale.isEducationalStatusEmpty());
        assertFalse(graduateCommonFemale.isEducationalStatusEmpty());
        assertFalse(graduateMeisterMale.isEducationalStatusEmpty());
        assertFalse(graduateMeisterFemale.isEducationalStatusEmpty());
        assertFalse(graduateSocialMale.isEducationalStatusEmpty());
        assertFalse(graduateSocialFemale.isEducationalStatusEmpty());

        assertFalse(prospectiveGraduateCommonMale.isEducationalStatusEmpty());
        assertFalse(prospectiveGraduateCommonFemale.isEducationalStatusEmpty());
        assertFalse(prospectiveGraduateMeisterMale.isEducationalStatusEmpty());
        assertFalse(prospectiveGraduateMeisterFemale.isEducationalStatusEmpty());
        assertFalse(prospectiveGraduateSocialMale.isEducationalStatusEmpty());
        assertFalse(prospectiveGraduateSocialFemale.isEducationalStatusEmpty());

        assertFalse(qualificationExamCommonMale.isEducationalStatusEmpty());
        assertFalse(qualificationExamCommonFemale.isEducationalStatusEmpty());
        assertFalse(qualificationExamMeisterMale.isEducationalStatusEmpty());
        assertFalse(qualificationExamMeisterFemale.isEducationalStatusEmpty());
        assertFalse(qualificationExamSocialMale.isEducationalStatusEmpty());
        assertFalse(qualificationExamSocialFemale.isEducationalStatusEmpty());

        assertTrue(basicLiving.isEducationalStatusEmpty());
        assertTrue(fromNorth.isEducationalStatusEmpty());
        assertTrue(lowestIncome.isEducationalStatusEmpty());
        assertTrue(multicultural.isEducationalStatusEmpty());
        assertTrue(oneParent.isEducationalStatusEmpty());
        assertTrue(teenHouseholder.isEducationalStatusEmpty());
        assertTrue(privilegedAdmission.isEducationalStatusEmpty());
        assertTrue(nationalMerit.isEducationalStatusEmpty());
    }

    @Test
    public void hasSchoolInfo() {
        assertTrue(graduateCommonMale.hasSchoolInfo());
        assertTrue(graduateCommonFemale.hasSchoolInfo());
        assertTrue(graduateMeisterMale.hasSchoolInfo());
        assertTrue(graduateMeisterFemale.hasSchoolInfo());
        assertTrue(graduateSocialMale.hasSchoolInfo());
        assertTrue(graduateSocialFemale.hasSchoolInfo());

        assertTrue(prospectiveGraduateCommonMale.hasSchoolInfo());
        assertTrue(prospectiveGraduateCommonFemale.hasSchoolInfo());
        assertTrue(prospectiveGraduateMeisterMale.hasSchoolInfo());
        assertTrue(prospectiveGraduateMeisterFemale.hasSchoolInfo());
        assertTrue(prospectiveGraduateSocialMale.hasSchoolInfo());
        assertTrue(prospectiveGraduateSocialFemale.hasSchoolInfo());

        assertFalse(qualificationExamCommonMale.hasSchoolInfo());
        assertFalse(qualificationExamCommonFemale.hasSchoolInfo());
        assertFalse(qualificationExamMeisterMale.hasSchoolInfo());
        assertFalse(qualificationExamMeisterFemale.hasSchoolInfo());
        assertFalse(qualificationExamSocialMale.hasSchoolInfo());
        assertFalse(qualificationExamSocialFemale.hasSchoolInfo());

        assertFalse(basicLiving.hasSchoolInfo());
        assertFalse(fromNorth.hasSchoolInfo());
        assertFalse(lowestIncome.hasSchoolInfo());
        assertFalse(multicultural.hasSchoolInfo());
        assertFalse(oneParent.hasSchoolInfo());
        assertFalse(teenHouseholder.hasSchoolInfo());
        assertFalse(privilegedAdmission.hasSchoolInfo());
        assertFalse(nationalMerit.hasSchoolInfo());
    }

    @Test
    public void getApplicationType() {
        assertEquals(graduateCommonMale.getApplicationType(), "COMMON");
        assertEquals(graduateCommonFemale.getApplicationType(), "COMMON");
        assertEquals(graduateMeisterMale.getApplicationType(), "MEISTER");
        assertEquals(graduateMeisterFemale.getApplicationType(), "MEISTER");
        assertEquals(graduateSocialMale.getApplicationType(), "SOCIAL");
        assertEquals(graduateSocialFemale.getApplicationType(), "SOCIAL");

        assertEquals(prospectiveGraduateCommonMale.getApplicationType(), "COMMON");
        assertEquals(prospectiveGraduateCommonFemale.getApplicationType(), "COMMON");
        assertEquals(prospectiveGraduateMeisterMale.getApplicationType(), "MEISTER");
        assertEquals(prospectiveGraduateMeisterFemale.getApplicationType(), "MEISTER");
        assertEquals(prospectiveGraduateSocialMale.getApplicationType(), "SOCIAL");
        assertEquals(prospectiveGraduateSocialFemale.getApplicationType(), "SOCIAL");

        assertEquals(qualificationExamCommonMale.getApplicationType(), "COMMON");
        assertEquals(qualificationExamCommonFemale.getApplicationType(), "COMMON");
        assertEquals(qualificationExamMeisterMale.getApplicationType(), "MEISTER");
        assertEquals(qualificationExamMeisterFemale.getApplicationType(), "MEISTER");
        assertEquals(qualificationExamSocialMale.getApplicationType(), "SOCIAL");
        assertEquals(qualificationExamSocialFemale.getApplicationType(), "SOCIAL");
    }

    @Test
    public void getSex() {
        assertEquals(graduateCommonMale.getSex(), Sex.MALE);
        assertEquals(graduateCommonFemale.getSex(), Sex.FEMALE);
        assertEquals(graduateMeisterMale.getSex(), Sex.MALE);
        assertEquals(graduateMeisterFemale.getSex(), Sex.FEMALE);
        assertEquals(graduateSocialMale.getSex(), Sex.MALE);
        assertEquals(graduateSocialFemale.getSex(), Sex.FEMALE);

        assertEquals(prospectiveGraduateCommonMale.getSex(), Sex.MALE);
        assertEquals(prospectiveGraduateCommonFemale.getSex(), Sex.FEMALE);
        assertEquals(prospectiveGraduateMeisterMale.getSex(), Sex.MALE);
        assertEquals(prospectiveGraduateMeisterFemale.getSex(), Sex.FEMALE);
        assertEquals(prospectiveGraduateSocialMale.getSex(), Sex.MALE);
        assertEquals(prospectiveGraduateSocialFemale.getSex(), Sex.FEMALE);

        assertEquals(qualificationExamCommonMale.getSex(), Sex.MALE);
        assertEquals(qualificationExamCommonFemale.getSex(), Sex.FEMALE);
        assertEquals(qualificationExamMeisterMale.getSex(), Sex.MALE);
        assertEquals(qualificationExamMeisterFemale.getSex(), Sex.FEMALE);
        assertEquals(qualificationExamSocialMale.getSex(), Sex.MALE);
        assertEquals(qualificationExamSocialFemale.getSex(), Sex.FEMALE);
    }

    @Test
    public void getPostCode() {
        assertEquals(graduateCommonMale.getPostCode(), "12345");
        assertEquals(graduateCommonFemale.getPostCode(), "12345");
        assertEquals(graduateMeisterMale.getPostCode(), "12345");
        assertEquals(graduateMeisterFemale.getPostCode(), "12345");
        assertEquals(graduateSocialMale.getPostCode(), "12345");
        assertEquals(graduateSocialFemale.getPostCode(), "12345");

        assertEquals(prospectiveGraduateCommonMale.getPostCode(), "12345");
        assertEquals(prospectiveGraduateCommonFemale.getPostCode(), "12345");
        assertEquals(prospectiveGraduateMeisterMale.getPostCode(), "12345");
        assertEquals(prospectiveGraduateMeisterFemale.getPostCode(), "12345");
        assertEquals(prospectiveGraduateSocialMale.getPostCode(), "12345");
        assertEquals(prospectiveGraduateSocialFemale.getPostCode(), "12345");

        assertEquals(qualificationExamCommonMale.getPostCode(), "12345");
        assertEquals(qualificationExamCommonFemale.getPostCode(), "12345");
        assertEquals(qualificationExamMeisterMale.getPostCode(), "12345");
        assertEquals(qualificationExamMeisterFemale.getPostCode(), "12345");
        assertEquals(qualificationExamSocialMale.getPostCode(), "12345");
        assertEquals(qualificationExamSocialFemale.getPostCode(), "12345");
    }

    @Test
    public void isGraduation() {
        assertTrue(graduateCommonMale.isGraduation());
        assertTrue(graduateCommonFemale.isGraduation());
        assertTrue(graduateMeisterMale.isGraduation());
        assertTrue(graduateMeisterFemale.isGraduation());
        assertTrue(graduateSocialMale.isGraduation());
        assertTrue(graduateSocialFemale.isGraduation());

        assertTrue(prospectiveGraduateCommonMale.isGraduation());
        assertTrue(prospectiveGraduateCommonFemale.isGraduation());
        assertTrue(prospectiveGraduateMeisterMale.isGraduation());
        assertTrue(prospectiveGraduateMeisterFemale.isGraduation());
        assertTrue(prospectiveGraduateSocialMale.isGraduation());
        assertTrue(prospectiveGraduateSocialFemale.isGraduation());

        assertFalse(qualificationExamCommonMale.isGraduation());
        assertFalse(qualificationExamCommonFemale.isGraduation());
        assertFalse(qualificationExamMeisterMale.isGraduation());
        assertFalse(qualificationExamMeisterFemale.isGraduation());
        assertFalse(qualificationExamSocialMale.isGraduation());
        assertFalse(qualificationExamSocialFemale.isGraduation());
    }

    @Test
    public void getApplicationRemark() {
        assertEquals(basicLiving.getApplicationRemark(), ApplicationRemark.BASIC_LIVING);
        assertEquals(fromNorth.getApplicationRemark(), ApplicationRemark.FROM_NORTH);
        assertEquals(lowestIncome.getApplicationRemark(), ApplicationRemark.LOWEST_INCOME);
        assertEquals(multicultural.getApplicationRemark(), ApplicationRemark.MULTICULTURAL);
        assertEquals(oneParent.getApplicationRemark(), ApplicationRemark.ONE_PARENT);
        assertEquals(teenHouseholder.getApplicationRemark(), ApplicationRemark.TEEN_HOUSEHOLDER);
        assertEquals(privilegedAdmission.getApplicationRemark(), ApplicationRemark.PRIVILEGED_ADMISSION);
        assertEquals(nationalMerit.getApplicationRemark(), ApplicationRemark.NATIONAL_MERIT);
    }

}
