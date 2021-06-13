package kr.hs.entrydsm.user.infrastructure.database;

import kr.hs.entrydsm.user.entity.refreshtoken.RefreshToken;
import kr.hs.entrydsm.user.entity.refreshtoken.RefreshTokenRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepositoryManager extends CrudRepository<RefreshToken, Long>, RefreshTokenRepository {
}
