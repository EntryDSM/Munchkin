package kr.hs.entrydsm.application.entity;

import kr.hs.entrydsm.application.builder.GraduationApplicationBuilder;
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
        assertThat(application.getScienceScore()).isEqualTo("AXAAAA");
        assertThat(application.getTechAndHomeScore()).isEqualTo("AXAAAA");
        assertThat(application.getGraduateAt()).isEqualTo(LocalDate.of(2019, 2, 1));
    }

    @Test
    public void getSchoolInfo() {
        GraduationApplication application = GraduationApplicationBuilder.build();

        assertThat(application.getSchoolName()).isEqualTo("대전하기중학교");
        assertThat(application.getSchoolCode()).isEqualTo("33333");
        assertThat(application.getSchoolClass()).isEqualTo("4");
    }

    @Test
    public void setSchoolInfo() {
        GraduationApplication application = GraduationApplicationBuilder.build();
        School school = School.builder()
                .name("대덕소프트웨어마이스터중학교")
                .address("대전광역시 유성구 장동")
                .code("44444")
                .information("대전광역시 교육청")
                .build();

        application.setSchoolTel("0421111111");
        application.setSchool(school);

        assertThat(application.getSchoolTel()).isEqualTo("0421111111");
        assertThat(application.getSchoolName()).isEqualTo("대덕소프트웨어마이스터중학교");
        assertThat(application.getSchoolCode()).isEqualTo("44444");
    }

    @Test
    public void getSchoolClassNull() {
        GraduationApplication application = GraduationApplicationBuilder.buildEmptyStudentNumber();

        assertThat(application.getSchoolClass()).isNull();
    }
}
