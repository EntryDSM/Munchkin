package kr.hs.entrydsm.common.context;

import lombok.Builder;

import java.math.BigDecimal;

public class GraduationReportCard extends ReportCard {

    private boolean isGraduated;
    private String schoolName;

    @Builder
    public GraduationReportCard(long receiptCode, BigDecimal totalScore, BigDecimal volunteerScore, BigDecimal gradeScore, int attendanceScore, boolean isGraduated, String schoolName) {
        super(receiptCode, totalScore, volunteerScore, gradeScore, attendanceScore);
        this.isGraduated = isGraduated;
        this.schoolName = schoolName;
    }
}
