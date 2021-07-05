package kr.hs.entrydsm.main.integrate.application;

import kr.hs.entrydsm.application.entity.Application;
import kr.hs.entrydsm.application.entity.GraduationApplication;
import kr.hs.entrydsm.application.entity.QualificationExamApplication;
import kr.hs.entrydsm.application.integrate.score.ScoreCalculator;
import kr.hs.entrydsm.application.usecase.dto.*;
import kr.hs.entrydsm.score.entity.Score;
import kr.hs.entrydsm.score.integrate.application.ScoreExportApplicationRepository;
import kr.hs.entrydsm.score.usecase.ScoreService;
import kr.hs.entrydsm.score.usecase.dto.UpdateQualificationExamRequest;
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
        return false;
    }

    private CalculatedScore getCalculatedScore(Application application) {
        CalculatedScore result;
        if (application.isGraduation()) {
            result = getGraduationScore((GraduationApplication) application);
        } else {
            result = getQualificationExamScore((QualificationExamApplication) application);
        }
        return result;
    }

    private CalculatedScore getGraduationScore(GraduationApplication application) {
        return null;
    }

    private CalculatedScore getQualificationExamScore(QualificationExamApplication application) {
        return null;
    }

    private CalculatedScore entityToDTO(Score score) {
        return CalculatedScore.builder()
                .receiptCode(score.getReceiptCode())
                .attendanceScore(score.getAttendanceScore())
                .volunteerScore(score.getVolunteerScore())
                .totalFirstGradeScore(score.getFirstGradeScore())
                .totalSecondGradeScore(score.getSecondGradeScore())
                .totalThirdGradeScore(score.getThirdGradeScore())
                .conversionScore(score.getTotalGradeScore())
                .totalScoreFirstRound(score.getTotalScore())
                .build();
    }
}
