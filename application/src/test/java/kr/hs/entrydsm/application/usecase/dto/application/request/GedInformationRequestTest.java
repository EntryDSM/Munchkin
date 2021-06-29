package kr.hs.entrydsm.application.usecase.dto.application.request;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class GedInformationRequestTest {

    private static final GedInformationRequest LOW_SCORE = new GedInformationRequest(BigDecimal.valueOf(21.14));

    private static final GedInformationRequest MIDDLE_SCORE = new GedInformationRequest(BigDecimal.valueOf(50.50));

    private static final GedInformationRequest HIGH_SCORE = new GedInformationRequest(BigDecimal.valueOf(80.80));

    private static final GedInformationRequest REQUEST = new GedInformationRequest();

    @Test
    void getGedAverageScore() {
        assertNull(REQUEST.getGedAverageScore());
        assertEquals(LOW_SCORE.getGedAverageScore(), BigDecimal.valueOf(21.14));
        assertEquals(MIDDLE_SCORE.getGedAverageScore(), BigDecimal.valueOf(50.50));
        assertEquals(HIGH_SCORE.getGedAverageScore(), BigDecimal.valueOf(80.80));
    }
}