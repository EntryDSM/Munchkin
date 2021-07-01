package kr.hs.entrydsm.application.usecase.dto;

import kr.hs.entrydsm.application.entity.GraduationApplication;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


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
    private String examCode;
    private BigDecimal totalFirstGradeScore;
    private BigDecimal totalSecondGradeScore;
    private BigDecimal totalThirdGradeScore;
    private BigDecimal volunteerScore;
    private BigDecimal conversionScore;
    private int attendanceScore;
    private BigDecimal totalScoreFirstRound;

    public void setGraduationApplication(GraduationApplication graduationApplication){
        this.schoolName = graduationApplication.getSchoolName();
        this.receiptCode = graduationApplication.getReceiptCode();
        this.studentNumber = graduationApplication.getStudentNumber();
    }

    public void setScore(CalculatedScore calculatedScore){
        this.totalFirstGradeScore = calculatedScore.getTotalFirstGradeScore();
        this.totalSecondGradeScore = calculatedScore.getTotalSecondGradeScore();
        this.totalThirdGradeScore = calculatedScore.getTotalThirdGradeScore();
        this.volunteerScore = calculatedScore.getVolunteerScore();
        this.conversionScore = calculatedScore.getConversionScore();
        this.attendanceScore = calculatedScore.getAttendanceScore();
        this.totalScoreFirstRound = calculatedScore.getTotalScoreFirstRound();
    }

}
