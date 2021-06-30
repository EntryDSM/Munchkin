package kr.hs.entrydsm.admin.auth;

import kr.hs.entrydsm.admin.entity.schedule.Schedule;
import kr.hs.entrydsm.admin.entity.schedule.ScheduleRepository;
import kr.hs.entrydsm.admin.entity.schedule.Type;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static kr.hs.entrydsm.admin.auth.ScheduleBaseTest.SCHEDULES;
import static kr.hs.entrydsm.admin.auth.ScheduleBaseTest.START_DATE;

public class MockScheduleRepositoryManager implements ScheduleRepository {
    @Override
    public List<Schedule> findAllBy() {
        return SCHEDULES;
    }

    @Override
    public Optional<Schedule> findByType(Type type) {
        return Optional.empty();
    }

    @Override
    public Optional<Schedule> findByYearAndType(String year, Type type) {
        return Optional.ofNullable(START_DATE);
    }
}
