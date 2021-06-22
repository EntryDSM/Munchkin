package kr.hs.entrydsm.application.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class GraduationApplicationTest {

    @Test
    public void isGraduationTrue() {
        Application application = GraduationApplicationBuilder.build();
        assertThat(application.isGraduation()).isTrue();
    }

    @Test
    public void graduationApplicationValues() {
        GraduationApplication application = GraduationApplicationBuilder.build(1);

        assertThat(application.getReceiptCode()).isEqualTo(1);
        assertThat(application.getIsGraduated()).isFalse();
        assertThat(application.getStudentNumber()).isEqualTo("30401");
        assertThat(application.getSchoolTel()).isEqualTo("0420000000");
        assertThat(application.getVolunteerTime()).isEqualTo(15);
        assertThat(application.getDayAbsenceCount()).isEqualTo(0);
        assertThat(application.getLectureAbsenceCount()).isEqualTo(0);
        assertThat(application.getLatenessCount()).isEqualTo(0);
        assertThat(application.getEarlyLeaveCount()).isEqualTo(0);
        assertThat(application.getKoreanScore()).isEqualTo("AXAAAA");
        assertThat(application.getEnglishScore()).isEqualTo("BXBBAA");
        assertThat(application.getMathScore()).isEqualTo("BXBBAA");
        assertThat(application.getSocialScore()).isEqualTo("AXAAAA");
        assertThat(application.getHistoryScore()).isEqualTo("AXAAAA");
        assertThat(application.getTechAndHomeScore()).isEqualTo("AXAAAA");
        assertThat(application.getGraduateAt()).isEqualTo(LocalDate.of(2019, 2, 1));
    }
}
