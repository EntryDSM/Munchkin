package kr.hs.entrydsm.application.entity;

import java.util.Optional;

public interface GraduationApplicationRepository {
    Optional<GraduationApplication> findByReceiptCode(Long receiptCode);
}
