package kr.hs.entrydsm.application.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class QualificationExamApplicationTest {

    @Test
    public void isGraduationFalse() {
        QualificationExamApplication application = new QualificationExamApplication();
        assertThat(application.isGraduation()).isFalse();
    }
}
