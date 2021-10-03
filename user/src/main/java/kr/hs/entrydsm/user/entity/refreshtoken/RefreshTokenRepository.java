package kr.hs.entrydsm.user.entity.refreshtoken;

import java.util.Optional;

public interface RefreshTokenRepository {
    Optional<RefreshToken> findById(long receiptCode);
    Optional<RefreshToken> findByToken(String token);
    RefreshToken save(RefreshToken refreshToken);
}
