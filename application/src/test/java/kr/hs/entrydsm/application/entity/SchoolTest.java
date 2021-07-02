package kr.hs.entrydsm.application.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SchoolTest {

    private static final School SCHOOL_1 = School.builder()
            .address("대전광역시")
            .code("1111111")
            .name("대덕중학교")
            .information("정보정보")
            .build();

    private static final School SCHOOL_2 = School.builder()
            .address("부산광역시")
            .code("2222222")
            .name("부산중학교")
            .information("정보정보정보")
            .build();

    private static final School SCHOOL = new School();

    @Test
    void getCode() {
        assertEquals(SCHOOL_1.getCode(), "1111111");
        assertEquals(SCHOOL_2.getCode(), "2222222");
        assertNull(SCHOOL.getCode());
    }

    @Test
    void getName() {
        assertEquals(SCHOOL_1.getName(), "대덕중학교");
        assertEquals(SCHOOL_2.getName(), "부산중학교");
        assertNull(SCHOOL.getName());
    }

    @Test
    void getInformation() {
        assertEquals(SCHOOL_1.getInformation(), "정보정보");
        assertEquals(SCHOOL_2.getInformation(), "정보정보정보");
        assertNull(SCHOOL.getInformation());
    }

    @Test
    void getAddress() {
        assertEquals(SCHOOL_1.getAddress(), "대전광역시");
        assertEquals(SCHOOL_2.getAddress(), "부산광역시");
        assertNull(SCHOOL.getAddress());
    }
}