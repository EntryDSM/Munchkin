package kr.hs.entrydsm.application.entity;

import java.util.Optional;

public interface QualificationExamApplicationRepository {
    Optional<QualificationExamApplication> findByReceiptCode(Long receiptCode);
    QualificationExamApplication save(QualificationExamApplication qualificationExamApplication);
    boolean existsByReceiptCode(Long receiptCode);
    void delete(QualificationExamApplication qualificationExamApplication);
}
