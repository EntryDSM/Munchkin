package kr.hs.entrydsm.score.entity;

import java.util.List;
import java.util.Optional;

public interface ScoreRepository {
    List<Score> findAll();
    Score save(Score score);
    Optional<Score> findByReceiptCode(long receiptCode);
    boolean existsByReceiptCode(long receiptCode);
}
