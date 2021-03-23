package kr.hs.entrydsm.user.entity;

import java.util.Optional;

public interface AuthCodeRedisRepository {
    Optional<AuthCode> findById(String phoneNumber);
    AuthCode save(AuthCode authCode);
}
