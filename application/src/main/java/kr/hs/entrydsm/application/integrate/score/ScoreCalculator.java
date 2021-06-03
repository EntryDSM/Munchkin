package kr.hs.entrydsm.application.integrate.score;

import kr.hs.entrydsm.application.usecase.dto.Score;
import kr.hs.entrydsm.common.model.Scores;

import java.util.List;

public interface ScoreCalculator {
    Iterable<Score> getAll();
    Score getScore(Long receiptCode);
    Scores getScores(Long receiptCode);
}
