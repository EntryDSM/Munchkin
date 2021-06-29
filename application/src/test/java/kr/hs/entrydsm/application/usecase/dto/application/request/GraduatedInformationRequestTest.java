package kr.hs.entrydsm.application.usecase.dto.application.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraduatedInformationRequestTest {

    private static final GraduatedInformationRequest TESTER_1 =
            new GraduatedInformationRequest("0001234567", "1111111", "10529");

    private static final GraduatedInformationRequest TESTER_2 =
            new GraduatedInformationRequest("1110234567", "2222222", "20532");

    private static final GraduatedInformationRequest TESTER_3 =
            new GraduatedInformationRequest("2220134567", "3333333", "30131");

    private static final GraduatedInformationRequest REQUEST =
            new GraduatedInformationRequest();

    @Test
    void getSchoolTel() {
        assertEquals(TESTER_1.getSchoolTel(), "0001234567");
        assertEquals(TESTER_2.getSchoolTel(), "1110234567");
        assertEquals(TESTER_3.getSchoolTel(), "2220134567");
        assertNull(REQUEST.getSchoolTel());
    }

    @Test
    void getSchoolCode() {
        assertEquals(TESTER_1.getSchoolCode(), "1111111");
        assertEquals(TESTER_2.getSchoolCode(), "2222222");
        assertEquals(TESTER_3.getSchoolCode(), "3333333");
        assertNull(REQUEST.getSchoolCode());
    }

    @Test
    void getStudentNumber() {
        assertEquals(TESTER_1.getStudentNumber(), "10529");
        assertEquals(TESTER_2.getStudentNumber(), "20532");
        assertEquals(TESTER_3.getStudentNumber(), "30131");
        assertNull(REQUEST.getStudentNumber());
    }
}