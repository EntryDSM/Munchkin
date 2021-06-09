package kr.hs.entrydsm.score.integrate.application;

import kr.hs.entrydsm.score.entity.Score;

public interface ScoreExportRepository {
    Iterable<Score> findAll();
    Score findByReceiptCode(long receiptCode);
}
