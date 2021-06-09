package kr.hs.entrydsm.application.integrate.score;

import kr.hs.entrydsm.application.entity.Application;
import kr.hs.entrydsm.application.usecase.dto.Score;

public interface ScoreCalculator {
    Iterable<Score> getAll();
    Score getScore(Application application);
}
