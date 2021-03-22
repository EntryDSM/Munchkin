package kr.hs.entrydsm.user.entity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthCodeRedisRepository extends CrudRepository<AuthCode, String> {
    Optional<AuthCode> findByPhoneNumberAndCode(String phoneNumber, String code);
}
