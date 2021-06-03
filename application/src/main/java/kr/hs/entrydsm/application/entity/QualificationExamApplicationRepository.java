package kr.hs.entrydsm.application.entity;

import java.util.Optional;

public interface QualificationExamApplicationRepository {
    Optional<QualificationExamApplication> findByReceiptCode(Long receiptCode);
}
