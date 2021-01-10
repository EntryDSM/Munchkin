package kr.hs.entrydsm.user.domain.entity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByReceiptCode(long receiptCode);
    List<User> findAllBy();
}
