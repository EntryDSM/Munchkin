package kr.hs.entrydsm.score.integrate.user;

import java.util.List;

public interface ScorerRepository {
    Scorer findByReceiptCode(long receiptCode);
    List<Scorer> findCandidatesByRegionAndType(boolean isDaejeon, Scorer.ApplicationType applicationType);
}
