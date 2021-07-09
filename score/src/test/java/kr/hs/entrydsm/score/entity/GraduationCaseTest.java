package kr.hs.entrydsm.score.entity;

import kr.hs.entrydsm.score.integrate.user.Scorer;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GraduationCaseTest extends EntityTest {
    private GraduationCase graduationCase(Scorer scorer) {
        return GraduationCase.builder()
                             .scorer(scorer)
                             .volunteerTime(29)
                             .dayAbsenceCount(2)
                             .lectureAbsenceCount(1)
                             .latenessCount(2)
                             .earlyLeaveCount(1)
                             .englishGrade("AAAXBA")
                             .historyGrade("ABBXAB")
                             .mathGrade("CBCXAA")
                             .koreanGrade("AAAXAA")
                             .scienceGrade("AABXAD")
                             .socialGrade("BCBXAA")
                             .techAndHomeGrade("BXEXXX")
                             .build();
    }

    @Test
    void calculateVolunteerScore() {
        for (Scorer scorer: scorers) {
                assertEquals(BigDecimal.valueOf(14.5).setScale(3, RoundingMode.HALF_UP),
                        graduationCase(scorer).calculateVolunteerScore());
        }
    }

    @Test
    void calculateAttendanceScore() {
        for (Scorer scorer: scorers) {
            assertEquals(12, graduationCase(scorer).calculateAttendanceScore());
        }
    }

    @Test
    void calculateGradeScore() {
        assertEquals(BigDecimal.valueOf(123.998), graduationCase(prospectiveGraduateCommon).calculateTotalGradeScore());
        assertEquals(BigDecimal.valueOf(70.856), graduationCase(prospectiveGraduateMeister).calculateTotalGradeScore());
        assertEquals(BigDecimal.valueOf(120.498), graduationCase(graduateCommon).calculateTotalGradeScore());
        assertEquals(BigDecimal.valueOf(68.856), graduationCase(graduateMeister).calculateTotalGradeScore());
    }

    private GraduationCase fromForeignCase(Scorer scorer) {
        return GraduationCase.builder()
                .scorer(scorer)
                .englishGrade("XAXXBA")
                .historyGrade("XBXXAB")
                .mathGrade("XBXXAA")
                .koreanGrade("XAXXAA")
                .scienceGrade("XAXXAD")
                .socialGrade("XCXXAA")
                .techAndHomeGrade("XXXXXX")
                .build();
    }

    @Test
    void calculateGradeScoreFromForeign() {
        assertEquals(BigDecimal.valueOf(130.664), fromForeignCase(prospectiveGraduateCommon).calculateTotalGradeScore());
        assertEquals(BigDecimal.valueOf(74.665), fromForeignCase(prospectiveGraduateMeister).calculateTotalGradeScore());
        assertEquals(BigDecimal.valueOf(125.998), fromForeignCase(graduateCommon).calculateTotalGradeScore());
        assertEquals(BigDecimal.valueOf(71.999), fromForeignCase(graduateMeister).calculateTotalGradeScore());
    }
}
