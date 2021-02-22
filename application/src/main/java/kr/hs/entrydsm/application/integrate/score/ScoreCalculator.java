package kr.hs.entrydsm.application.integrate.score;

import kr.hs.entrydsm.common.model.Scores;

public interface ScoreCalculator {

    Scores getScores(Long receiptCode);
}
