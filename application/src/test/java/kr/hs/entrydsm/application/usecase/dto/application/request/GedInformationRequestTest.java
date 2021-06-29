package kr.hs.entrydsm.application.usecase.dto.application.request;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class GedInformationRequestTest {

    private static final GedInformationRequest lowScore = new GedInformationRequest(BigDecimal.valueOf(21.14));

    private static final GedInformationRequest middleScore = new GedInformationRequest(BigDecimal.valueOf(50.50));

    private static final GedInformationRequest highScore = new GedInformationRequest(BigDecimal.valueOf(80.80));

    @Test
    void getGedAverageScore() {
        assertEquals(lowScore.getGedAverageScore(), BigDecimal.valueOf(21.14));
        assertEquals(middleScore.getGedAverageScore(), BigDecimal.valueOf(50.50));
        assertEquals(highScore.getGedAverageScore(), BigDecimal.valueOf(80.80));
    }
}