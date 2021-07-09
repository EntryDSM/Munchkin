package kr.hs.entrydsm.score.entity;

import java.util.Optional;

public interface ScoreRepository {
    Iterable<Score> findAll();
    Score save(Score score);
    Optional<Score> findByReceiptCode(long receiptCode);
    boolean existsByReceiptCode(long receiptCode);
}
