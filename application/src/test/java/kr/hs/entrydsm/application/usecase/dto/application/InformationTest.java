package kr.hs.entrydsm.application.usecase.dto.application;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InformationTest {

    private static final Information FIRST_INFORMATION =
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
        assertEquals(FIRST_INFORMATION.getName(), "test1");
    }

    @Test
    void getSex() {
        assertEquals(FIRST_INFORMATION.getSex(), "MALE");
    }

    @Test
    void getBirthday() {
        assertEquals(FIRST_INFORMATION.getBirthday(), "20040728");
    }

    @Test
    void getParentName() {
        assertEquals(FIRST_INFORMATION.getParentName(), "test1parent");
    }

    @Test
    void getTelephoneNumber() {
        assertEquals(FIRST_INFORMATION.getTelephoneNumber(), "01012345678");
    }

    @Test
    void getParentTel() {
        assertEquals(FIRST_INFORMATION.getParentTel(), "01087654321");
    }

    @Test
    void getHomeTel() {
        assertEquals(FIRST_INFORMATION.getHomeTel(), "0510231564");
    }

    @Test
    void getAddress() {
        assertEquals(FIRST_INFORMATION.getAddress(), "homeaddr");
    }

    @Test
    void getDetailAddress() {
        assertEquals(FIRST_INFORMATION.getDetailAddress(), "thisismyhome");
    }

    @Test
    void getPostCode() {
        assertEquals(FIRST_INFORMATION.getPostCode(), "12345");
    }

    @Test
    void getPhotoFileName() {
        assertEquals(FIRST_INFORMATION.getPhotoFileName(), "test.jpg");
    }
}