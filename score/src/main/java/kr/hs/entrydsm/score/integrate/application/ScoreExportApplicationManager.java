package kr.hs.entrydsm.score.integrate.application;

import kr.hs.entrydsm.score.entity.Score;
import kr.hs.entrydsm.score.entity.ScoreRepository;
import kr.hs.entrydsm.score.usecase.exception.GradeOrScoreNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ScoreExportApplicationManager implements ScoreExportApplicationRepository {
    private final ScoreRepository scoreRepository;

    @Override
    public Iterable<Score> findAll() {
        return scoreRepository.findAll();
    }

    @Override
    public Score findByReceiptCode(long receiptCode) {
        return scoreRepository.findByReceiptCode(receiptCode).orElseThrow(GradeOrScoreNotFoundException::new);
    }
}
