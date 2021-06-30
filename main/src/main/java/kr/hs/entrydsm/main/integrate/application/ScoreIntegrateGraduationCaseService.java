package kr.hs.entrydsm.main.integrate.application;

import kr.hs.entrydsm.application.entity.GraduationApplication;
import kr.hs.entrydsm.application.integrate.score.ApplicationExportScoreRepository;
import kr.hs.entrydsm.score.integrate.application.GraduationCase;
import kr.hs.entrydsm.score.integrate.application.GraduationCaseRepository;
import kr.hs.entrydsm.score.integrate.user.ScorerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ScoreIntegrateGraduationCaseService implements GraduationCaseRepository {

    private final ApplicationExportScoreRepository applicationExportRepository;
    private final ScorerRepository scorerRepository;

    @Override
    public Optional<GraduationCase> findByReceiptCode(long receiptCode) {
        GraduationApplication application = applicationExportRepository.getGraduationApplication(receiptCode);
        GraduationCase graduationCase = GraduationCase.builder()
                .scorer(scorerRepository.findByReceiptCode(receiptCode))
                .volunteerTime(application.getVolunteerTime())
                .dayAbsenceCount(application.getDayAbsenceCount())
                .lectureAbsenceCount(application.getLectureAbsenceCount())
                .latenessCount(application.getLatenessCount())
                .earlyLeaveCount(application.getEarlyLeaveCount())
                .koreanGrade(application.getKoreanScore())
                .socialGrade(application.getSocialScore())
                .historyGrade(application.getHistoryScore())
                .mathGrade(application.getMathScore())
                .scienceGrade(application.getScienceScore())
                .englishGrade(application.getEnglishScore())
                .techAndHomeGrade(application.getTechAndHomeScore())
                .build();

        return Optional.of(graduationCase);
    }

    @Override
    public void save(GraduationCase graduationCase) {
        applicationExportRepository.saveGraduationApplication(
                GraduationApplication.builder()
                .volunteerTime(graduationCase.getVolunteerTime())
                .dayAbsenceCount(graduationCase.getDayAbsenceCount())
                .lectureAbsenceCount(graduationCase.getLectureAbsenceCount())
                .latenessCount(graduationCase.getLatenessCount())
                .earlyLeaveCount(graduationCase.getEarlyLeaveCount())
                .koreanScore(graduationCase.getKoreanGrade())
                .socialScore(graduationCase.getSocialGrade())
                .historyScore(graduationCase.getHistoryGrade())
                .mathScore(graduationCase.getMathGrade())
                .scienceScore(graduationCase.getScienceGrade())
                .englishScore(graduationCase.getEnglishGrade())
                .techAndHomeScore(graduationCase.getTechAndHomeGrade())
                .build()
        );
    }
}
