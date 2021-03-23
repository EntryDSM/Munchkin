package kr.hs.entrydsm.user.entity.authcode;

import java.util.Optional;

public interface AuthCodeRedisRepository {
    Optional<AuthCode> findById(String phoneNumber);
    AuthCode save(AuthCode authCode);
}
