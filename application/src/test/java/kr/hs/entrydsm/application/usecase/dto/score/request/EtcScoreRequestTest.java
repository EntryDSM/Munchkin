package kr.hs.entrydsm.application.usecase.dto.score.request;

import kr.hs.entrydsm.application.entity.GraduationApplication;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EtcScoreRequestTest {

    private static final GraduationApplication APPLICATION = new GraduationApplication();

    private static final EtcScoreRequest REQUEST_1 = EtcScoreRequest.builder()
            .dayAbsenceCount(0)
            .earlyLeaveCount(0)
            .latenessCount(0)
            .lectureAbsenceCount(0)
            .volunteerTime(0)
            .build();

    private static final EtcScoreRequest REQUEST_2 = EtcScoreRequest.builder()
            .dayAbsenceCount(5)
            .earlyLeaveCount(5)
            .latenessCount(5)
            .lectureAbsenceCount(5)
            .volunteerTime(5)
            .build();

    private static final EtcScoreRequest REQUEST_3 = EtcScoreRequest.builder()
            .dayAbsenceCount(9)
            .earlyLeaveCount(9)
            .latenessCount(9)
            .lectureAbsenceCount(9)
            .volunteerTime(9)
            .build();

    private static final EtcScoreRequest REQUEST = new EtcScoreRequest();

    @Test
    void getVolunteerTime() {
        assertEquals(REQUEST_1.getVolunteerTime(), 0);
        assertEquals(REQUEST_2.getVolunteerTime(), 5);
        assertEquals(REQUEST_3.getVolunteerTime(), 9);
        assertNull(REQUEST.getVolunteerTime());
    }

    @Test
    void getDayAbsenceCount() {
        assertEquals(REQUEST_1.getDayAbsenceCount(), 0);
        assertEquals(REQUEST_2.getDayAbsenceCount(), 5);
        assertEquals(REQUEST_3.getDayAbsenceCount(), 9);
        assertNull(REQUEST.getDayAbsenceCount());
    }

    @Test
    void getLectureAbsenceCount() {
        assertEquals(REQUEST_1.getLectureAbsenceCount(), 0);
        assertEquals(REQUEST_2.getLectureAbsenceCount(), 5);
        assertEquals(REQUEST_3.getLectureAbsenceCount(), 9);
        assertNull(REQUEST.getLectureAbsenceCount());
    }

    @Test
    void getLatenessCount() {
        assertEquals(REQUEST_1.getLatenessCount(), 0);
        assertEquals(REQUEST_2.getLatenessCount(), 5);
        assertEquals(REQUEST_3.getLatenessCount(), 9);
        assertNull(REQUEST.getLatenessCount());
    }

    @Test
    void getEarlyLeaveCount() {
        assertEquals(REQUEST_1.getEarlyLeaveCount(), 0);
        assertEquals(REQUEST_2.getEarlyLeaveCount(), 5);
        assertEquals(REQUEST_3.getEarlyLeaveCount(), 9);
        assertNull(REQUEST.getEarlyLeaveCount());
    }

    @Test
    void from() {
        assertNull(EtcScoreRequest.from(APPLICATION).getDayAbsenceCount());
    }
}