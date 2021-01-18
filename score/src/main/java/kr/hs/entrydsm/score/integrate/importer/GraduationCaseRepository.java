package kr.hs.entrydsm.score.integrate.importer;

import kr.hs.entrydsm.common.context.beans.Published;
import kr.hs.entrydsm.score.entity.GraduationCase;

import java.util.Optional;

@Published
public interface GraduationCaseRepository {
    Optional<GraduationCase> findByReceiptCode(long receiptCode);
}
