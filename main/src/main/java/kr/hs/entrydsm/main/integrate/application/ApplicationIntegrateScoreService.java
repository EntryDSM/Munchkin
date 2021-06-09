package kr.hs.entrydsm.main.integrate.application;

import kr.hs.entrydsm.application.entity.Application;
import kr.hs.entrydsm.application.entity.GraduationApplication;
import kr.hs.entrydsm.application.integrate.score.ScoreCalculator;
import kr.hs.entrydsm.application.usecase.dto.Score;
import kr.hs.entrydsm.application.usecase.dto.SubjectScore;
import kr.hs.entrydsm.common.context.beans.Published;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Published
@Service
public class ApplicationIntegrateScoreService implements ScoreCalculator {

    @Override
    public Iterable<Score> getAll() {
        return null;
    }

    @Override
    public Score getScore(Application application) {
        return null;
    };

    private Optional<SubjectScore> makeSubjectScore(Application application) { // Optional 값이 null이면 검정고시
        if (application.isGraduation()) {
            GraduationApplication graduationApplication = (GraduationApplication) application;
            SubjectScore subjectScore = SubjectScore.builder()
                    .koreanScore(graduationApplication.getKoreanScore())
                    .socialScore(graduationApplication.getSocialScore())
                    .scienceScore(graduationApplication.getScienceScore())
                    .mathScore(graduationApplication.getMathScore())
                    .historyScore(graduationApplication.getHistoryScore())
                    .englishScore(graduationApplication.getEnglishScore())
                    .techAndHomeScore(graduationApplication.getTechAndHomeScore())
                    .build();
            return Optional.of(subjectScore);
        }
        return Optional.empty();
    }
}
