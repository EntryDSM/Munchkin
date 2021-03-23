package kr.hs.entrydsm.user.entity;

import java.util.Optional;

public interface AuthCodeLimitRedisRepository {
    Optional<AuthCodeLimit> findById(String phoneNumber);
    AuthCodeLimit save(AuthCodeLimit authCodeLimit);
}
