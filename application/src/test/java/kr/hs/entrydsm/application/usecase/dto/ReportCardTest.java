package kr.hs.entrydsm.application.usecase.dto;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class ReportCardTest {

    private final ReportCard graduationReportCard = ReportCard.graduationBuilder()
            .receiptCode(1)
            .isGraduated(false)
            .schoolName("대전하기중학교")
            .schoolTel("0420000000")
            .volunteerTime(20)
            .latenessCount(0)
            .earlyLeaveCount(0)
            .lectureAbsenceCount(0)
            .dayAbsenceCount(0)
            .calculatedScore(
                    CalculatedScore.builder()
                            .receiptCode(1)
                            .attendanceScore(10)
                            .volunteerScore(BigDecimal.TEN)
                            .conversionScore(BigDecimal.TEN)
                            .totalFirstGradeScore(BigDecimal.TEN)
                            .totalSecondGradeScore(BigDecimal.TEN)
                            .totalThirdGradeScore(BigDecimal.TEN)
                            .totalScoreFirstRound(BigDecimal.TEN)
                            .build()
            ).build();

    private final ReportCard qualificationExamReportCard = ReportCard.qualificationBuilder()
            .receiptCode(1)
            .averageScore(BigDecimal.TEN)
            .calculatedScore(
                    CalculatedScore.builder()
                            .receiptCode(1)
                            .attendanceScore(0)
                            .volunteerScore(null)
                            .conversionScore(null)
                            .totalFirstGradeScore(null)
                            .totalSecondGradeScore(null)
                            .totalThirdGradeScore(null)
                            .totalScoreFirstRound(BigDecimal.TEN)
                            .build()
            ).build();

    @Test
    public void graduationReportCardValueCheck() {
        assertThat(graduationReportCard.getReceiptCode()).isEqualTo(1);
        assertThat(graduationReportCard.getIsGraduated()).isFalse();
        assertThat(graduationReportCard.getSchoolName()).isEqualTo("대전하기중학교");
        assertThat(graduationReportCard.getSchoolTel()).isEqualTo("0420000000");
        assertThat(graduationReportCard.getVolunteerTime()).isEqualTo(20);
        assertThat(graduationReportCard.getLatenessCount()).isEqualTo(0);
        assertThat(graduationReportCard.getEarlyLeaveCount()).isEqualTo(0);
        assertThat(graduationReportCard.getLectureAbsenceCount()).isEqualTo(0);
        assertThat(graduationReportCard.getDayAbsenceCount()).isEqualTo(0);
        assertThat(graduationReportCard.getGradeScore()).isEqualTo(BigDecimal.TEN);
        assertThat(graduationReportCard.getVolunteerScore()).isEqualTo(BigDecimal.TEN);
        assertThat(graduationReportCard.getTotalScore()).isEqualTo(BigDecimal.TEN);
        assertThat(graduationReportCard.getAttendanceScore()).isEqualTo(10);
    }

    @Test
    public void qualificationExamReportCardValueCheck() {
        assertThat(qualificationExamReportCard.getReceiptCode()).isEqualTo(1);
        assertThat(qualificationExamReportCard.getIsGraduated()).isNull();
        assertThat(qualificationExamReportCard.getSchoolName()).isNull();
        assertThat(qualificationExamReportCard.getSchoolTel()).isNull();
        assertThat(qualificationExamReportCard.getVolunteerTime()).isNull();
        assertThat(qualificationExamReportCard.getLatenessCount()).isNull();
        assertThat(qualificationExamReportCard.getEarlyLeaveCount()).isNull();
        assertThat(qualificationExamReportCard.getLectureAbsenceCount()).isNull();
        assertThat(qualificationExamReportCard.getDayAbsenceCount()).isNull();
        assertThat(qualificationExamReportCard.getGradeScore()).isNull();
        assertThat(qualificationExamReportCard.getVolunteerScore()).isNull();
        assertThat(qualificationExamReportCard.getTotalScore()).isEqualTo(BigDecimal.TEN);
        assertThat(qualificationExamReportCard.getAttendanceScore()).isEqualTo(0);
    }

}
