package kr.hs.entrydsm.admin.entity;

import kr.hs.entrydsm.admin.entity.Schedule;

import java.util.List;

public interface ScheduleRepository {
    List<Schedule> findAllBy();
}
