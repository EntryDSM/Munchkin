package kr.hs.entrydsm.admin.auth;

import kr.hs.entrydsm.admin.entity.schedule.Schedule;
import kr.hs.entrydsm.admin.entity.schedule.ScheduleId;
import kr.hs.entrydsm.admin.entity.schedule.Type;
import kr.hs.entrydsm.admin.usecase.dto.schedule.Schedules;
import kr.hs.entrydsm.admin.usecase.dto.schedule.ScheduleRequest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class ScheduleBaseTest extends AdminBaseTest{

    protected static final ScheduleId START_SCHEDULE_ID = new ScheduleId("2021",Type.START_DATE);

    protected static final Schedule START_DATE = Schedule.builder()
            .year(START_SCHEDULE_ID.getYear())
            .type(START_SCHEDULE_ID.getType())
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

    protected static final ScheduleRequest SCHEDULE_REQUEST = ScheduleRequest.builder()
            .schedules(null)
            .build();

    protected static final List<Schedule> SCHEDULES = new ArrayList<>();

    protected static final Schedules SCHEDULES_DTO = Schedules.builder()
            .type(Type.START_DATE)
            .date("2021-06-28")
            .build();

    static {
        SCHEDULES.add(START_DATE);
        SCHEDULES.add(END_DATE);
        SCHEDULES.add(FIRST_ANNOUNCEMENT);
        SCHEDULES.add(INTERVIEW);
        SCHEDULES.add(SECOND_ANNOUNCEMENT);
    }

}
