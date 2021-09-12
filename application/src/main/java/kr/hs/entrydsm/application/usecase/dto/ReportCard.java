package kr.hs.entrydsm.application.usecase.dto;

import kr.hs.entrydsm.application.entity.Application;
import kr.hs.entrydsm.application.entity.GraduationApplication;
import kr.hs.entrydsm.application.entity.QualificationExamApplication;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ReportCard {

    private final long receiptCode;
    private final CalculatedScore calculatedScore;

    private final Boolean isGraduated;
    private final String schoolName;
    private final String schoolTel;

    private final BigDecimal averageScore;

    @Builder(builderMethodName = "graduationBuilder", builderClassName = "GraduationBuilder")
    public ReportCard(long receiptCode, CalculatedScore calculatedScore, boolean isGraduated, String schoolName, String schoolTel) {
        this.receiptCode = receiptCode;
        this.calculatedScore = calculatedScore;
        this.isGraduated = isGraduated;
        this.schoolName = schoolName;
        this.schoolTel = schoolTel;
        this.averageScore = null;
    }

    @Builder(builderMethodName = "qualificationBuilder", builderClassName = "QualificationBuilder")
    public ReportCard(long receiptCode, CalculatedScore calculatedScore, BigDecimal averageScore) {
        this.receiptCode = receiptCode;
        this.calculatedScore = calculatedScore;
        this.averageScore = averageScore;
        this.isGraduated = null;
        this.schoolName = null;
        this.schoolTel = null;
    }

    public static ReportCard from(Application application, CalculatedScore calculatedScore) {
        ReportCard result;
        if (application.isGraduation()) {
            GraduationApplication graduationApplication = (GraduationApplication) application;
            result = ReportCard.graduationBuilder()
                    .receiptCode(application.getReceiptCode())
                    .calculatedScore(calculatedScore)
                    .isGraduated(graduationApplication.getIsGraduated())
                    .schoolTel(graduationApplication.getSchoolTel())
                    .schoolName(graduationApplication.getSchoolName())
                    .build();
        } else {
            QualificationExamApplication qualificationExamApplication = (QualificationExamApplication) application;
            result = ReportCard.qualificationBuilder()
                    .receiptCode(application.getReceiptCode())
                    .calculatedScore(calculatedScore)
                    .build();
        }
        return result;
    }

    public BigDecimal getTotalScore() {
        return calculatedScore.getTotalScoreFirstRound();
    }

    public BigDecimal getVolunteerScore() {
        return calculatedScore.getVolunteerScore();
    }

    public BigDecimal getGradeScore() {
        return calculatedScore.getConversionScore();
    }

    public Integer getVolunteerTime() {
        return calculatedScore.getVolunteerTime();
    }

    public Integer getDayAbsenceCount() {
        return calculatedScore.getDayAbsenceCount();
    }

    public Integer getLectureAbsenceCount() {
        return calculatedScore.getLectureAbsenceCount();
    }

    public Integer getEarlyLeaveCount() {
        return calculatedScore.getEarlyLeaveCount();
    }

    public Integer getLatenessCount() {
        return calculatedScore.getLatenessCount();
    }

    public int getAttendanceScore() {
        return calculatedScore.getAttendanceScore();
    }
}
