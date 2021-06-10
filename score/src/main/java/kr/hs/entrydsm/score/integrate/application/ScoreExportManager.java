package kr.hs.entrydsm.score.integrate.application;

import kr.hs.entrydsm.score.entity.Score;
import kr.hs.entrydsm.score.entity.ScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ScoreExportManager implements ScoreExportRepository {
    private final ScoreRepository scoreRepository;

    @Override
    public Iterable<Score> findAll() {
        return scoreRepository.findAll();
    }

    @Override
    public Score findByReceiptCode(long receiptCode) {
        return scoreRepository.findByReceiptCode(receiptCode);
    }
}
