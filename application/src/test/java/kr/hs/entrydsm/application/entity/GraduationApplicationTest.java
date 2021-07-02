package kr.hs.entrydsm.application.entity;

import kr.hs.entrydsm.application.builder.GraduationApplicationBuilder;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class GraduationApplicationTest {

    @Test
    public void isGraduationTrue() {
        Application application = GraduationApplicationBuilder.build();
        assertThat(application.isGraduation()).isTrue();
    }

    @Test
    public void graduationApplicationValues() {
        GraduationApplication application = GraduationApplicationBuilder.build(1);
        application.setReceiptCode(1L);

        assertThat(application.getReceiptCode()).isEqualTo(1);
        assertThat(application.getIsGraduated()).isFalse();
        assertThat(application.getStudentNumber()).isEqualTo("30401");
        assertThat(application.getSchoolTel()).isEqualTo("0420000000");
        assertThat(application.getGraduatedAt()).isEqualTo(LocalDate.of(2019, 2, 1));
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

    @Test
    public void getSchoolName() {
        GraduationApplication application = GraduationApplicationBuilder.buildEmptyStudentNumber();

        assertEquals(application.getSchoolName(), "대덕소프트웨어마이스터중학교");
    }

    @Test
    public void getSchoolCode() {
        GraduationApplication application = GraduationApplicationBuilder.buildEmptyStudentNumber();

        assertEquals(application.getSchoolCode(), "44444");
    }

}
