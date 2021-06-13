package kr.hs.entrydsm.user.entity.authcode;

import java.util.Optional;

public interface AuthCodeLimitRedisRepository {
    Optional<AuthCodeLimit> findById(String email);
    AuthCodeLimit save(AuthCodeLimit authCodeLimit);
}
