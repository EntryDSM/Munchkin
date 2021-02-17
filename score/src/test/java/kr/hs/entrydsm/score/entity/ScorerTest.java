package kr.hs.entrydsm.score.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ScorerTest extends EntityTest {


    @Test
    public void isMeister() {
        assertFalse(prospectiveGraduateCommon.isMeister());
        assertTrue(prospectiveGraduateMeister.isMeister());
        assertFalse(graduateCommon.isMeister());
        assertTrue(prospectiveGraduateMeister.isMeister());
    }

    @Test
    public void isQualificationExam() {
        assertFalse(prospectiveGraduateCommon.isQualificationExam());
        assertFalse(prospectiveGraduateMeister.isQualificationExam());
        assertFalse(graduateCommon.isQualificationExam());
        assertFalse(prospectiveGraduateMeister.isQualificationExam());
    }

    @Test
    public void isGraduated() {
        assertFalse(prospectiveGraduateCommon.isGraduated());
        assertFalse(prospectiveGraduateMeister.isGraduated());
        assertTrue(graduateCommon.isGraduated());
        assertTrue(graduateMeister.isGraduated());
    }
}
