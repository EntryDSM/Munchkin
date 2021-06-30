package kr.hs.entrydsm.main.integrate.application;

import kr.hs.entrydsm.application.entity.Application;
import kr.hs.entrydsm.application.entity.GraduationApplication;
import kr.hs.entrydsm.application.entity.QualificationExamApplication;
import kr.hs.entrydsm.application.integrate.score.ScoreCalculator;
import kr.hs.entrydsm.application.usecase.dto.*;
import kr.hs.entrydsm.application.usecase.dto.score.request.EtcScoreRequest;
import kr.hs.entrydsm.application.usecase.dto.score.request.SubjectScoreRequest;
import kr.hs.entrydsm.application.usecase.exception.NullGradeExistException;
import kr.hs.entrydsm.score.entity.Score;
import kr.hs.entrydsm.score.integrate.application.ScoreExportApplicationRepository;
import kr.hs.entrydsm.score.usecase.ScoreService;
import kr.hs.entrydsm.score.usecase.dto.UpdateGraduationRequest;
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
    public CalculatedScore getScore(Application application) {
        CalculatedScore result;
        if (application.isGraduation()) {
            GraduationApplication graduationApplication = (GraduationApplication) application;
            if (graduationApplication.isAnyGradeNull()) {
                throw new NullGradeExistException();
            }
            result = getGraduationScore(graduationApplication);
        } else {
            QualificationExamApplication qualificationExamApplication = (QualificationExamApplication) application;
            if (qualificationExamApplication.getAverageScore() == null) {
                throw new NullGradeExistException();
            }
            result = getQualificationExamScore((QualificationExamApplication) application);
        }
        return result;
    }

    private CalculatedScore getGraduationScore(GraduationApplication application) {
        TotalGrade totalGrade = makeTotalGrade(application);
        SubjectScoreRequest subjectScore = totalGrade.getSubjectScore();
        EtcScoreRequest etcScore = totalGrade.getEtcScore();

        Score score = scoreService.updateGraduation(UpdateGraduationRequest.builder()
                .volunteerTime(etcScore.getVolunteerTime())
                .latenessCount(etcScore.getLatenessCount())
                .dayAbsenceCount(etcScore.getDayAbsenceCount())
                .earlyLeaveCount(etcScore.getEarlyLeaveCount())
                .lectureAbsenceCount(etcScore.getLectureAbsenceCount())
                .englishGrade(subjectScore.getEnglishScore())
                .historyGrade(subjectScore.getHistoryScore())
                .koreanGrade(subjectScore.getKoreanScore())
                .mathGrade(subjectScore.getMathScore())
                .scienceGrade(subjectScore.getScienceScore())
                .socialGrade(subjectScore.getSocialScore())
                .techAndHomeGrade(subjectScore.getTechAndHomeScore())
                .build());

        return entityToDTO(score);
    }

    private CalculatedScore getQualificationExamScore(QualificationExamApplication application) {
        QualificationExamGrade qualificationExamGrade = makeQualificationExamGrade(application);

        Score score = scoreService.updateQualificationExam(UpdateQualificationExamRequest.builder()
                .gedAverageScore(qualificationExamGrade.getAverageScore())
                .build());

        return entityToDTO(score);
    }

    private QualificationExamGrade makeQualificationExamGrade(QualificationExamApplication application) {
        return new QualificationExamGrade(application.getAverageScore());
    }

    private TotalGrade makeTotalGrade(GraduationApplication application) {
        SubjectScoreRequest subjectScore = SubjectScoreRequest.from(application);
        EtcScoreRequest etcScore = EtcScoreRequest.from(application);
        return TotalGrade.from(subjectScore, etcScore);
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
