package kr.hs.entrydsm.user.entity.user;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByReceiptCode(long receiptCode);
    Optional<User> findByEmail(String email);
    User save(User user);
    boolean existsByEmail(String email);
}
