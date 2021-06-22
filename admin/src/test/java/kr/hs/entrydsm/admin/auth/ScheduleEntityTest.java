package kr.hs.entrydsm.admin.auth;

import kr.hs.entrydsm.admin.entity.schedule.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@DisplayName("일정 테스트")
public class ScheduleEntityTest extends ScheduleBaseTest {

    @Test
    public void checkStartDate() {
        assertTrue(START_DATE.getYear().equals("2021"));
        assertTrue(START_DATE.getType().equals(Type.START_DATE));
        assertTrue(START_DATE.getDate().equals(LocalDate.parse("2021-10-18")));
    }

    @Test
    public void checkEndDate() {
        assertTrue(END_DATE.getYear().equals("2021"));
        assertFalse(END_DATE.getType().equals(Type.START_DATE));
        assertTrue(END_DATE.getType().equals(Type.END_DATE));
        assertTrue(END_DATE.getDate().equals(LocalDate.parse("2021-10-21")));
    }

    @Test
    public void checkFistAnnouncement() {
        assertTrue(FIRST_ANNOUNCEMENT.getYear().equals("2021"));
        assertTrue(FIRST_ANNOUNCEMENT.getType().equals(Type.FIRST_ANNOUNCEMENT));
        assertFalse(FIRST_ANNOUNCEMENT.getDate().equals(LocalDate.parse("2021-10-21")));
        assertTrue(FIRST_ANNOUNCEMENT.getDate().equals(LocalDate.parse("2021-10-25")));
    }

    @Test
    public void checkSecondAnnouncement() {
        assertTrue(SECOND_ANNOUNCEMENT.getYear().equals("2021"));
        assertTrue(SECOND_ANNOUNCEMENT.getType().equals(Type.SECOND_ANNOUNCEMENT));
        assertTrue(SECOND_ANNOUNCEMENT.getDate().equals(LocalDate.parse("2021-11-04")));
    }

    @Test
    public void checkInterview() {
        assertTrue(INTERVIEW.getYear().equals("2021"));
        assertTrue(INTERVIEW.getType().equals(Type.INTERVIEW));
        assertTrue(INTERVIEW.getDate().equals(LocalDate.parse("2021-10-29")));
    }
}
