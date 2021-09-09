package kr.hs.entrydsm.score.entity;

import java.util.Optional;

public interface GraduationCaseRepository {
    Optional<GraduationCase> findByReceiptCode(long receiptCode);
    GraduationCase save(GraduationCase graduationCase);
    void delete(GraduationCase graduationCase);
}
