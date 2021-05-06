package kr.hs.entrydsm.main.integrate.application;

import kr.hs.entrydsm.application.integrate.score.ScoreCalculator;
import kr.hs.entrydsm.application.usecase.dto.Score;
import kr.hs.entrydsm.common.context.beans.Published;
import kr.hs.entrydsm.common.model.Scores;
import kr.hs.entrydsm.score.integrate.application.ApplicationScoreExportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Published
@Service
@RequiredArgsConstructor
public class ApplicationIntegrateScoreService implements ScoreCalculator {

    private final ApplicationScoreExportRepository applicationScoreExportRepository;

    @Override
    public Iterable<Score> getAll() {
        return null;
    }

    @Override
    public Score getScore(Long receiptCode) {
        return null;
    }

    @Override
    public Scores getScores(Long receiptCode) {
        kr.hs.entrydsm.score.entity.Score score = applicationScoreExportRepository.findByReceiptCode(receiptCode);
        return Scores.builder()
                .gradeScore(score.getGradeScore())
                .volunteerScore(score.getVolunteerScore())
                .attendanceScore(score.getAttendanceScore())
                .totalScore(score.getTotalScore())
                .build();
    }
}
