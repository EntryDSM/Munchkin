package kr.hs.entrydsm.application.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class ApplicantTest {

    private final Applicant applicant = Applicant.builder()
            .receiptCode(1)
            .telephoneNumber("01012345678")
            .applicationType(ApplicationType.COMMON)
            .applicationRemark(ApplicationRemark.BASIC_LIVING)
            .educationalStatus(EducationalStatus.GRADUATE)
            .isDaejeon(true)
            .name("김빵빵")
            .sex(Sex.MALE)
            .birthday(LocalDate.of(2004, 3, 14))
            .parentName("김빵붕")
            .parentTel("01087654321")
            .address("대한민국 어딘가")
            .postCode("12345")
            .photoFileName("사진 이름")
            .homeTel("0421234567")
            .selfIntroduce("자기소개")
            .studyPlan("학업계획")
            .build();

    @Test
    public void isApplicationTypeCommonTrue() {
        assertThat(applicant.isCommonApplicationType()).isTrue();
    }

    @Test
    public void isApplicationTypeMeisterFalse() {
        assertThat(applicant.isMeisterApplicationType()).isFalse();
    }

    @Test
    public void isApplicationTypeSocialFalse() {
        assertThat(applicant.isSocialApplicationType()).isFalse();
    }

    @Test
    public void isQualificationExamFalse() {
        assertThat(applicant.isQualificationExam()).isFalse();
    }

    @Test
    public void isGraduateTrue() {
        assertThat(applicant.isGraduate()).isTrue();
    }

    @Test
    public void isProspectiveGraduate() {
        assertThat(applicant.isProspectiveGraduate()).isFalse();
    }

    @Test
    public void isNationalMeritFalse() {
        assertThat(applicant.isNationalMerit()).isFalse();
    }

    @Test
    public void isPrivilegedAdmission() {
        assertThat(applicant.isPrivilegedAdmission()).isFalse();
    }

    @Test
    public void isSexTrue() {
        assertThat(applicant.isMale()).isTrue();
    }

    @Test
    public void isSexFalse() {
        assertThat(applicant.isFemale()).isFalse();
    }

    @Test
    public void isSchoolInfoTrue() {
        assertThat(applicant.hasSchoolInfo()).isTrue();
    }

    @Test
    public void isHomeTelEmptyFalse() {
        assertThat(applicant.isHomeTelEmpty()).isFalse();
    }



}
