package kr.hs.entrydsm.application.integrate.score;

import kr.hs.entrydsm.application.entity.Application;
import kr.hs.entrydsm.application.usecase.dto.CalculatedScore;

public interface ScoreCalculator {
    Iterable<CalculatedScore> getAll();
    CalculatedScore getScore(Application application);
}
