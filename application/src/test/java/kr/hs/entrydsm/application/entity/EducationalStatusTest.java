package kr.hs.entrydsm.application.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EducationalStatusTest {

    @Test
    public void educationalStatus() {
        assertEquals(EducationalStatus.GRADUATE, "GRADUATE");
        assertEquals(EducationalStatus.PROSPECTIVE_GRADUATE, "PROSPECTIVE_GRADUATE");
        assertEquals(EducationalStatus.QUALIFICATION_EXAM, "QUALIFICATION_EXAM");
    }

}