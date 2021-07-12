package kr.hs.entrydsm.score.integrate.user;

import java.util.List;

public interface ScorerRepository {
    Scorer findByReceiptCode(long receiptCode);
    List<Scorer> findByRegionAndType(boolean isDaejeon, Scorer.ApplicationType applicationType);
}
