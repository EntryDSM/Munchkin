package kr.hs.entrydsm.admin.domain.repository;

import kr.hs.entrydsm.admin.domain.entity.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepostory {
    Optional<RefreshToken> findByRefreshToken(String refreshToken);
}
