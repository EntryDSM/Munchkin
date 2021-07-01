package kr.hs.entrydsm.score.entity;

import kr.hs.entrydsm.score.integrate.user.Scorer;
import lombok.*;

import javax.persistence.Entity;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@Entity(name = "tbl_qualification_exam_case")
public class QualificationExamCase extends ApplicationCase {
    @Digits(integer = 3, fraction = 2)
    private BigDecimal averageScore;

    @Builder
    public QualificationExamCase(Scorer scorer, BigDecimal averageScore) {
        super(scorer);
        this.averageScore = averageScore;
    }

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
