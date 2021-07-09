package kr.hs.entrydsm.application.usecase.dto.application.response;

import kr.hs.entrydsm.application.usecase.dto.application.request.Information;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class GedInformationResponseTest {

    private static final Information INFORMATION = Information.builder()
            .name("test1")
            .sex("MALE")
            .birthday("20040728")
            .parentName("test1parent")
            .telephoneNumber("01012345678")
            .parentTel("01087654321")
            .homeTel("0510231564")
            .address("homeaddr")
            .detailAddress("thisismyhome")
            .postCode("12345")
            .photoFileName("test.jpg")
            .build();

    private static final GedInformationResponse RESPONSE_1 = new GedInformationResponse(BigDecimal.valueOf(21.14));

    private static final GedInformationResponse RESPONSE_2 = new GedInformationResponse(BigDecimal.valueOf(50.50));

    private static final GedInformationResponse RESPONSE_3 = new GedInformationResponse(BigDecimal.valueOf(80.80));

    @Test
    void setPhotoFileName() {
        RESPONSE_1.setPhotoFileName("test1.jpg");
        RESPONSE_2.setPhotoFileName("test2.jpg");
        RESPONSE_3.setPhotoFileName("test3.jpg");
    }

    @Test
    void setInformation() {
        RESPONSE_1.setInformation(INFORMATION);
        RESPONSE_2.setInformation(INFORMATION);
        RESPONSE_3.setInformation(INFORMATION);
    }

    @Test
    void getGedAverageScore() {
        assertEquals(RESPONSE_1.getGedAverageScore(), BigDecimal.valueOf(21.14));
        assertEquals(RESPONSE_2.getGedAverageScore(), BigDecimal.valueOf(50.50));
        assertEquals(RESPONSE_3.getGedAverageScore(), BigDecimal.valueOf(80.80));
    }

    @Test
    void setGedAverageScore() {
        RESPONSE_1.setGedAverageScore(BigDecimal.valueOf(21.14));
        RESPONSE_2.setGedAverageScore(BigDecimal.valueOf(50.50));
        RESPONSE_3.setGedAverageScore(BigDecimal.valueOf(80.80));
    }
}