package kr.hs.entrydsm.admin.usecase.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExcelUserScore {

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

    private String totalFirstGradeScores;

    private String totalSecondGradeScores;

    private String totalThirdGradeScores;

    private String volunteerTime;

    private String volunteerScore;

    private String dayAbsenceCount;

    private String lectureAbsenceCount;

    private String latenessCount;

    private String earlyLeaveCount;

    private String conversionScore;

    private String attendanceScore;

    private String totalScoreFirstRound;

}
