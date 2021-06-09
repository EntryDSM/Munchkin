package kr.hs.entrydsm.main.integrate.application;

import kr.hs.entrydsm.application.entity.Application;
import kr.hs.entrydsm.application.entity.GraduationApplication;
import kr.hs.entrydsm.application.integrate.score.ScoreCalculator;
import kr.hs.entrydsm.application.usecase.dto.Score;
import kr.hs.entrydsm.application.usecase.dto.SubjectScore;
import kr.hs.entrydsm.application.usecase.dto.TotalGrade;
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

    private TotalGrade makeTotalGrade(Application application) {
        TotalGrade result = null;
        if (application.isGraduation()) {
            GraduationApplication graduationApplication = (GraduationApplication) application;
            SubjectScore subjectScore = makeSubjectScore(graduationApplication);
            result = TotalGrade.builder()
                    .subjectScore(subjectScore)
                    .dayAbsenceCount(graduationApplication.getDayAbsenceCount())
                    .earlyLeaveCount(graduationApplication.getEarlyLeaveCount())
                    .latenessCount(graduationApplication.getLatenessCount())
                    .lectureAbsenceCount(graduationApplication.getLectureAbsenceCount())
                    .volunteerTime(graduationApplication.getVolunteerTime())
                    .build();
        }
        return result;
    }

    private SubjectScore makeSubjectScore(GraduationApplication application) {
        return SubjectScore.builder()
                .koreanScore(application.getKoreanScore())
                .mathScore(application.getMathScore())
                .englishScore(application.getEnglishScore())
                .scienceScore(application.getScienceScore())
                .socialScore(application.getSocialScore())
                .historyScore(application.getHistoryScore())
                .techAndHomeScore(application.getTechAndHomeScore())
                .build();
    }
}
