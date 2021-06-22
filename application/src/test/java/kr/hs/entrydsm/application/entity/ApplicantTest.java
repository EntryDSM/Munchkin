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
    }

    @Test
    public void isFromNorth() {
        assertFalse(basicLiving.isFromNorth());
        assertTrue(fromNorth.isFromNorth());
        assertFalse(lowestIncome.isFromNorth());
        assertFalse(multicultural.isFromNorth());
        assertFalse(oneParent.isFromNorth());
        assertFalse(teenHouseholder.isFromNorth());
    }

    @Test
    public void isLowestIncome() {
        assertFalse(basicLiving.isLowestIncome());
        assertFalse(fromNorth.isLowestIncome());
        assertTrue(lowestIncome.isLowestIncome());
        assertFalse(multicultural.isLowestIncome());
        assertFalse(oneParent.isLowestIncome());
        assertFalse(teenHouseholder.isLowestIncome());
    }

    @Test
    public void isMulticultural() {
        assertFalse(basicLiving.isMulticultural());
        assertFalse(fromNorth.isMulticultural());
        assertFalse(lowestIncome.isMulticultural());
        assertTrue(multicultural.isMulticultural());
        assertFalse(oneParent.isMulticultural());
        assertFalse(teenHouseholder.isMulticultural());
    }

    @Test
    public void isOneParent() {
        assertFalse(basicLiving.isOneParent());
        assertFalse(fromNorth.isOneParent());
        assertFalse(lowestIncome.isOneParent());
        assertFalse(multicultural.isOneParent());
        assertTrue(oneParent.isOneParent());
        assertFalse(teenHouseholder.isOneParent());
    }

    @Test
    public void isTeenHouseholder() {
        assertFalse(basicLiving.isTeenHouseholder());
        assertFalse(fromNorth.isTeenHouseholder());
        assertFalse(lowestIncome.isTeenHouseholder());
        assertFalse(multicultural.isTeenHouseholder());
        assertFalse(oneParent.isTeenHouseholder());
        assertTrue(teenHouseholder.isTeenHouseholder());
    }

}
