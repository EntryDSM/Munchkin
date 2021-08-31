package kr.hs.entrydsm.application.builder;

import kr.hs.entrydsm.application.usecase.dto.CalculatedScore;

import java.math.BigDecimal;

public class CalculatedScoreBuilder {

    public static CalculatedScore build() {
        return CalculatedScore.builder()
                .receiptCode(1)
                .totalThirdBeforeBeforeScore(BigDecimal.ONE)
                .totalThirdBeforeScore(BigDecimal.ONE)
                .totalThirdGradeScore(BigDecimal.ONE)
                .volunteerScore(BigDecimal.ONE)
                .conversionScore(BigDecimal.ONE)
                .attendanceScore(1)
                .totalScoreFirstRound(BigDecimal.ONE)
                .build();
    }
}
