package kr.hs.entrydsm.application.integrate.score;

import kr.hs.entrydsm.application.usecase.dto.Score;
import kr.hs.entrydsm.common.model.Scores;

import java.util.List;

public interface ScoreCalculator {
    List<Score> getAll();
    Scores getScores(Long receiptCode);
}
