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
    }

}
