package kr.hs.entrydsm.score.integrate.application;

import kr.hs.entrydsm.common.context.beans.Published;
import kr.hs.entrydsm.score.entity.Score;

@Published
public interface ScoreExportApplicationRepository {
    Iterable<Score> findAll();
    Score findByReceiptCode(long receiptCode);
}
