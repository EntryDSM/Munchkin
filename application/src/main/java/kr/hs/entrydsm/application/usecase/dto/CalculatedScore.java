package kr.hs.entrydsm.application.usecase.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class CalculatedScore {
    private long receiptCode;
    private BigDecimal totalThirdBeforeBeforeScore;
    private BigDecimal totalThirdBeforeScore;
    private BigDecimal totalThirdGradeScore;
    private BigDecimal volunteerScore; //봉사점수 총합
    private BigDecimal conversionScore; //교과 성적 환산 점수
    private Integer attendanceScore; //출석 점수
    private BigDecimal totalScoreFirstRound; //1차 전형 총점

    public static CalculatedScore emptyScore(long receiptCode) {
        return CalculatedScore.builder()
                .receiptCode(receiptCode)
                .totalThirdBeforeBeforeScore(null)
                .totalThirdBeforeScore(null)
                .totalThirdGradeScore(null)
                .volunteerScore(null)
                .conversionScore(null)
                .attendanceScore(null)
                .totalScoreFirstRound(null)
                .build();
    }
}
