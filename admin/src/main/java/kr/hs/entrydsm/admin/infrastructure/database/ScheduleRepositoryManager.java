package kr.hs.entrydsm.admin.infrastructure.database;

import kr.hs.entrydsm.admin.entity.Schedule;
import kr.hs.entrydsm.admin.entity.ScheduleRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepositoryManager extends CrudRepository<Schedule, String>, ScheduleRepository {

    @Override
    List<Schedule> findAllBy();
}