package kr.hs.entrydsm.score.entity;

import kr.hs.entrydsm.score.integrate.user.Scorer;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QualificationExamCaseTest extends EntityTest {
    private QualificationExamCase qualificationExamCase(Scorer scorer) {
        return QualificationExamCase.builder()
                                    .scorer(scorer)
                                    .averageScore(BigDecimal.valueOf(90.21))
                                    .build();
    }

    @Test
    void calculateVolunteerScore() {
        for (Scorer scorer: scorers) {
            assertEquals(qualificationExamCase(scorer).calculateVolunteerScore(), BigDecimal.valueOf(13.042));
        }
    }

    @Test
    void calculateAttendanceScore() {
        for (Scorer scorer: scorers) {
            assertEquals(qualificationExamCase(scorer).calculateAttendanceScore(), 15);
        }
    }

    @Test
    void calculateGradeScore() {
        assertEquals(BigDecimal.valueOf(112.588), qualificationExamCase(prospectiveGraduateCommon).calculateTotalGradeScore());
        assertEquals(BigDecimal.valueOf(64.336), qualificationExamCase(prospectiveGraduateMeister).calculateTotalGradeScore());
        assertEquals(BigDecimal.valueOf(112.588), qualificationExamCase(graduateCommon).calculateTotalGradeScore());
        assertEquals(BigDecimal.valueOf(64.336), qualificationExamCase(graduateMeister).calculateTotalGradeScore());
    }
}
