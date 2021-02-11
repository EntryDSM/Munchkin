package kr.hs.entrydsm.admin.infrastructure.database;

import kr.hs.entrydsm.admin.domain.entity.Schedule;
import kr.hs.entrydsm.admin.domain.repository.ScheduleRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepositoryManager extends JpaRepository<Schedule, String>, ScheduleRepository {

    @Override
    List<Schedule> findAllBy();
}
