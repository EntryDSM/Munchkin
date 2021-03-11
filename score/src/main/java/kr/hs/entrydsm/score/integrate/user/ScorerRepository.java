package kr.hs.entrydsm.score.integrate.user;

public interface ScorerRepository {
    Scorer findByReceiptCode(long receiptCode);
}
