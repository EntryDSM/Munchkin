package kr.hs.entrydsm.score.integrate.application;

import kr.hs.entrydsm.score.integrate.user.Scorer;
import lombok.*;

import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@AllArgsConstructor
public class QualificationExamCase extends ApplicationCase {

    @Builder(builderMethodName = "builder")
    public static QualificationExamCase initializer(Scorer scorer, BigDecimal averageScore) {
       QualificationExamCase qualificationExamCase = new QualificationExamCase(scorer, averageScore);
       return nullCheck(qualificationExamCase);
    }

    private final Scorer scorer;

    @Digits(integer = 3, fraction = 2)
    private final BigDecimal averageScore;

    @Override
    public BigDecimal calculateVolunteerScore() { return volunteerScoreFormula(); }

    @Override
    public Integer calculateAttendanceScore() { return MAX_ATTENDANCE_SCORE; }

    @Override
    public BigDecimal[] calculateGradeScores() { return new BigDecimal[]{BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO}; }

    @Override
    public BigDecimal calculateTotalGradeScore() { return gradeScoreFormula(); }


    private BigDecimal volunteerScoreFormula() {
        return averageScore.subtract(BigDecimal.valueOf(40))
                           .divide(BigDecimal.valueOf(5), 3, RoundingMode.HALF_UP)
                           .add(BigDecimal.valueOf(3));
    }

    private BigDecimal gradeScoreFormula() {
        BigDecimal gradeScore = averageScore.subtract(BigDecimal.valueOf(50)).multiply(BigDecimal.valueOf(3));

        if (scorer.isMeister()) {
            return gradeScore.multiply(BigDecimal.valueOf(0.6));
        } else {
            return gradeScore;
        }
    }
}
