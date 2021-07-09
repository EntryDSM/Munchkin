package kr.hs.entrydsm.main.integrate.application;

import kr.hs.entrydsm.application.entity.Application;
import kr.hs.entrydsm.application.entity.GraduationApplication;
import kr.hs.entrydsm.application.entity.QualificationExamApplication;
import kr.hs.entrydsm.application.integrate.score.ScoreCalculator;
import kr.hs.entrydsm.application.usecase.dto.*;
import kr.hs.entrydsm.score.entity.Score;
import kr.hs.entrydsm.score.integrate.application.ScoreExportApplicationRepository;
import kr.hs.entrydsm.score.usecase.ScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Service
public class ApplicationIntegrateScoreService implements ScoreCalculator {

    private final ScoreExportApplicationRepository scoreExportRepository;
    private final ScoreService scoreService;

    @Override
    public Iterable<CalculatedScore> getAll() {
        return StreamSupport.stream(scoreExportRepository.findAll().spliterator(), false)
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CalculatedScore calculateScore(Application application) {
        return getCalculatedScore(application);
    }

    @Override
    public boolean isExists(long receiptCode) {
        return scoreService.isExistsByReceiptCode(receiptCode);
    }

    private CalculatedScore getCalculatedScore(Application application) {
        CalculatedScore result;
        if (application.isGraduation())
            result = getGraduationScore((GraduationApplication) application);
        else
            result = getQualificationExamScore((QualificationExamApplication) application);
        return result;
    }

    private CalculatedScore getGraduationScore(GraduationApplication application) {
        Score score = scoreService.findByReceiptCode(application.getReceiptCode());

        return CalculatedScore.builder()
                .receiptCode(application.getReceiptCode())
                .attendanceScore(score.getAttendanceScore())
                .volunteerScore(score.getVolunteerScore())
                .conversionScore(score.getTotalGradeScore())
                .totalFirstGradeScore(score.getThirdBeforeBeforeScore())
                .totalSecondGradeScore(score.getThirdBeforeScore())
                .totalThirdGradeScore(score.getThirdGradeScore())
                .totalScoreFirstRound(score.getTotalScore())
                .build();
    }

    private CalculatedScore getQualificationExamScore(QualificationExamApplication application) {
        Score score = scoreService.findByReceiptCode(application.getReceiptCode());

        return CalculatedScore.builder()
                .receiptCode(application.getReceiptCode())
                .attendanceScore(score.getAttendanceScore())
                .volunteerScore(score.getVolunteerScore())
                .conversionScore(score.getTotalGradeScore())
                .totalFirstGradeScore(null)
                .totalSecondGradeScore(null)
                .totalThirdGradeScore(null)
                .totalScoreFirstRound(score.getTotalScore())
                .build();
    }

    private CalculatedScore entityToDTO(Score score) {
        return CalculatedScore.builder()
                .receiptCode(score.getReceiptCode())
                .attendanceScore(score.getAttendanceScore())
                .volunteerScore(score.getVolunteerScore())
                .totalFirstGradeScore(score.getThirdBeforeBeforeScore())
                .totalSecondGradeScore(score.getThirdBeforeScore())
                .totalThirdGradeScore(score.getThirdGradeScore())
                .conversionScore(score.getTotalGradeScore())
                .totalScoreFirstRound(score.getTotalScore())
                .build();
    }
}
