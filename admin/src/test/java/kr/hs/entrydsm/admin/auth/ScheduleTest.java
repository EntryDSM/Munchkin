package kr.hs.entrydsm.admin.auth;

import kr.hs.entrydsm.admin.entity.schedule.Type;
import kr.hs.entrydsm.admin.usecase.dto.request.ScheduleRequest;
import kr.hs.entrydsm.admin.usecase.exception.ScheduleNotFoundException;
import kr.hs.entrydsm.admin.usecase.schedule.ScheduleService;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("admin-schedule")
@SpringBootTest(classes = ScheduleService.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ScheduleTest extends ScheduleBaseTest {

    @MockBean
    ScheduleService scheduleService;

    @Test
    @Order(1)
    public void checkStartDate() {
        assertTrue(START_DATE.getYear().equals("2021"));
        assertTrue(START_DATE.getType().equals(Type.START_DATE));
        assertTrue(START_DATE.getDate().equals(LocalDate.parse("2021-10-18")));
    }

    @Test
    @Order(1)
    public void checkEndDate() {
        assertTrue(END_DATE.getYear().equals("2021"));
        assertFalse(END_DATE.getType().equals(Type.START_DATE));
        assertTrue(END_DATE.getType().equals(Type.END_DATE));
        assertTrue(END_DATE.getDate().equals(LocalDate.parse("2021-10-21")));
    }

    @Test
    @Order(1)
    public void checkFistAnnouncement() {
        assertTrue(FIRST_ANNOUNCEMENT.getYear().equals("2021"));
        assertTrue(FIRST_ANNOUNCEMENT.getType().equals(Type.FIRST_ANNOUNCEMENT));
        assertFalse(FIRST_ANNOUNCEMENT.getDate().equals(LocalDate.parse("2021-10-21")));
        assertTrue(FIRST_ANNOUNCEMENT.getDate().equals(LocalDate.parse("2021-10-25")));
    }

    @Test
    @Order(1)
    public void checkSecondAnnouncement() {
        assertTrue(SECOND_ANNOUNCEMENT.getYear().equals("2021"));
        assertTrue(SECOND_ANNOUNCEMENT.getType().equals(Type.SECOND_ANNOUNCEMENT));
        assertTrue(SECOND_ANNOUNCEMENT.getDate().equals(LocalDate.parse("2021-11-04")));
    }

    @Test
    @Order(1)
    public void checkInterview() {
        assertTrue(INTERVIEW.getYear().equals("2021"));
        assertTrue(INTERVIEW.getType().equals(Type.INTERVIEW));
        assertTrue(INTERVIEW.getDate().equals(LocalDate.parse("2021-10-29")));
    }

    @Test
    @Order(2)
    public void modify_schedule() {
        try {
            scheduleService.updateSchedules(new ScheduleRequest("2021", Type.START_DATE, "2021-06-22"));
        } catch (ScheduleNotFoundException e) {
        }
    }

    @Test
    @Order(1)
    public void get_schedule() {
        scheduleService.getSchedules();
    }

}
