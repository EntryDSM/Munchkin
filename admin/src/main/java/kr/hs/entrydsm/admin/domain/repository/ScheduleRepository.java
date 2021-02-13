package kr.hs.entrydsm.admin.domain.repository;

import kr.hs.entrydsm.admin.domain.entity.Schedule;

import java.util.List;

public interface ScheduleRepository {
    List<Schedule> findAllBy();
}
