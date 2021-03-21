package kr.hs.entrydsm.user.infrastructure.database;

import kr.hs.entrydsm.user.entity.Status;
import kr.hs.entrydsm.user.entity.StatusRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepositoryManager extends CrudRepository<Status, Long>, StatusRepository {
}
