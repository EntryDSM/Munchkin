package kr.hs.entrydsm.user.domain.repository;

import kr.hs.entrydsm.user.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findByReceiptCode(long receiptCode);
    Page<User> findAllBy(Pageable pageable);
}
