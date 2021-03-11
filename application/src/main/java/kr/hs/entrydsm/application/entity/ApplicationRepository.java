package kr.hs.entrydsm.application.entity;

import java.util.Optional;

public interface ApplicationRepository {
    Optional<Application> findByReceiptCode(Long receiptCode);
}
