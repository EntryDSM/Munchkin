package kr.hs.entrydsm.user.domain.entity;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findByReceiptCode(Integer receiptCode);
    List<User> findAll();
    User save(User user);
}
