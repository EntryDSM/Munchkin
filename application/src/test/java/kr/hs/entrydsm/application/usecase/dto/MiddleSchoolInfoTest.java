package kr.hs.entrydsm.application.usecase.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MiddleSchoolInfoTest {

    private static final MiddleSchoolInfo INFO_1 = MiddleSchoolInfo.builder()
            .middleSchool("해강중학교")
            .middleSchoolStudentNumber("30132")
            .yearOfGraduation(2020)
            .build();

    private static final MiddleSchoolInfo INFO_2 = MiddleSchoolInfo.builder()
            .middleSchool("대덕중학교")
            .middleSchoolStudentNumber("30133")
            .yearOfGraduation(2021)
            .build();

    private static final MiddleSchoolInfo INFO = new MiddleSchoolInfo();

    @Test
    void getYearOfGraduation() {
        assertEquals(INFO_1.getYearOfGraduation(), 2020);
        assertEquals(INFO_2.getYearOfGraduation(), 2021);
        assertNull(INFO.getYearOfGraduation());
    }

    @Test
    void getMiddleSchoolStudentNumber() {
        assertEquals(INFO_1.getMiddleSchoolStudentNumber(), "30132");
        assertEquals(INFO_2.getMiddleSchoolStudentNumber(), "30133");
        assertNull(INFO.getMiddleSchoolStudentNumber());
    }

    @Test
    void getMiddleSchool() {
        assertEquals(INFO_1.getMiddleSchool(), "해강중학교");
        assertEquals(INFO_2.getMiddleSchool(), "대덕중학교");
        assertNull(INFO.getMiddleSchool());
    }
}