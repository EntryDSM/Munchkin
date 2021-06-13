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
    private BigDecimal totalFirstGradeScore; //1학년 성적 총합
    private BigDecimal totalSecondGradeScore; //2학년 성적 총합
    private BigDecimal totalThirdGradeScore; //3학년 성적 총합
    private BigDecimal volunteerScore; //봉사점수 총합
    private BigDecimal conversionScore; //교과 성적 환산 점수
    private int attendanceScore; //출석 점수
    private BigDecimal totalScoreFirstRound; //1차 전형 총점
}
