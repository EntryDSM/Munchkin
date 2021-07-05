package kr.hs.entrydsm.score.entity;

public interface ScoreRepository {
    Iterable<Score> findAll();
    Score save(Score score);
    Score findByReceiptCode(long receiptCode);
    boolean existsByReceiptCode(long receiptCode);
}
