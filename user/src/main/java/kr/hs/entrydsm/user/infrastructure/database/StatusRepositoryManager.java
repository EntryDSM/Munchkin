package kr.hs.entrydsm.user.infrastructure.database;

import kr.hs.entrydsm.user.entity.status.Status;
import kr.hs.entrydsm.user.entity.status.StatusRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusRepositoryManager extends CrudRepository<Status, Long>, StatusRepository {
    List<Status> findAllByIsSubmitTrue();
    List<Status> findAllByIsFirstRoundPassTrue();
}
