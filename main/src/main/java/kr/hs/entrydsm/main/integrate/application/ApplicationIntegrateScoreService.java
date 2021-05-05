package kr.hs.entrydsm.main.integrate.application;

import kr.hs.entrydsm.application.integrate.score.ScoreCalculator;
import kr.hs.entrydsm.application.usecase.dto.Score;
import kr.hs.entrydsm.common.context.beans.Published;
import kr.hs.entrydsm.common.model.Scores;
import org.springframework.stereotype.Service;

@Published
@Service
public class ApplicationIntegrateScoreService implements ScoreCalculator {

    @Override
    public Iterable<Score> getAll() {
        return null;
    }

    @Override
    public Scores getScores(Long receiptCode) {
        return null;
    }
}
