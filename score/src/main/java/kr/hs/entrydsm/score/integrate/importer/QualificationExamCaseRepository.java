package kr.hs.entrydsm.score.integrate.importer;

import kr.hs.entrydsm.common.context.beans.Published;
import kr.hs.entrydsm.score.entity.QualificationExamCase;

import java.util.Optional;

@Published
public interface QualificationExamCaseRepository {
    Optional<QualificationExamCase> findByReceiptCode(long receiptCode);
}
