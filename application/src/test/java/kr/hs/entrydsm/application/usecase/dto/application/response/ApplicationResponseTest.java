package kr.hs.entrydsm.application.usecase.dto.application.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationResponseTest {

    private static final ApplicationResponse response1 =
            ApplicationResponse.builder()
            .educationalStatus("PROSPECTIVE_GRADUATE")
            .applicationType("COMMON")
            .isDaejeon(true)
            .applicationRemark("ONE_PARENT")
            .isGraduated(false)
            .graduatedAt("202203")
            .build();

    private static final ApplicationResponse response2 =
            ApplicationResponse.builder()
                    .educationalStatus("GRADUATE")
                    .applicationType("MEISTER")
                    .isDaejeon(true)
                    .applicationRemark("FROM_NORTH")
                    .isGraduated(true)
                    .graduatedAt("202103")
                    .build();

    private static final ApplicationResponse response3 =
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
        response1.setIsGraduated(false);
        response2.setIsGraduated(true);
        response3.setIsGraduated(false);
    }

    @Test
    void setGraduatedAt() {
        response1.setGraduatedAt("202203");
        response2.setGraduatedAt("202103");
        response3.setGraduatedAt("202106");
    }

    @Test
    void getEducationalStatus() {
        assertEquals(response1.getEducationalStatus(), "PROSPECTIVE_GRADUATE");
        assertEquals(response2.getEducationalStatus(), "GRADUATE");
        assertEquals(response3.getEducationalStatus(), "QUALIFICATION_EXAM");
    }

    @Test
    void getApplicationType() {
        assertEquals(response1.getApplicationType(), "COMMON");
        assertEquals(response2.getApplicationType(), "MEISTER");
        assertEquals(response3.getApplicationType(), "SOCIAL");
    }

    @Test
    void isDaejeon() {
        assertTrue(response1.isDaejeon());
        assertTrue(response2.isDaejeon());
        assertFalse(response3.isDaejeon());
    }

    @Test
    void getApplicationRemark() {
        assertEquals(response1.getApplicationRemark(), "ONE_PARENT");
        assertEquals(response2.getApplicationRemark(), "FROM_NORTH");
        assertEquals(response3.getApplicationRemark(), "MULTICULTURAL");
    }

    @Test
    void getGraduatedAt() {
        assertEquals(response1.getGraduatedAt(), "202203");
        assertEquals(response2.getGraduatedAt(), "202103");
        assertEquals(response3.getGraduatedAt(), "202106");
    }

    @Test
    void isGraduated() {
        assertTrue(response1.isDaejeon());
        assertTrue(response2.isDaejeon());
        assertFalse(response3.isDaejeon());
    }
}