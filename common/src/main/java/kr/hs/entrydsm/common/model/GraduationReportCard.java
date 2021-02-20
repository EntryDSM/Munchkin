package kr.hs.entrydsm.common.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GraduationReportCard extends ReportCard {

    private final boolean isGraduated;
    private final String schoolName;

    @Builder
    public GraduationReportCard(long receiptCode, Scores scores, boolean isGraduated, String schoolName) {
        super(receiptCode, scores);
        this.isGraduated = isGraduated;
        this.schoolName = schoolName;
    }

    @Override
    public boolean isGraduation() {
        return true;
    }
}
