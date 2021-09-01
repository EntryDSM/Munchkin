package kr.hs.entrydsm.user.entity.authcode;

import java.util.Optional;

public interface AuthCodeRedisRepository {
    Optional<AuthCode> findById(String email);
    AuthCode save(AuthCode authCode);
    void deleteByEmail(String email);
}
