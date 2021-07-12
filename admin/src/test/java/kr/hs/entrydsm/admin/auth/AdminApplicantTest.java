package kr.hs.entrydsm.admin.auth;

import kr.hs.entrydsm.admin.usecase.applicant.ApplicantService;
import kr.hs.entrydsm.admin.usecase.applicant.ApplicantServiceManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("admin-applicant")
@SpringBootTest(classes = ApplicantServiceManager.class)
public class AdminApplicantTest extends AdminApplicantBaseTest {

    @MockBean
    ApplicantService applicantService;

    @Test
    public void applicant() {
        assertTrue(!APPLICANT.getIsDaejeon());
        assertFalse(APPLICANT.getEmail().isBlank());
        assertFalse(APPLICANT.getName().isBlank());
        assertFalse(APPLICANT.getPhotoFileName().isBlank());
        assertFalse(APPLICANT.getBirthDate().toString().isBlank());
        assertTrue(APPLICANT.getIsSubmit());
        assertFalse(APPLICANT.getIsPrintedArrived());
        assertTrue(APPLICANT.getDetailAddress() == null);
        assertFalse(APPLICANT.getAddress().isBlank());
        assertFalse(APPLICANT.getTelephoneNumber().isBlank());
        assertFalse(APPLICANT.getParentTel().isBlank());
        assertFalse(APPLICANT.getHomeTel().isBlank());
        assertFalse(APPLICANT.getSelfIntroduce().isBlank());
        assertFalse(APPLICANT.getStudyPlan().isBlank());
        assertTrue(APPLICANT.getDistance() == null);
        assertTrue(APPLICANT.getEducationalStatus().equals("PROSPECTIVE_GRADUATE"));
        assertTrue(APPLICANT.getApplicationType().equals("COMMON"));
        assertEquals(APPLICANT.getExamCode(), "12036");
        assertEquals(APPLICANT.getReceiptCode(), 123456L);
    }

    @Test
    public void save_exam_code_user_response() {
        assertTrue(SAVE_EXAM_CODE_USER_RESPONSE.getIsDaejeon()==true);
        assertFalse(SAVE_EXAM_CODE_USER_RESPONSE.getApplicationType().equals("COMMON"));
        assertFalse(SAVE_EXAM_CODE_USER_RESPONSE.getExamCode().isBlank());
        assertTrue(SAVE_EXAM_CODE_USER_RESPONSE.getAddress().equals("경상북도 상주시 경상대로 314"));
        assertTrue(SAVE_EXAM_CODE_USER_RESPONSE.getReceiptCode() == 123456);
        assertTrue(SAVE_EXAM_CODE_USER_RESPONSE.getDistance() == 0);

        SAVE_EXAM_CODE_USER_RESPONSE.updateExamCode("123456");
        SAVE_EXAM_CODE_USER_RESPONSE.updateDistance(123.123);
    }

    @Test
    public void application_status() {
        assertEquals(APPLICATION_STATUS.getCommonScores().get(0), BigDecimal.valueOf(123.5));
        assertEquals(APPLICATION_STATUS.getMeisterScores().get(0), BigDecimal.valueOf(121.2));
        assertEquals(APPLICATION_STATUS.getSpecialScores().get(0), BigDecimal.valueOf(122.5));
    }

    @Test
    public void common_score() {
        assertTrue(COMMON_SCORE.getScore_79() != 0);
        assertTrue(COMMON_SCORE.getScore80_92() == 0);
        assertTrue(COMMON_SCORE.getScore93_105() != 0);
        assertTrue(COMMON_SCORE.getScore106_118() != 0);
        assertTrue(COMMON_SCORE.getScore119_131() != 0);
        assertTrue(COMMON_SCORE.getScore132_144() != 0);
        assertTrue(COMMON_SCORE.getScore145_157() != 0);
        assertTrue(COMMON_SCORE.getScore158_170() != 0);

        COMMON_SCORE.addScore(80);
        COMMON_SCORE.addScore(90);
        COMMON_SCORE.addScore(100);
        COMMON_SCORE.addScore(110);
        COMMON_SCORE.addScore(120);
        COMMON_SCORE.addScore(130);
        COMMON_SCORE.addScore(140);
        COMMON_SCORE.addScore(150);
    }

    @Test
    public void social_socre() {
        assertEquals(MEISTER_SCORE.getScore_19(), 1);
        assertEquals(MEISTER_SCORE.getScore20_32(), 3);
        assertEquals(MEISTER_SCORE.getScore33_45(), 0);
        assertEquals(MEISTER_SCORE.getScore46_58(), 5);
        assertEquals(MEISTER_SCORE.getScore59_71(), 9);
        assertEquals(MEISTER_SCORE.getScore72_84(), 9);
        assertEquals(MEISTER_SCORE.getScore85_97(), 7);
        assertEquals(MEISTER_SCORE.getScore98_110(), 9);

        MEISTER_SCORE.addScore(10);
        MEISTER_SCORE.addScore(20.5);
        MEISTER_SCORE.addScore(31);
        MEISTER_SCORE.addScore(41);
        MEISTER_SCORE.addScore(51);
        MEISTER_SCORE.addScore(61);
        MEISTER_SCORE.addScore(71);
        MEISTER_SCORE.addScore(81);
    }

    @Test
    public void coordinate() {
        assertFalse(COORDINATE.getLat().isBlank());
        assertFalse(COORDINATE.getLon().isBlank());
        assertFalse(COORDINATE.getCityDo().isBlank());
        assertFalse(COORDINATE.getGuGun().isBlank());
        assertFalse(COORDINATE.getEupMyun().isBlank());
        assertFalse(COORDINATE.getLegalDong().isBlank());
        assertFalse(COORDINATE.getAdminDong().isBlank());
        assertFalse(COORDINATE.getRi().isBlank());
        assertFalse(COORDINATE.getBunji().isBlank());
        assertFalse(COORDINATE.getBuildingName().isBlank());
        assertFalse(COORDINATE.getBuildingDong().isBlank());
        assertFalse(COORDINATE.getLatEntr().isBlank());
        assertFalse(COORDINATE.getLonEntr().isBlank());
        assertFalse(COORDINATE.getNewBuildingIndex().isBlank());
        assertFalse(COORDINATE.getNewRoadName().isBlank());
        assertFalse(COORDINATE.getZipcode().isBlank());
    }

    @Test
    public void evaluation() {
        assertFalse(EVALUATION.getVolunteerTime().toString().isBlank());
        assertFalse(EVALUATION.getConversionScore().toString().isBlank());
        assertFalse(EVALUATION.getDayAbsenceCount().toString().isBlank());
        assertFalse(EVALUATION.getLectureAbsenceCount().toString().isBlank());
        assertFalse(EVALUATION.getEarlyLeaveCount().toString().isBlank());
        assertFalse(EVALUATION.getLatenessCount().toString().isBlank());
        assertFalse(EVALUATION.getSelfIntroduce().isBlank());
        assertFalse(EVALUATION.getStudyPlan().isBlank());
        assertFalse(EVALUATION.getAverageScore().toString().isBlank());
    }

    @Test
    public void personalData() {
        assertTrue(PERSONAL_DATA.getPhotoFileName() == null);
        assertFalse(PERSONAL_DATA.getName().isBlank());
        assertFalse(PERSONAL_DATA.getEmail().isBlank());
        assertFalse(PERSONAL_DATA.getBirthDate().toString().isBlank());
        assertFalse(PERSONAL_DATA.getSchoolName().isBlank());
        assertFalse(!PERSONAL_DATA.isGraduated());
        assertFalse(PERSONAL_DATA.getEducationalStatus().isBlank());
        assertFalse(PERSONAL_DATA.getApplicationType().isBlank());
        assertFalse(PERSONAL_DATA.getAddress().isBlank());
        assertTrue(PERSONAL_DATA.getDetailAddress() == null);
        assertFalse(PERSONAL_DATA.getTelephoneNumber().isBlank());
        assertFalse(PERSONAL_DATA.getParentTel().isBlank());
        assertFalse(PERSONAL_DATA.getSchoolName().isBlank());
        assertFalse(PERSONAL_DATA.getHomeTel().isBlank());
    }

    @Test
    public void not_submit_applicant() {
        assertTrue(NOT_SUBMIT_APPLICANT.getApplicantTel().equals("010-0000-0000"));
        assertTrue(NOT_SUBMIT_APPLICANT.getParentTel().equals("010-0000-0000"));
        assertTrue(NOT_SUBMIT_APPLICANT.getHomeTel().equals("010-0000-0000"));
        assertTrue(NOT_SUBMIT_APPLICANT.getSchoolTel().equals("010-0000-0000"));
    }

    @Test
    public void properties() {
        assertFalse(PROPERTIES.getTotalDistance() == 0);
        assertFalse(PROPERTIES.getTotalTime() == 0);
        assertFalse(PROPERTIES.getTotalFare() == 0);
        assertFalse(PROPERTIES.getTaxiFare() == 0);
    }

    @Test
    public void route_body() {
        assertFalse(ROUTE_BODY.getEndX() == 0);
        assertFalse(ROUTE_BODY.getEndY() == 0);
        assertFalse(ROUTE_BODY.getStartX() == 0);
        assertFalse(ROUTE_BODY.getStartY() == 0);
        assertFalse(ROUTE_BODY.getTotalValue() == 0);
    }

    @Test
    public void status() {
        assertTrue(STATUS.isPrintedArrived());
        assertTrue(STATUS.isSubmit());
    }

    @Test
    public void route_guidance_request() {
        assertTrue(ROUTE_GUIDANCE_REQUEST.getLng() == 0);
        assertTrue(ROUTE_GUIDANCE_REQUEST.getLat() == 0);
        assertTrue(ROUTE_GUIDANCE_REQUEST.getStartX() == 0);
        assertTrue(ROUTE_GUIDANCE_REQUEST.getStartY() == 0);
        assertTrue(ROUTE_GUIDANCE_REQUEST.getTotalValue() == 0);
    }

    @Test
    public void changeIsPrintedArrived() {
        applicantService.changePrintArrivedOrNot(APPLICANT.getReceiptCode(), true);
    }

    @Test
    public void getDetail() {
        applicantService.getDetailApplicantInfo(123456);
    }

    @Test
    public void getApplciants() {
        applicantService.getApplicants(PAGEABLE, 123456L, true, false, null, null, true, false, false, true);
    }

}
