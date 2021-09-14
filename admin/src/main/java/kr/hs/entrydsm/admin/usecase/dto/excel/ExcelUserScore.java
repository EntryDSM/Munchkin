package kr.hs.entrydsm.admin.usecase.dto.excel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExcelUserScore {

    private String koreanGrade;

    private String socialGrade;

    private String historyGrade;

    private String mathGrade;

    private String scienceGrade;

    private String englishGrade;

    private String techAndHomeGrade;

    private BigDecimal morePreviousSemesterScores;

    private BigDecimal previousSemesterScores;

    private BigDecimal totalThirdGradeScores;

    private Integer volunteerTime;

    private BigDecimal volunteerScore;

    private Integer dayAbsenceCount;

    private Integer lectureAbsenceCount;

    private Integer latenessCount;

    private Integer earlyLeaveCount;

    private BigDecimal conversionScore;

    private Integer attendanceScore;

    private BigDecimal totalScoreFirstRound;

}
