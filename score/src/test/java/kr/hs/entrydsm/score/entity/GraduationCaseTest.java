package kr.hs.entrydsm.score.entity;

import kr.hs.entrydsm.score.integrate.application.GraduationCase;
import kr.hs.entrydsm.score.integrate.user.Scorer;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GraduationCaseTest extends EntityTest {
    private GraduationCase graduationCase(Scorer scorer) {
        return GraduationCase.builder()
                             .scorer(scorer)
                             .volunteerTime(44)
                             .dayAbsenceCount(2)
                             .lectureAbsenceCount(1)
                             .latenessCount(2)
                             .earlyLeaveCount(1)
                             .englishGrade("AXAABA")
                             .historyGrade("AXBBAB")
                             .mathGrade("CXCBAA")
                             .koreanGrade("AXAAAA")
                             .scienceGrade("AXBAAD")
                             .socialGrade("BXBCAA")
                             .techAndHomeGrade("BXXXXX")
                             .build();
    }

    @Test
    void calculateVolunteerScore() {
        for (Scorer scorer: scorers) {
            assertEquals(graduationCase(scorer).calculateVolunteerScore(), BigDecimal.valueOf(14.667));
        }
    }

    @Test
    void calculateAttendanceScore() {
        for (Scorer scorer: scorers) {
            assertEquals(graduationCase(scorer).calculateAttendanceScore(), 12);
        }
    }

    @Test
    void calculateGradeScore() {
        assertEquals(graduationCase(prospectiveGraduateCommon).calculateGradeScore(), BigDecimal.valueOf(136.107));
        assertEquals(graduationCase(prospectiveGraduateMeister).calculateGradeScore(), BigDecimal.valueOf(81.664));
        assertEquals(graduationCase(graduateCommon).calculateGradeScore(), BigDecimal.valueOf(133.107));
        assertEquals(graduationCase(graduateMeister).calculateGradeScore(), BigDecimal.valueOf(79.864));
    }
}
