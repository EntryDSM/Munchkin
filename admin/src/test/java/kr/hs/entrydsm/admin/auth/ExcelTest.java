package kr.hs.entrydsm.admin.auth;

import kr.hs.entrydsm.admin.usecase.excel.ExcelService;
import kr.hs.entrydsm.admin.usecase.excel.ExcelServiceManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("admin-excel")
@SpringBootTest(classes = ExcelServiceManager.class)
public class ExcelTest extends ExcelBaseTest {

    @MockBean
    ExcelService excelService;

    @Test
    public void excel_user() {
        assertTrue(EXCEL_USER.getApplicationType().equals("COMMON"));
        assertTrue(EXCEL_USER.getExamCode().equals("123456"));
        assertEquals(EXCEL_USER.getReceiptCode(), 123456L);
        assertEquals(EXCEL_USER.getApplicationRemrk(), null);
        assertTrue(EXCEL_USER.getArea().equals("대전"));
        assertEquals(EXCEL_USER.getName(), "홍길동");
        assertEquals(EXCEL_USER.getParentName(),"홍상순");
        assertEquals(EXCEL_USER.getBirthDay(), "2000-00-00");
        assertEquals(EXCEL_USER.getTelephoneNumber(), "010-0000-0000");
        assertEquals(EXCEL_USER.getSex(), "남자");
        assertTrue(!EXCEL_USER.getSelfIntroduce().isBlank());
        assertTrue(!EXCEL_USER.getStudyPlan().isBlank());
        assertEquals(EXCEL_USER.getParentTel(), "010-0000-0000");
        assertFalse(EXCEL_USER.getAddress().isBlank());
        assertFalse(EXCEL_USER.getEducationalStatus().isBlank());
    }

    @Test
    public void excel_user_score() {
        assertFalse(EXCEL_USER_SCORE.getYearOfGraduation().isBlank());
        assertFalse(EXCEL_USER_SCORE.getMiddleSchoolStudentNumber().isBlank());
        assertFalse(EXCEL_USER_SCORE.getMiddleSchool().isBlank());
        assertFalse(EXCEL_USER_SCORE.getKoreanGrade().isBlank());
        assertFalse(EXCEL_USER_SCORE.getSocialGrade().isBlank());
        assertFalse(EXCEL_USER_SCORE.getHistoryGrade().isBlank());
        assertFalse(EXCEL_USER_SCORE.getMathGrade().isBlank());
        assertFalse(EXCEL_USER_SCORE.getScienceGrade().isBlank());
        assertFalse(EXCEL_USER_SCORE.getEnglishGrade().isBlank());
        assertFalse(EXCEL_USER_SCORE.getTechAndHomeGrade().isBlank());
        assertFalse(EXCEL_USER_SCORE.getTotalFirstGradeScores().isBlank());
        assertFalse(EXCEL_USER_SCORE.getTotalSecondGradeScores().isBlank());
        assertFalse(EXCEL_USER_SCORE.getTotalThirdGradeScores().isBlank());
        assertFalse(EXCEL_USER_SCORE.getVolunteerScore().isBlank());
        assertFalse(EXCEL_USER_SCORE.getVolunteerTime().isBlank());
        assertFalse(EXCEL_USER_SCORE.getDayAbsenceCount().isBlank());
        assertFalse(EXCEL_USER_SCORE.getLectureAbsenceCount().isBlank());
        assertFalse(EXCEL_USER_SCORE.getLatenessCount().isBlank());
        assertFalse(EXCEL_USER_SCORE.getEarlyLeaveCount().isBlank());
        assertFalse(EXCEL_USER_SCORE.getConversionScore().isBlank());
        assertFalse(EXCEL_USER_SCORE.getAttendanceScore().isBlank());
        assertFalse(EXCEL_USER_SCORE.getTotalScoreFirstRound().isBlank());
    }

    @Test
    public void createAdmissionTicket() throws IOException {
        excelService.createAdmissionTicket(EXCEL_USER.getReceiptCode());
    }

    @Test
    public void createApplicantInformation() throws IOException {
        excelService.createApplicantInformation();
    }

    @Test
    public void getAllExcels() throws IOException {
        excelService.getAllExcels();
    }

}
