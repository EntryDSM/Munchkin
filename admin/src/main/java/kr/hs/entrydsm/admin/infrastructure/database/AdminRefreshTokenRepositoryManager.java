package kr.hs.entrydsm.admin.infrastructure.database;

import kr.hs.entrydsm.admin.entity.refreshtoken.AdminRefreshTokenRepository;
import kr.hs.entrydsm.admin.entity.refreshtoken.AdminRefreshToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRefreshTokenRepositoryManager extends CrudRepository<AdminRefreshToken, String>, AdminRefreshTokenRepository {
    @Override
    Optional<AdminRefreshToken> findByRefreshToken(String refreshToken);
}
