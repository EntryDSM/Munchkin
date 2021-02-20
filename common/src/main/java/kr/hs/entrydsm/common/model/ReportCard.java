package kr.hs.entrydsm.common.model;

import java.math.BigDecimal;

public abstract class ReportCard {

    private final long receiptCode;
    private final Scores scores;

    protected ReportCard(long receiptCode, Scores scores) {
        this.receiptCode = receiptCode;
        this.scores = scores;
    }

    public final long getReceiptCode() {
        return receiptCode;
    }

    public final BigDecimal getTotalScore() {
        return scores.getTotalScore();
    }

    public final BigDecimal getVolunteerScore() {
        return scores.getVolunteerScore();
    }

    public final BigDecimal getGradeScore() {
        return scores.getGradeScore();
    }

    public final int getAttendanceScore() {
        return scores.getAttendanceScore();
    }
}
