package kr.hs.entrydsm.main.integrate.application;

import kr.hs.entrydsm.application.integrate.score.ScoreCalculator;
import kr.hs.entrydsm.common.context.beans.Published;
import kr.hs.entrydsm.common.model.Scores;

@Published
public class ApplicationIntegrateScoreService implements ScoreCalculator {

    @Override
    public Scores getScores(Long receiptCode) {
        return null;
    }
}
