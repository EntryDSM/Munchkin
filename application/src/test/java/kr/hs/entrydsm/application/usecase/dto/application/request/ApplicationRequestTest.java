package kr.hs.entrydsm.application.usecase.dto.application.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationRequestTest {

    private static final ApplicationRequest PROSPECTIVE_COMMON =
            new ApplicationRequest("PROSPECTIVE_GRADUATE", "COMMON", false,
                    "ONE_PARENT", "202106");

    private static final ApplicationRequest GRADUATE_MEISTER =
            new ApplicationRequest("GRADUATE", "MEISTER", true,
                    "FROM_NORTH", "202107");

    private static final ApplicationRequest QUALIFICATION_SOCIAL =
            new ApplicationRequest("QUALIFICATION_EXAM", "SOCIAL", true,
                    "BASIC_LIVING", "202108");

    @Test
    public void educationalStatus() {
        assertEquals(PROSPECTIVE_COMMON.getEducationalStatus(), "PROSPECTIVE_GRADUATE");
        assertEquals(GRADUATE_MEISTER.getEducationalStatus(), "GRADUATE");
        assertEquals(QUALIFICATION_SOCIAL.getEducationalStatus(), "QUALIFICATION_EXAM");
    }

    @Test
    public void applicationType() {
        assertEquals(PROSPECTIVE_COMMON.getApplicationType(), "COMMON");
        assertEquals(GRADUATE_MEISTER.getApplicationType(), "MEISTER");
        assertEquals(QUALIFICATION_SOCIAL.getApplicationType(), "SOCIAL");
    }

    @Test
    public void isDaejeon() {
        assertFalse(PROSPECTIVE_COMMON.isDaejeon());
        assertTrue(GRADUATE_MEISTER.isDaejeon());
        assertTrue(QUALIFICATION_SOCIAL.isDaejeon());
    }

    @Test
    public void applicatioNRemark() {
        assertEquals(PROSPECTIVE_COMMON.getApplicationRemark(), "ONE_PARENT");
        assertEquals(GRADUATE_MEISTER.getApplicationRemark(), "FROM_NORTH");
        assertEquals(QUALIFICATION_SOCIAL.getApplicationRemark(), "BASIC_LIVING");
    }

    @Test
    public void graduatedAt() {
        assertEquals(PROSPECTIVE_COMMON.getGraduatedAt(), "202106");
        assertEquals(GRADUATE_MEISTER.getGraduatedAt(), "202107");
        assertEquals(QUALIFICATION_SOCIAL.getGraduatedAt(), "202108");
    }

}