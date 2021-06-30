package kr.hs.entrydsm.application.usecase.dto.score.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EtcScoreResponseTest {

    private static final EtcScoreResponse RESPONSE_1 = EtcScoreResponse.builder()
            .dayAbsenceCount(0)
            .earlyLeaveCount(0)
            .latenessCount(0)
            .lectureAbsenceCount(0)
            .volunteerTime(0)
            .build();

    private static final EtcScoreResponse RESPONSE_2 = EtcScoreResponse.builder()
            .dayAbsenceCount(5)
            .earlyLeaveCount(5)
            .latenessCount(5)
            .lectureAbsenceCount(5)
            .volunteerTime(5)
            .build();


    private static final EtcScoreResponse RESPONSE_3 = EtcScoreResponse.builder()
            .dayAbsenceCount(9)
            .earlyLeaveCount(9)
            .latenessCount(9)
            .lectureAbsenceCount(9)
            .volunteerTime(9)
            .build();

    private static final EtcScoreResponse RESPONSE = new EtcScoreResponse();

    @Test
    void getVolunteerTime() {
        assertEquals(RESPONSE_1.getVolunteerTime(), 0);
        assertEquals(RESPONSE_2.getVolunteerTime(), 5);
        assertEquals(RESPONSE_3.getVolunteerTime(), 9);
        assertNull(RESPONSE.getVolunteerTime());
    }

    @Test
    void getDayAbsenceCount() {
        assertEquals(RESPONSE_1.getDayAbsenceCount(), 0);
        assertEquals(RESPONSE_2.getDayAbsenceCount(), 5);
        assertEquals(RESPONSE_3.getDayAbsenceCount(), 9);
        assertNull(RESPONSE.getDayAbsenceCount());
    }

    @Test
    void getLectureAbsenceCount() {
        assertEquals(RESPONSE_1.getLectureAbsenceCount(), 0);
        assertEquals(RESPONSE_2.getLectureAbsenceCount(), 5);
        assertEquals(RESPONSE_3.getLectureAbsenceCount(), 9);
        assertNull(RESPONSE.getLectureAbsenceCount());
    }

    @Test
    void getLatenessCount() {
        assertEquals(RESPONSE_1.getLatenessCount(), 0);
        assertEquals(RESPONSE_2.getLatenessCount(), 5);
        assertEquals(RESPONSE_3.getLatenessCount(), 9);
        assertNull(RESPONSE.getLatenessCount());
    }

    @Test
    void getEarlyLeaveCount() {
        assertEquals(RESPONSE_1.getEarlyLeaveCount(), 0);
        assertEquals(RESPONSE_2.getEarlyLeaveCount(), 5);
        assertEquals(RESPONSE_3.getEarlyLeaveCount(), 9);
        assertNull(RESPONSE.getEarlyLeaveCount());
    }
}