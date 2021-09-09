package kr.hs.entrydsm.score.entity;

import java.util.Optional;

public interface QualificationExamCaseRepository {
    Optional<QualificationExamCase> findByReceiptCode(long receiptCode);
    QualificationExamCase save(QualificationExamCase qualificationExamCase);
    void delete(QualificationExamCase qualificationExamCase);
}
