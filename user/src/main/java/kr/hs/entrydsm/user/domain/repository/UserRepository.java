package kr.hs.entrydsm.user.domain.repository;

import kr.hs.entrydsm.user.domain.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findByReceiptCode(long receiptCode);
    List<User> findAllBy();
}
