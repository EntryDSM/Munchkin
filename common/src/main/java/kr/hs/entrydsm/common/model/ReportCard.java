package kr.hs.entrydsm.common.context;

import java.math.BigDecimal;

public abstract class ReportCard {

    private long receiptCode;
    private BigDecimal totalScore;
    private BigDecimal volunteerScore;
    private BigDecimal gradeScore;
    private int attendanceScore;

    protected ReportCard(long receiptCode, BigDecimal totalScore, BigDecimal volunteerScore, BigDecimal gradeScore, int attendanceScore) {
        this.receiptCode = receiptCode;
        this.totalScore = totalScore;
        this.volunteerScore = volunteerScore;
        this.gradeScore = gradeScore;
        this.attendanceScore = attendanceScore;
    }
}
