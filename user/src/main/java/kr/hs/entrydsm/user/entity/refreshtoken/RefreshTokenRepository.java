package kr.hs.entrydsm.user.entity.refreshtoken;

import java.util.Optional;

public interface RefreshTokenRepository {
    Optional<RefreshToken> findById(long receiptCode);
    RefreshToken save(RefreshToken refreshToken);
}
