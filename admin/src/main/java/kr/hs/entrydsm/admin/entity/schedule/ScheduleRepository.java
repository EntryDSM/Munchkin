package kr.hs.entrydsm.admin.entity.schedule;

import kr.hs.entrydsm.admin.entity.schedule.Schedule;

import java.util.List;

public interface ScheduleRepository {
    List<Schedule> findAllBy();
}
