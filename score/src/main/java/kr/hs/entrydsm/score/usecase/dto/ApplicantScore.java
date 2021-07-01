package kr.hs.entrydsm.score.usecase.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class ApplicantScore {

    private String yearOfGraduation;

    private String middleSchoolStudentNumber;

    private String middleSchool;

    private String koreanGrade;

    private String socialGrade;

    private String historyGrade;

    private String mathGrade;

    private String scienceGrade;

    private String englishGrade;

    private String techAndHomeGrade;

    private BigDecimal totalFirstGradeScores;

    private BigDecimal totalSecondGradeScores;

    private BigDecimal totalThirdGradeScores;

    private Integer volunteerTime;

    private Integer volunteerScore;

    private Integer dayAbsenceCount;

    private Integer lectureAbsenceCount;

    private Integer latenessCount;

    private Integer earlyLeaveCount;

    private BigDecimal conversionScore;

    private Integer attendanceScore;

    private BigDecimal totalScoreFirstRound;

}
