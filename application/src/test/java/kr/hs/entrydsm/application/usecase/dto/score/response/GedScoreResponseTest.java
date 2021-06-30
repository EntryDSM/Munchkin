package kr.hs.entrydsm.application.usecase.dto.score.response;

import kr.hs.entrydsm.application.usecase.dto.score.request.GedScoreRequest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class GedScoreResponseTest {

    private static final GedScoreResponse RESPONSE_1 = new GedScoreResponse(BigDecimal.valueOf(70.50));

    private static final GedScoreResponse RESPONSE_2 = new GedScoreResponse(BigDecimal.valueOf(85.50));

    private static final GedScoreResponse RESPONSE_3 = new GedScoreResponse(BigDecimal.valueOf(100));

    private static final GedScoreResponse RESPONSE = new GedScoreResponse();

    @Test
    void getGedAverageScore() {
        assertEquals(RESPONSE_1.getGedAverageScore(), BigDecimal.valueOf(70.50));
        assertEquals(RESPONSE_2.getGedAverageScore(), BigDecimal.valueOf(85.50));
        assertEquals(RESPONSE_3.getGedAverageScore(), BigDecimal.valueOf(100));
        assertNull(RESPONSE.getGedAverageScore());
    }
}