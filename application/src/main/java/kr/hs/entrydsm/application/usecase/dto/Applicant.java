package kr.hs.entrydsm.application.usecase.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Applicant {
    private Long receiptCode;
    private String studentNumber;
    private String schoolName;
    private Integer volunteerTime;
    private Integer dayAbsenceCount;
    private Integer lectureAbsenceCount;
    private Integer latenessCount;
    private Integer earlyLeaveCount;
    private String koreanScore;
    private String socialScore;
    private String historyScore;
    private String mathScore;
    private String scienceScore;
    private String englishScore;
    private String techAndHomeScore;
    private String totalFirstGradeScores;
    private String totalSecondGradeScores;
    private String totalThirdGradeScores;
    private String volunteerScore;
    private String conversionScore;
    private String attendanceScore;
    private String totalScoreFirstRound;
}
