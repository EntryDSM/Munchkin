package kr.hs.entrydsm.common.model;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class QualificationReportCard extends ReportCard {

    private final BigDecimal averageScore;

    @Builder
    public QualificationReportCard(long receiptCode, Scores scores, BigDecimal averageScore) {
        super(receiptCode, scores);
        this.averageScore = averageScore;
    }
}
