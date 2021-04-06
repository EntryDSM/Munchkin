package kr.hs.entrydsm.score.integrate.application;

import kr.hs.entrydsm.score.entity.Score;
import org.springframework.stereotype.Service;

@Service
public class ApplicationScoreExportManager implements ApplicationScoreExportRepository {
    @Override
    public Score findByReceiptCode(long receiptCode) {
        return null;
    }
}
