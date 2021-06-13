package kr.hs.entrydsm.user.entity.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByReceiptCode(long receiptCode);
    Page<User> findAllBy(Pageable pageable);
    Optional<User> findByEmail(String email);
    User save(User user);
    boolean existsByEmail(String email);
}
