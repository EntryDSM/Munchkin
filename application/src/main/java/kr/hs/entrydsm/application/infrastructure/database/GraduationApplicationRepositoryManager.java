package kr.hs.entrydsm.application.infrastructure.database;

import kr.hs.entrydsm.application.entity.GraduationApplication;
import kr.hs.entrydsm.application.entity.GraduationApplicationRepository;
import org.springframework.data.repository.CrudRepository;

public interface GraduationApplicationRepositoryManager extends GraduationApplicationRepository, CrudRepository<GraduationApplication, Long> {
}
