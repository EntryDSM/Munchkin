package kr.hs.entrydsm.application.integrate.score;

import kr.hs.entrydsm.application.entity.Application;
import kr.hs.entrydsm.application.entity.GraduationApplication;
import kr.hs.entrydsm.application.entity.QualificationExamApplication;
import kr.hs.entrydsm.application.usecase.dto.Score;

public interface ScoreCalculator {
    Iterable<Score> getAll();
    Score getGraduationScore(GraduationApplication application);
    Score getQualificationExamScore(QualificationExamApplication application);
}
