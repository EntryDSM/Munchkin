package kr.hs.entrydsm.application.usecase.dto.application.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraduatedInformationRequestTest {

    private static final GraduatedInformationRequest tester1 =
            new GraduatedInformationRequest("0001234567", "1111111", "10529");

    private static final GraduatedInformationRequest tester2 =
            new GraduatedInformationRequest("1110234567", "2222222", "20532");

    private static final GraduatedInformationRequest tester3 =
            new GraduatedInformationRequest("2220134567", "3333333", "30131");

    @Test
    void getSchoolTel() {
        assertEquals(tester1.getSchoolTel(), "0001234567");
        assertEquals(tester2.getSchoolTel(), "1110234567");
        assertEquals(tester3.getSchoolTel(), "2220134567");
    }

    @Test
    void getSchoolCode() {
        assertEquals(tester1.getSchoolCode(), "1111111");
        assertEquals(tester2.getSchoolCode(), "2222222");
        assertEquals(tester3.getSchoolCode(), "3333333");
    }

    @Test
    void getStudentNumber() {
        assertEquals(tester1.getStudentNumber(), "10529");
        assertEquals(tester2.getStudentNumber(), "20532");
        assertEquals(tester3.getStudentNumber(), "30131");
    }
}