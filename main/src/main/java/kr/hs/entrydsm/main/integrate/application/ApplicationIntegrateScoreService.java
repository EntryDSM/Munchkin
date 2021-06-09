package kr.hs.entrydsm.main.integrate.application;

import kr.hs.entrydsm.application.entity.Application;
import kr.hs.entrydsm.application.entity.GraduationApplication;
import kr.hs.entrydsm.application.entity.QualificationExamApplication;
import kr.hs.entrydsm.application.integrate.score.ScoreCalculator;
import kr.hs.entrydsm.application.usecase.dto.QualificationExamGrade;
import kr.hs.entrydsm.application.usecase.dto.Score;
import kr.hs.entrydsm.application.usecase.dto.SubjectScore;
import kr.hs.entrydsm.application.usecase.dto.TotalGrade;
import kr.hs.entrydsm.common.context.beans.Published;
import org.springframework.stereotype.Service;

@Published
@Service
public class ApplicationIntegrateScoreService implements ScoreCalculator {

    @Override
    public Iterable<Score> getAll() {
        return null;
    }

    @Override
    public Score getGraduationScore(GraduationApplication application) {
        return null;
    };

    @Override
    public Score getQualificationExamScore(QualificationExamApplication application) {
        return null;
    }

    private QualificationExamGrade makeQualificationExamGrade(QualificationExamApplication application) {
        return new QualificationExamGrade(application.getAverageScore());
    }

    private TotalGrade makeTotalGrade(GraduationApplication application) {
        SubjectScore subjectScore = SubjectScore.from(application);
        return TotalGrade.fromApplicationAndSubjectScore(application, subjectScore);
    }
}
