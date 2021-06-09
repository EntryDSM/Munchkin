package kr.hs.entrydsm.application.usecase.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ReportCard {

    private final long receiptCode;
    private final Score score;

    private final Boolean isGraduated;
    private final String schoolName;
    private final String schoolTel;
    private final Integer volunteerTime;
    private final Integer latenessCount;
    private final Integer earlyLeaveCount;
    private final Integer lectureAbsenceCount;
    private final Integer dayAbsenceCount;

    private final BigDecimal averageScore;

    @Builder(builderMethodName = "graduationBuilder", builderClassName = "GraduationBuilder")
    public ReportCard(long receiptCode, Score score, boolean isGraduated, String schoolName, String schoolTel,
                      int volunteerTime, int latenessCount, int earlyLeaveCount, int lectureAbsenceCount, int dayAbsenceCount) {
        this.receiptCode = receiptCode;
        this.score = score;
        this.isGraduated = isGraduated;
        this.schoolName = schoolName;
        this.schoolTel = schoolTel;
        this.volunteerTime = volunteerTime;
        this.latenessCount = latenessCount;
        this.earlyLeaveCount = earlyLeaveCount;
        this.lectureAbsenceCount = lectureAbsenceCount;
        this.dayAbsenceCount = dayAbsenceCount;
        this.averageScore = null;
    }

    @Builder(builderMethodName = "qualificationBuilder", builderClassName = "QualificationBuilder")
    public ReportCard(long receiptCode, Score score, BigDecimal averageScore) {
        this.receiptCode = receiptCode;
        this.score = score;
        this.averageScore = averageScore;
        this.isGraduated = null;
        this.schoolName = null;
        this.schoolTel = null;
        this.volunteerTime = null;
        this.latenessCount = null;
        this.earlyLeaveCount = null;
        this.lectureAbsenceCount = null;
        this.dayAbsenceCount = null;
    }

    public BigDecimal getTotalScore() {
        return score.getTotalScoreFirstRound();
    }

    public BigDecimal getVolunteerScore() {
        return score.getVolunteerScore();
    }

    public BigDecimal getGradeScore() {
        return score.getConversionScore();
    }

    public int attendanceScore() {
        return score.getAttendanceScore();
    }
}
