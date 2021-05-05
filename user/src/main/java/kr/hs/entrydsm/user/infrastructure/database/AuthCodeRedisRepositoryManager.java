package kr.hs.entrydsm.user.infrastructure.database;

import kr.hs.entrydsm.user.entity.authcode.AuthCode;
import kr.hs.entrydsm.user.entity.authcode.AuthCodeRedisRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthCodeRedisRepositoryManager extends CrudRepository<AuthCode, String>, AuthCodeRedisRepository {
}
