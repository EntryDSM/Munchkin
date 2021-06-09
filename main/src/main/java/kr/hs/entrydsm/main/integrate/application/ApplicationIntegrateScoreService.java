package kr.hs.entrydsm.main.integrate.application;

import kr.hs.entrydsm.application.entity.GraduationApplication;
import kr.hs.entrydsm.application.entity.QualificationExamApplication;
import kr.hs.entrydsm.application.integrate.score.ScoreCalculator;
import kr.hs.entrydsm.application.usecase.dto.QualificationExamGrade;
import kr.hs.entrydsm.application.usecase.dto.Score;
import kr.hs.entrydsm.application.usecase.dto.SubjectScore;
import kr.hs.entrydsm.application.usecase.dto.TotalGrade;
import kr.hs.entrydsm.common.context.beans.Published;
import kr.hs.entrydsm.score.integrate.application.ScoreExportRepository;
import kr.hs.entrydsm.score.usecase.ScoreService;
import kr.hs.entrydsm.score.usecase.dto.UpdateGraduationRequest;
import kr.hs.entrydsm.score.usecase.dto.UpdateQualificationExamRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Published
@Service
public class ApplicationIntegrateScoreService implements ScoreCalculator {

    private final ScoreExportRepository scoreExportRepository;
    private final ScoreService scoreService;

    @Override
    public Iterable<Score> getAll() {
        return StreamSupport.stream(scoreExportRepository.findAll().spliterator(), false)
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Score getGraduationScore(GraduationApplication application) {
        TotalGrade totalGrade = makeTotalGrade(application);
        SubjectScore subjectScore = totalGrade.getSubjectScore();

        kr.hs.entrydsm.score.entity.Score score = scoreService.updateGraduation(UpdateGraduationRequest.builder()
                .volunteerTime(totalGrade.getVolunteerTime())
                .latenessCount(totalGrade.getLatenessCount())
                .dayAbsenceCount(totalGrade.getDayAbsenceCount())
                .earlyLeaveCount(totalGrade.getEarlyLeaveCount())
                .lectureAbsenceCount(totalGrade.getLectureAbsenceCount())
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

    @Override
    public Score getQualificationExamScore(QualificationExamApplication application) {
        QualificationExamGrade qualificationExamGrade = makeQualificationExamGrade(application);

        kr.hs.entrydsm.score.entity.Score score = scoreService.updateQualificationExam(UpdateQualificationExamRequest.builder()
                .gedAverageScore(qualificationExamGrade.getAverageScore())
                .build());

        return entityToDTO(score);
    }

    private QualificationExamGrade makeQualificationExamGrade(QualificationExamApplication application) {
        return new QualificationExamGrade(application.getAverageScore());
    }

    private TotalGrade makeTotalGrade(GraduationApplication application) {
        return TotalGrade.builder()
                .subjectScore(makeSubjectScore(application))
                .dayAbsenceCount(application.getDayAbsenceCount())
                .earlyLeaveCount(application.getEarlyLeaveCount())
                .latenessCount(application.getLatenessCount())
                .lectureAbsenceCount(application.getLectureAbsenceCount())
                .volunteerTime(application.getVolunteerTime())
                .build();
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

    private Score entityToDTO(kr.hs.entrydsm.score.entity.Score score) {
        return Score.builder()
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
