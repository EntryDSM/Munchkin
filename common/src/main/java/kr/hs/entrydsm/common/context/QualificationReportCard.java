package kr.hs.entrydsm.common.context;

import lombok.Builder;

import java.math.BigDecimal;

public class QualificationReportCard extends ReportCard {

    private BigDecimal averageScore;

    @Builder
    protected QualificationReportCard(long receiptCode, BigDecimal totalScore, BigDecimal volunteerScore, BigDecimal gradeScore, int attendanceScore, BigDecimal averageScore) {
        super(receiptCode, totalScore, volunteerScore, gradeScore, attendanceScore);
        this.averageScore = averageScore;
    }
}
