package kr.hs.entrydsm.score.integrate.admin;

import kr.hs.entrydsm.score.entity.GraduationCase;
import kr.hs.entrydsm.score.entity.GraduationCaseRepository;
import kr.hs.entrydsm.score.entity.Score;
import kr.hs.entrydsm.score.entity.ScoreRepository;
import kr.hs.entrydsm.score.integrate.user.Scorer;
import kr.hs.entrydsm.score.integrate.user.ScorerRepository;
import kr.hs.entrydsm.score.usecase.dto.ApplicantScore;
import kr.hs.entrydsm.score.usecase.dto.ApplicationStatusResponse;
import kr.hs.entrydsm.score.usecase.dto.FirstRoundSuccessfulCandidate;
import kr.hs.entrydsm.score.usecase.exception.ApplicationTypeUnmatchedException;
import kr.hs.entrydsm.score.usecase.exception.GradeNotFoundException;
import kr.hs.entrydsm.score.usecase.exception.GradeOrScoreNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class ScoreExportAdminManager implements ScoreExportAdminRepository {
    private final ScoreRepository scoreRepository;
    private final ScorerRepository scorerRepository;
    private final GraduationCaseRepository graduationCaseRepository;

    @Override
    public ApplicationStatusResponse getApplicationStatus() {
        return ApplicationStatusResponse.builder()
                .commonScore(getScores(Scorer.ApplicationType.COMMON))
                .meisterScore(getScores(Scorer.ApplicationType.MEISTER))
                .specialScore(getScores(Scorer.ApplicationType.SOCIAL))
                .build();
    }

    private List<BigDecimal> getScores(Scorer.ApplicationType applicationType) {
        return scoreRepository.findAll()
                .stream()
                .filter(score -> getScorerType(score).equals(applicationType))
                .map(Score::getTotalScore).collect(Collectors.toList());
    }

    private Scorer.ApplicationType getScorerType(Score score) {
        return scorerRepository.findByReceiptCode(score.getReceiptCode()).getApplicationType();
    }

    @Override
    public ApplicantScore getApplicantScore(Long receiptCode) {
        Scorer scorer = scorerRepository.findByReceiptCode(receiptCode);
        Score score = scoreRepository.findByReceiptCode(receiptCode).orElseThrow(GradeOrScoreNotFoundException::new);

        if (scorer.isQualificationExam()) {
            return ApplicantScore.builder()
                    .koreanGrade("")
                    .englishGrade("")
                    .historyGrade("")
                    .scienceGrade("")
                    .socialGrade("")
                    .mathGrade("")
                    .techAndHomeGrade("")
                    .earlyLeaveCount(0)
                    .lectureAbsenceCount(0)
                    .latenessCount(0)
                    .dayAbsenceCount(0)
                    .volunteerTime(0)
                    .totalFirstGradeScores(score.getThirdBeforeBeforeScore())
                    .totalSecondGradeScores(score.getThirdBeforeScore())
                    .totalThirdGradeScores(score.getThirdGradeScore())
                    .conversionScore(score.getTotalGradeScore())
                    .volunteerScore(score.getVolunteerScore())
                    .attendanceScore(score.getAttendanceScore())
                    .totalScoreFirstRound(score.getTotalScore())
                    .build();
        } else {
            GraduationCase applicationCase = graduationCaseRepository.findByReceiptCode(receiptCode).orElseThrow();
            return ApplicantScore.builder()
                    .koreanGrade(applicationCase.getKoreanGrade())
                    .englishGrade(applicationCase.getEnglishGrade())
                    .historyGrade(applicationCase.getHistoryGrade())
                    .scienceGrade(applicationCase.getScienceGrade())
                    .socialGrade(applicationCase.getSocialGrade())
                    .mathGrade(applicationCase.getMathGrade())
                    .techAndHomeGrade(applicationCase.getTechAndHomeGrade())
                    .earlyLeaveCount(applicationCase.getEarlyLeaveCount())
                    .lectureAbsenceCount(applicationCase.getLectureAbsenceCount())
                    .latenessCount(applicationCase.getLatenessCount())
                    .dayAbsenceCount(applicationCase.getDayAbsenceCount())
                    .volunteerTime(applicationCase.getVolunteerTime())
                    .totalFirstGradeScores(score.getThirdBeforeBeforeScore())
                    .totalSecondGradeScores(score.getThirdBeforeScore())
                    .totalThirdGradeScores(score.getThirdGradeScore())
                    .conversionScore(score.getTotalGradeScore())
                    .volunteerScore(score.getVolunteerScore())
                    .attendanceScore(score.getAttendanceScore())
                    .totalScoreFirstRound(score.getTotalScore())
                    .build();
        }
    }

    @Override
    public FirstRoundSuccessfulCandidate getSuccessfulCandidateReceiptCodes() {
        Application[] applications = {
                new Application(ApplicationType.DAEJEON_COMMON),
                new Application(ApplicationType.DAEJEON_MEISTER),
                new Application(ApplicationType.DAEJEON_SOCIAL),
                new Application(ApplicationType.NATIONWIDE_COMMON),
                new Application(ApplicationType.NATIONWIDE_MEISTER),
                new Application(ApplicationType.NATIONWIDE_SOCIAL),
        };

        List<Score> spareApplicationQueue = new ArrayList<>();

        for (Application application : applications) {
            getScoresByApplication(application, spareApplicationQueue);
        }

        spareApplicationQueue.sort(Comparator.comparing(o -> {
            Score score = (Score) o;
            BigDecimal totalScore = score.getTotalScore();
            if (!scorerRepository.findByReceiptCode(score.getReceiptCode()).isCommon()) {
                totalScore = totalScore.multiply(BigDecimal.valueOf(1.75)).setScale(3, RoundingMode.HALF_UP);
            }
            return totalScore;
        }).reversed());

        for (Application application : applications) {
            int lackApplicationCount = application.applicationType.capacity - application.passed.size();
            if (lackApplicationCount > 0) {
                for (int i = 0; i < lackApplicationCount; i++) {
                    Score score = spareApplicationQueue.remove(0);
                    applications[getIndexByScorer(scorerRepository.findByReceiptCode(score.getReceiptCode()))]
                            .passed
                            .add(score);
                }
            }
        }

        return FirstRoundSuccessfulCandidate.builder()
                .DaejeonCommonApplicants(getReceiptsByScores(applications[0].passed))
                .DaejeonMeisterApplicants(getReceiptsByScores(applications[1].passed))
                .DaejeonSocialApplicants(getReceiptsByScores(applications[2].passed))
                .NationwideCommonApplicants(getReceiptsByScores(applications[3].passed))
                .NationwideMeisterApplicants(getReceiptsByScores(applications[4].passed))
                .NationwideSocialApplicants(getReceiptsByScores(applications[5].passed))
                .build();
    }

    @RequiredArgsConstructor
    private static class Application {
        private List<Score> passed = new ArrayList<>();
        private final ApplicationType applicationType;
    }

    @AllArgsConstructor
    private enum ApplicationType {
        DAEJEON_COMMON(true, Scorer.ApplicationType.COMMON, 20),
        DAEJEON_MEISTER(true, Scorer.ApplicationType.MEISTER, 9),
        DAEJEON_SOCIAL(true, Scorer.ApplicationType.SOCIAL, 1),
        NATIONWIDE_COMMON(false, Scorer.ApplicationType.COMMON, 20),
        NATIONWIDE_MEISTER(false, Scorer.ApplicationType.MEISTER, 9),
        NATIONWIDE_SOCIAL(false, Scorer.ApplicationType.SOCIAL, 1);

        private final boolean isDaejeon;
        private final Scorer.ApplicationType applicationType;
        private final int capacity;
    }

    private int getIndexByScorer(Scorer scorer) {
        if (scorer.isDaejeon()) {
            switch (scorer.getApplicationType()) {
                case COMMON:
                    return 0;
                case MEISTER:
                    return 1;
                case SOCIAL:
                    return 2;
            }
        } else {
            switch (scorer.getApplicationType()) {
                case COMMON:
                    return 3;
                case MEISTER:
                    return 4;
                case SOCIAL:
                    return 5;
            }
        }
        throw new ApplicationTypeUnmatchedException();
    }

    private void getScoresByApplication(Application application, List<Score> spareQueue) {
        ApplicationType type = application.applicationType;
        List<Score> sortedScores = scorerRepository.findByRegionAndType(type.isDaejeon, type.applicationType)
                .stream()
                .map(scorer -> scoreRepository.findByReceiptCode(scorer.getReceiptCode()).orElseThrow(GradeNotFoundException::new))
                .sorted(Comparator.comparing(Score::getTotalScore).reversed())
                .collect(Collectors.toList());
        int capacity = type.capacity;

        if (sortedScores.size() > capacity) {
            spareQueue.addAll(sortedScores.subList(capacity, sortedScores.size() - 1));
        }

        application.passed = sortedScores.subList(0, capacity);
    }

    private List<Long> getReceiptsByScores(List<Score> scores) {
        return scores.stream().map(Score::getReceiptCode).collect(Collectors.toList());
    }
}
