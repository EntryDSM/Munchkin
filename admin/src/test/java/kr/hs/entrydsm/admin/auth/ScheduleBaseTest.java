package kr.hs.entrydsm.admin.auth;

import kr.hs.entrydsm.admin.entity.schedule.Schedule;
import kr.hs.entrydsm.admin.entity.schedule.ScheduleRepository;
import kr.hs.entrydsm.admin.entity.schedule.Type;
import kr.hs.entrydsm.admin.infrastructure.database.ScheduleRepositoryManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

public abstract class ScheduleBaseTest extends AdminBaseTest{

    protected static final ScheduleRepository scheduleRepository = new MockScheduleRepositoryManager();
    protected static final ScheduleRepositoryManager scheduleRepositoryManager = mock(ScheduleRepositoryManager.class);

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

    protected static final List<Schedule> SCHEDULES = new ArrayList<>();

    static {
        SCHEDULES.add(START_DATE);
        SCHEDULES.add(END_DATE);
        SCHEDULES.add(FIRST_ANNOUNCEMENT);
        SCHEDULES.add(INTERVIEW);
        SCHEDULES.add(SECOND_ANNOUNCEMENT);
    }

}
