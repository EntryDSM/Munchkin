package kr.hs.entrydsm.application.infrastructure.database;

import kr.hs.entrydsm.application.domain.entity.Applicant;
import kr.hs.entrydsm.application.domain.entity.Application;
import kr.hs.entrydsm.application.domain.repository.ApplicationRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationRepositoryManager<T extends Application> extends ApplicationRepository, CrudRepository<T, Long> {

    @Override
    Applicant findByReceiptCode(Long receiptCode);
}
