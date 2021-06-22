package kr.hs.entrydsm.application.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class QualificationExamApplicationTest {

    @Test
    public void isGraduationFalse() {
        Application application = QualificationExamApplicationBuilder.build();
        assertThat(application.isGraduation()).isFalse();
    }

    @Test
    public void qualificationExamApplicationValues() {
        QualificationExamApplication application = QualificationExamApplicationBuilder.build();

        assertThat(application.getAverageScore()).isEqualTo(BigDecimal.valueOf(10));
        assertThat(application.getQualifiedAt()).isEqualTo(LocalDate.of(2020, 8, 13));
    }
}
