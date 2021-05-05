package kr.hs.entrydsm.user.entity;

import kr.hs.entrydsm.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByReceiptCode(long receiptCode);
    Page<User> findAllBy(Pageable pageable);
    Optional<User> findByTelephoneNumber(String telephoneNumber);
}
