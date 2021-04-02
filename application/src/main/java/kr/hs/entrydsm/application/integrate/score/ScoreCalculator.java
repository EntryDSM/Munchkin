package kr.hs.entrydsm.application.integrate.score;

import kr.hs.entrydsm.application.usecase.dto.Score;
import kr.hs.entrydsm.common.model.Scores;

public interface ScoreCalculator {
    Iterable<Score> getAll();
    Scores getScores(Long receiptCode);
}
