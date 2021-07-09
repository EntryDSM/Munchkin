package kr.hs.entrydsm.score.integrate.admin;

import kr.hs.entrydsm.score.entity.*;
import kr.hs.entrydsm.score.integrate.user.Scorer;
import kr.hs.entrydsm.score.integrate.user.ScorerRepository;
import kr.hs.entrydsm.score.usecase.dto.ApplicantScore;
import kr.hs.entrydsm.score.usecase.dto.ApplicationStatusResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

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

    @Override
    public ApplicantScore getApplicantScore(Long receiptCode) {
        Scorer scorer = scorerRepository.findByReceiptCode(receiptCode);
        Score score = scoreRepository.findByReceiptCode(receiptCode);
        
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

    private List<BigDecimal> getScores(Scorer.ApplicationType applicationType) {
        return scoreStream().filter(score -> getScorerType(score).equals(applicationType))
                .map(Score::getTotalScore).collect(Collectors.toList());
    }

    private Stream<Score> scoreStream() {
        return StreamSupport.stream(scoreRepository.findAll().spliterator(), false);
    }

    private Scorer.ApplicationType getScorerType(Score score) {
        return scorerRepository.findByReceiptCode(score.getReceiptCode()).getApplicationType();
    }

}
