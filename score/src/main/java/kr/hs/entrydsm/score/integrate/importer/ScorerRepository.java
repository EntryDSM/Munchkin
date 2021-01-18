package kr.hs.entrydsm.score.integrate.importer;

import kr.hs.entrydsm.common.context.beans.Published;
import kr.hs.entrydsm.score.entity.Scorer;

import java.util.Optional;

@Published
public interface ScorerRepository {
    Optional<Scorer> findByReceiptCode(long receiptCode);
}
