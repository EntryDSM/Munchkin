package kr.hs.entrydsm.application.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GraduationApplicationTest {

    @Test
    public void isGraduationTrue() {
        GraduationApplication application = new GraduationApplication();
        assertThat(application.isGraduation()).isTrue();
    }
}
