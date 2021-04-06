package kr.hs.entrydsm.score.integrate.application;

import kr.hs.entrydsm.score.entity.Score;

public interface ApplicationScoreExportRepository {
    Score findByReceiptCode(long receiptCode);
}
