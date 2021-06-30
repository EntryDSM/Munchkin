package kr.hs.entrydsm.application.usecase.dto.application.response;

import kr.hs.entrydsm.application.usecase.dto.application.Information;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraduatedInformationResponseTest {

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

    private static final GraduatedInformationResponse RESPONSE_1 =
            new GraduatedInformationResponse("0001234567", "1111111", "학교",
                    "10529", true);

    private static final GraduatedInformationResponse RESPONSE_2 =
            new GraduatedInformationResponse("1110234567", "2222222", "하악교",
                    "20532", true);

    private static final GraduatedInformationResponse RESPONSE_3 =
            new GraduatedInformationResponse("2220134567", "3333333", "학꾜",
                    "30131", false);

    private static final GraduatedInformationResponse RESPONSE =
            new GraduatedInformationResponse();

    @Test
    void setIsGraduated() {
        RESPONSE_1.setIsGraduated(true);
        RESPONSE_2.setIsGraduated(true);
        RESPONSE_3.setIsGraduated(false);
        RESPONSE.setIsGraduated(false);
    }

    @Test
    void setInformation() {
        RESPONSE_1.setInformation(INFORMATION);
        RESPONSE_2.setInformation(INFORMATION);
        RESPONSE_3.setInformation(INFORMATION);
        RESPONSE.setInformation(INFORMATION);
    }

    @Test
    void setPhotoFileName() {
        RESPONSE_1.setPhotoFileName("test1.jpg");
        RESPONSE_2.setPhotoFileName("test2.jpg");
        RESPONSE_3.setPhotoFileName("test3.jpg");
        RESPONSE.setPhotoFileName("test4.jpg");
    }

    @Test
    void setSchoolName() {
        RESPONSE_1.setSchoolName("학교");
        RESPONSE_2.setSchoolName("하악교");
        RESPONSE_3.setSchoolName("학꾜");
    }

    @Test
    void getSchoolTel() {
        assertEquals(RESPONSE_1.getSchoolTel(), "0001234567");
        assertEquals(RESPONSE_2.getSchoolTel(), "1110234567");
        assertEquals(RESPONSE_3.getSchoolTel(), "2220134567");
    }

    @Test
    void getSchoolCode() {
        assertEquals(RESPONSE_1.getSchoolCode(), "1111111");
        assertEquals(RESPONSE_2.getSchoolCode(), "2222222");
        assertEquals(RESPONSE_3.getSchoolCode(), "3333333");
    }

    @Test
    void getSchoolName() {
        assertEquals(RESPONSE_1.getSchoolName(), "학교");
        assertEquals(RESPONSE_2.getSchoolName(), "하악교");
        assertEquals(RESPONSE_3.getSchoolName(), "학꾜");
    }

    @Test
    void getStudentNumber() {
        assertEquals(RESPONSE_1.getStudentNumber(), "10529");
        assertEquals(RESPONSE_2.getStudentNumber(), "20532");
        assertEquals(RESPONSE_3.getStudentNumber(), "30131");
    }

    @Test
    void isGraduated() {
        assertTrue(RESPONSE_1.isGraduated());
        assertTrue(RESPONSE_2.isGraduated());
        assertFalse(RESPONSE_3.isGraduated());
    }

    @Test
    void setSchoolTel() {
        RESPONSE_1.setSchoolTel("0001234567");
        RESPONSE_2.setSchoolTel("1110234567");
        RESPONSE_3.setSchoolTel("2220134567");
    }

    @Test
    void setSchoolCode() {
        RESPONSE_1.setSchoolCode("1111111");
        RESPONSE_2.setSchoolTel("2222222");
        RESPONSE_3.setSchoolCode("3333333");
    }

    @Test
    void setStudentNumber() {
        RESPONSE_1.setStudentNumber("10529");
        RESPONSE_2.setStudentNumber("20532");
        RESPONSE_3.setStudentNumber("30131");
    }
}