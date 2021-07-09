package kr.hs.entrydsm.score.entity;

import kr.hs.entrydsm.score.integrate.user.Scorer;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
public abstract class ApplicationCase {
    protected static final BigDecimal COMMON_GRADE_RATE = BigDecimal.valueOf(1.75);

    protected static final int MAX_VOLUNTEER_TIME = 30;
    protected static final int MIN_VOLUNTEER_TIME = 6;

    protected static final int MAX_VOLUNTEER_SCORE = 15;
    protected static final int MIN_VOLUNTEER_SCORE = 3;

    protected static final int MAX_ATTENDANCE_SCORE = 15;

    @Transient
    protected Scorer scorer;

    @Id
    protected Long receiptCode;

    public ApplicationCase(Scorer scorer) {
        this.scorer = scorer;
        this.receiptCode = scorer.getReceiptCode();
    }

    abstract public BigDecimal calculateVolunteerScore();
    abstract public Integer calculateAttendanceScore();
    abstract public BigDecimal[] calculateGradeScores();
    abstract public BigDecimal calculateTotalGradeScore();
}
