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
    private BigDecimal attendanceScore;
    private BigDecimal totalScoreFirstRound;

    public void setGraduationApplication(GraduationApplication graduationApplication){
        this.schoolName = graduationApplication.getSchoolName();
        this.receiptCode = graduationApplication.getReceiptCode();
        this.studentNumber = graduationApplication.getStudentNumber();
        this.volunteerTime = graduationApplication.getVolunteerTime();
        this.dayAbsenceCount = graduationApplication.getDayAbsenceCount();
        this.lectureAbsenceCount = graduationApplication.getLectureAbsenceCount();
        this.latenessCount = graduationApplication.getLatenessCount();
        this.earlyLeaveCount = graduationApplication.getEarlyLeaveCount();
        this.koreanScore = graduationApplication.getKoreanScore();
        this.socialScore = graduationApplication.getSocialScore();
        this.historyScore = graduationApplication.getHistoryScore();
        this.mathScore = graduationApplication.getMathScore();
        this.scienceScore = graduationApplication.getScienceScore();
        this.englishScore = graduationApplication.getEnglishScore();
        this.techAndHomeScore = graduationApplication.getTechAndHomeScore();
    }

    public void setScore(Score score){
        this.totalFirstGradeScore = score.getTotalFirstGradeScore();
        this.totalSecondGradeScore = score.getTotalSecondGradeScore();
        this.totalThirdGradeScore = score.getTotalThirdGradeScore();
        this.volunteerScore = score.getVolunteerScore();
        this.conversionScore = score.getConversionScore();
        this.attendanceScore = score.getAttendanceScore();
        this.totalScoreFirstRound = score.getTotalScoreFirstRound();
    }

}
