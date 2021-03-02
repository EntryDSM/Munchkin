package kr.hs.entrydsm.score.integrate.application;

import java.util.Optional;

public interface GraduationCaseRepository {
    Optional<GraduationCase> findByReceiptCode(long receiptCode);
    void save(GraduationCase graduationCase);
}
