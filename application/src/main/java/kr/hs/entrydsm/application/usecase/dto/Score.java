package kr.hs.entrydsm.application.usecase.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Score {
    private String totalFirstGradeScores; //1학년 성적 총합
    private String totalSecondGradeScores; //2학년 성적 총합
    private String totalThirdGradeScores; //3학년 성적 총합
    private String volunteerScore; //봉사점수 총합
    private String conversionScore; //교과 성적 환산 점수
    private String attendanceScore; //출석 점수
    private String totalScoreFirstRound; //1차 전형 총점
}
