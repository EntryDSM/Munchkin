package kr.hs.entrydsm.application.infrastructure.database;

import kr.hs.entrydsm.application.entity.Applicant;
import kr.hs.entrydsm.application.entity.Application;
import kr.hs.entrydsm.application.entity.ApplicationRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepositoryManager<T extends Application> extends ApplicationRepository, CrudRepository<T, Long> {
}
