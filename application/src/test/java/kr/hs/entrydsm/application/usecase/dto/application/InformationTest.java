package kr.hs.entrydsm.application.usecase.dto.application;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InformationTest {

    private static final Information firstInformation =
            Information.builder()
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

    @Test
    void getName() {
        assertEquals(firstInformation.getName(), "test1");
    }

    @Test
    void getSex() {
        assertEquals(firstInformation.getSex(), "MALE");
    }

    @Test
    void getBirthday() {
        assertEquals(firstInformation.getBirthday(), "20040728");
    }

    @Test
    void getParentName() {
        assertEquals(firstInformation.getParentName(), "test1parent");
    }

    @Test
    void getTelephoneNumber() {
        assertEquals(firstInformation.getTelephoneNumber(), "01012345678");
    }

    @Test
    void getParentTel() {
        assertEquals(firstInformation.getParentTel(), "01087654321");
    }

    @Test
    void getHomeTel() {
        assertEquals(firstInformation.getHomeTel(), "0510231564");
    }

    @Test
    void getAddress() {
        assertEquals(firstInformation.getAddress(), "homeaddr");
    }

    @Test
    void getDetailAddress() {
        assertEquals(firstInformation.getDetailAddress(), "thisismyhome");
    }

    @Test
    void getPostCode() {
        assertEquals(firstInformation.getPostCode(), "12345");
    }

    @Test
    void getPhotoFileName() {
        assertEquals(firstInformation.getPhotoFileName(), "test.jpg");
    }
}