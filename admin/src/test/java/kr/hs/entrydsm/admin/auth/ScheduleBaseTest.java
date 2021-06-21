package kr.hs.entrydsm.admin.auth;

import kr.hs.entrydsm.admin.entity.schedule.Schedule;
import kr.hs.entrydsm.admin.entity.schedule.Type;

import java.time.LocalDate;

public class ScheduleBaseTest {

    protected static final Schedule START_DATE = Schedule.builder()
            .year("2021")
            .type(Type.START_DATE)
            .date(LocalDate.of(2021,10,18))
            .build();

    protected static final Schedule END_DATE = Schedule.builder()
            .year("2021")
            .type(Type.END_DATE)
            .date(LocalDate.of(2021,10,21))
            .build();

    protected static final Schedule FIRST_ANNOUNCEMENT = Schedule.builder()
            .year("2021")
            .type(Type.FIRST_ANNOUNCEMENT)
            .date(LocalDate.of(2021,10,25))
            .build();

    protected static final Schedule SECOND_ANNOUNCEMENT = Schedule.builder()
            .year("2021")
            .type(Type.SECOND_ANNOUNCEMENT)
            .date(LocalDate.of(2021,11,4))
            .build();

    protected static final Schedule INTERVIEW = Schedule.builder()
            .year("2021")
            .type(Type.INTERVIEW)
            .date(LocalDate.of(2021,10,29))
            .build();
}
