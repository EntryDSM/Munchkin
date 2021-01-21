package kr.hs.entrydsm.score.integrate.user;

import java.util.Optional;

public interface ScorerRepository {
    Optional<Scorer> findByReceiptCode(long receiptCode);
}
