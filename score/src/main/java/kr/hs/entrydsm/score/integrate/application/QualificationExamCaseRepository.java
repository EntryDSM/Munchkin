package kr.hs.entrydsm.score.integrate.application;

import java.util.Optional;

public interface QualificationExamCaseRepository {
    Optional<QualificationExamCase> findByReceiptCode(long receiptCode);
    void save(QualificationExamCase qualificationExamCase);
}
