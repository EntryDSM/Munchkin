package kr.hs.entrydsm.user.infrastructure.database;

import kr.hs.entrydsm.user.entity.AuthCodeLimit;
import kr.hs.entrydsm.user.entity.AuthCodeLimitRedisRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthCodeLimitRedisRepositoryManager extends CrudRepository<AuthCodeLimit, String>, AuthCodeLimitRedisRepository {
}
