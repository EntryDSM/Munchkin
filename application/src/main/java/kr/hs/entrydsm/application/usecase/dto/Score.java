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
public class Score {
    private Long receiptCode;
    private BigDecimal totalFirstGradeScores; //1학년 성적 총합
    private BigDecimal totalSecondGradeScores; //2학년 성적 총합
    private BigDecimal totalThirdGradeScores; //3학년 성적 총합
    private int volunteerScore; //봉사점수 총합
    private BigDecimal conversionScore; //교과 성적 환산 점수
    private BigDecimal attendanceScore; //출석 점수
    private BigDecimal totalScoreFirstRound; //1차 전형 총점
}
