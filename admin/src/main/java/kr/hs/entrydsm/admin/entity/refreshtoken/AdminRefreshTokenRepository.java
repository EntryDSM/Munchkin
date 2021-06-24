package kr.hs.entrydsm.admin.entity.refreshtoken;

import java.util.Optional;

public interface AdminRefreshTokenRepository {
    Optional<RefreshToken> findByRefreshToken(String refreshToken);
}
