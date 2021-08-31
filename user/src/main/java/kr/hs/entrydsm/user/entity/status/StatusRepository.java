package kr.hs.entrydsm.user.entity.status;

import java.util.Optional;

public interface StatusRepository {
    Status save(Status status);
    Optional<Status> findByReceiptCode(Long receiptCode);
}
