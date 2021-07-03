package kr.hs.entrydsm.application.usecase.dto.application.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationResponseTest {

    private static final ApplicationResponse RESPONSE_1 =
            ApplicationResponse.builder()
            .educationalStatus("PROSPECTIVE_GRADUATE")
            .applicationType("COMMON")
            .isDaejeon(true)
            .applicationRemark("ONE_PARENT")
            .isGraduated(false)
            .graduatedAt("202203")
            .build();

    private static final ApplicationResponse RESPONSE_2 =
            ApplicationResponse.builder()
                    .educationalStatus("GRADUATE")
                    .applicationType("MEISTER")
                    .isDaejeon(true)
                    .applicationRemark("FROM_NORTH")
                    .isGraduated(true)
                    .graduatedAt("202103")
                    .build();

    private static final ApplicationResponse RESPONSE_3 =
            ApplicationResponse.builder()
                    .educationalStatus("QUALIFICATION_EXAM")
                    .applicationType("SOCIAL")
                    .isDaejeon(false)
                    .applicationRemark("MULTICULTURAL")
                    .isGraduated(false)
                    .graduatedAt("202106")
                    .build();


    @Test
    void setIsGraduated() {
        RESPONSE_1.setIsGraduated(false);
        RESPONSE_2.setIsGraduated(true);
        RESPONSE_3.setIsGraduated(false);
    }

    @Test
    void setGraduatedAt() {
        RESPONSE_1.setGraduatedAt("202203");
        RESPONSE_2.setGraduatedAt("202103");
        RESPONSE_3.setGraduatedAt("202106");
    }

    @Test
    void getEducationalStatus() {
        assertEquals(RESPONSE_1.getEducationalStatus(), "PROSPECTIVE_GRADUATE");
        assertEquals(RESPONSE_2.getEducationalStatus(), "GRADUATE");
        assertEquals(RESPONSE_3.getEducationalStatus(), "QUALIFICATION_EXAM");
    }

    @Test
    void getApplicationType() {
        assertEquals(RESPONSE_1.getApplicationType(), "COMMON");
        assertEquals(RESPONSE_2.getApplicationType(), "MEISTER");
        assertEquals(RESPONSE_3.getApplicationType(), "SOCIAL");
    }

    @Test
    void isDaejeon() {
        assertTrue(RESPONSE_1.isDaejeon());
        assertTrue(RESPONSE_2.isDaejeon());
        assertFalse(RESPONSE_3.isDaejeon());
    }

    @Test
    void getApplicationRemark() {
        assertEquals(RESPONSE_1.getApplicationRemark(), "ONE_PARENT");
        assertEquals(RESPONSE_2.getApplicationRemark(), "FROM_NORTH");
        assertEquals(RESPONSE_3.getApplicationRemark(), "MULTICULTURAL");
    }

    @Test
    void getGraduatedAt() {
        assertEquals(RESPONSE_1.getGraduatedAt(), "202203");
        assertEquals(RESPONSE_2.getGraduatedAt(), "202103");
        assertEquals(RESPONSE_3.getGraduatedAt(), "202106");
    }

    @Test
    void isGraduated() {
        assertFalse(RESPONSE_1.isGraduated());
        assertTrue(RESPONSE_2.isGraduated());
        assertFalse(RESPONSE_3.isGraduated());
    }
}