package kr.hs.entrydsm.application.usecase.dto.application.response;

import kr.hs.entrydsm.application.usecase.dto.application.Information;
import kr.hs.entrydsm.application.usecase.dto.application.request.GedInformationRequest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class GedInformationResponseTest {

    private static final Information information = Information.builder()
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

    private static final GedInformationResponse response1 = new GedInformationResponse(BigDecimal.valueOf(21.14));

    private static final GedInformationResponse response2 = new GedInformationResponse(BigDecimal.valueOf(50.50));

    private static final GedInformationResponse response3 = new GedInformationResponse(BigDecimal.valueOf(80.80));

    @Test
    void setPhotoFileName() {
        response1.setPhotoFileName("test1.jpg");
        response2.setPhotoFileName("test2.jpg");
        response3.setPhotoFileName("test3.jpg");
    }

    @Test
    void setInformation() {
        response1.setInformation(information);
        response2.setInformation(information);
        response3.setInformation(information);
    }

    @Test
    void getGedAverageScore() {
        assertEquals(response1.getGedAverageScore(), BigDecimal.valueOf(21.14));
        assertEquals(response2.getGedAverageScore(), BigDecimal.valueOf(50.50));
        assertEquals(response3.getGedAverageScore(), BigDecimal.valueOf(80.80));
    }

    @Test
    void setGedAverageScore() {
        response1.setGedAverageScore(BigDecimal.valueOf(21.14));
        response2.setGedAverageScore(BigDecimal.valueOf(50.50));
        response3.setGedAverageScore(BigDecimal.valueOf(80.80));
    }
}