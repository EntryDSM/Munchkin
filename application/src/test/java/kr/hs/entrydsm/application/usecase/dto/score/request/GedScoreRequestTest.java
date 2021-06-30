package kr.hs.entrydsm.application.usecase.dto.score.request;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class GedScoreRequestTest {

    private static final GedScoreRequest REQUEST_1 = new GedScoreRequest(BigDecimal.valueOf(70.50));

    private static final GedScoreRequest REQUEST_2 = new GedScoreRequest(BigDecimal.valueOf(85.50));

    private static final GedScoreRequest REQUEST_3 = new GedScoreRequest(BigDecimal.valueOf(100));

    private static final GedScoreRequest REQUEST = new GedScoreRequest();

    @Test
    void getGedAverageScore() {
        assertEquals(REQUEST_1.getGedAverageScore(), BigDecimal.valueOf(70.50));
        assertEquals(REQUEST_2.getGedAverageScore(), BigDecimal.valueOf(85.50));
        assertEquals(REQUEST_3.getGedAverageScore(), BigDecimal.valueOf(100));
        assertNull(REQUEST.getGedAverageScore());
    }
}