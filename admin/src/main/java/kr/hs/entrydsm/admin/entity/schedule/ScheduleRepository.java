package kr.hs.entrydsm.admin.entity.schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {
    List<Schedule> findAllBy();
    Optional<Schedule> findByYearAndType(String year, Type type);
}
