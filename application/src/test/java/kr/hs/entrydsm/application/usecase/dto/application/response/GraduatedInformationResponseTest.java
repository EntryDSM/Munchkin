package kr.hs.entrydsm.application.usecase.dto.application.response;

import kr.hs.entrydsm.application.usecase.dto.application.Information;
import kr.hs.entrydsm.application.usecase.dto.application.request.GraduatedInformationRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraduatedInformationResponseTest {

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

    private static final GraduatedInformationResponse response1 =
            new GraduatedInformationResponse("0001234567", "1111111",
                    "10529", true);

    private static final GraduatedInformationResponse response2 =
            new GraduatedInformationResponse("1110234567", "2222222",
                    "20532", true);

    private static final GraduatedInformationResponse response3 =
            new GraduatedInformationResponse("2220134567", "3333333",
                    "30131", false);

    private static final GraduatedInformationResponse response4 =
            new GraduatedInformationResponse();

    @Test
    void setIsGraduated() {
        response1.setIsGraduated(true);
        response2.setIsGraduated(true);
        response3.setIsGraduated(false);
        response4.setIsGraduated(false);
    }

    @Test
    void setInformation() {
        response1.setInformation(information);
        response2.setInformation(information);
        response3.setInformation(information);
        response4.setInformation(information);
    }

    @Test
    void setPhotoFileName() {
        response1.setPhotoFileName("test1.jpg");
        response2.setPhotoFileName("test2.jpg");
        response3.setPhotoFileName("test3.jpg");
        response4.setPhotoFileName("test4.jpg");
    }

    @Test
    void getSchoolTel() {
        assertEquals(response1.getSchoolTel(), "0001234567");
        assertEquals(response2.getSchoolTel(), "1110234567");
        assertEquals(response3.getSchoolTel(), "2220134567");
    }

    @Test
    void getSchoolCode() {
        assertEquals(response1.getSchoolCode(), "1111111");
        assertEquals(response2.getSchoolCode(), "2222222");
        assertEquals(response3.getSchoolCode(), "3333333");
    }

    @Test
    void getStudentNumber() {
        assertEquals(response1.getStudentNumber(), "10529");
        assertEquals(response2.getStudentNumber(), "20532");
        assertEquals(response3.getStudentNumber(), "30131");
    }

    @Test
    void isGraduated() {
        assertTrue(response1.isGraduated());
        assertTrue(response2.isGraduated());
        assertFalse(response3.isGraduated());
    }

    @Test
    void setSchoolTel() {
        response1.setSchoolTel("0001234567");
        response2.setSchoolTel("1110234567");
        response3.setSchoolTel("2220134567");
    }

    @Test
    void setSchoolCode() {
        response1.setSchoolCode("1111111");
        response2.setSchoolTel("2222222");
        response3.setSchoolCode("3333333");
    }

    @Test
    void setStudentNumber() {
        response1.setStudentNumber("10529");
        response2.setStudentNumber("20532");
        response3.setStudentNumber("30131");
    }
}