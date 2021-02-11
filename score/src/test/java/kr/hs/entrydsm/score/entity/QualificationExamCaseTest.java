package kr.hs.entrydsm.score.entity;

import kr.hs.entrydsm.score.integrate.application.QualificationExamCase;
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
        assertEquals(qualificationExamCase(prospectiveGraduateCommon).calculateGradeScore(), BigDecimal.valueOf(120.630));
        assertEquals(qualificationExamCase(prospectiveGraduateMeister).calculateGradeScore(), BigDecimal.valueOf(72.378));
        assertEquals(qualificationExamCase(graduateCommon).calculateGradeScore(), BigDecimal.valueOf(120.630));
        assertEquals(qualificationExamCase(graduateMeister).calculateGradeScore(), BigDecimal.valueOf(72.378));
    }
}
