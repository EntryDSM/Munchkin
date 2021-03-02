package kr.hs.entrydsm.application.integrate.score;

import kr.hs.entrydsm.common.model.Scores;

public interface ScoreCalculator { // TODO implement

    Scores getScores(Long receiptCode);
}
