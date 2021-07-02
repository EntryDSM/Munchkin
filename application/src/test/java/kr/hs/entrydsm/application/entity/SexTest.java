package kr.hs.entrydsm.application.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SexTest {

    @Test
    public void sex() {
        assertEquals(Sex.MALE, "MALE");
        assertEquals(Sex.FEMALE, "FEMALE");
    }

}