package kr.hs.entrydsm.admin.auth;

import kr.hs.entrydsm.admin.usecase.applicant.ApplicantService;
import kr.hs.entrydsm.admin.usecase.applicant.ApplicantServiceManager;
import kr.hs.entrydsm.admin.usecase.dto.SpecialScore;
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
        assertTrue(APPLICANT.isDaejeon()==false);

        assertEquals(APPLICANT.isGraduated(), false);
        assertTrue(APPLICANT.getEducationalStatus().equals("PROSPECTIVE_GRADUATE"));
        assertTrue(APPLICANT.getApplicationType().equals("COMMON"));
        assertEquals(APPLICANT.getExamCode(), "12036");
        assertEquals(APPLICANT.getReceiptCode(), 123456L);
    }

    @Test
    public void save_exam_code_user_response() {
        assertTrue(SAVE_EXAM_CODE_USER_RESPONSE.isDaejeon()==true);
        assertFalse(SAVE_EXAM_CODE_USER_RESPONSE.getApplicationType().equals("COMMON"));
    }

    @Test
    public void application_status() {
        assertEquals(APPLICATION_STATUS.getCommonScore().get(0), BigDecimal.valueOf(123.5));
        assertEquals(APPLICATION_STATUS.getMeisterScore().get(0), BigDecimal.valueOf(121.2));
        assertEquals(APPLICATION_STATUS.getSpecialScore().get(0), BigDecimal.valueOf(122.5));
    }

    @Test
    public void meister_socre() {
        assertEquals(MEISTER_SCORE.getScore_20(), 1);
        assertEquals(MEISTER_SCORE.getScore21_30(), 3);
        assertEquals(MEISTER_SCORE.getScore31_40(), 0);
        assertEquals(MEISTER_SCORE.getScore41_50(), 5);
        assertEquals(MEISTER_SCORE.getScore51_60(), 9);
        assertEquals(MEISTER_SCORE.getScore61_70(), 9);
        assertEquals(MEISTER_SCORE.getScore71_80(), 7);
        assertEquals(MEISTER_SCORE.getScore81_90(), 9);
    }

    @Test
    public void changeIsPrintedArrived() {
        applicantService.changeIsPrintedArrived(APPLICANT.getReceiptCode(), true);
    }

    @Test
    public void saveExamCode() throws Exception {
        applicantService.saveExamCode();
    }

}
