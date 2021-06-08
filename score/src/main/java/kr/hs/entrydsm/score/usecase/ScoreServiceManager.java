package kr.hs.entrydsm.score.usecase;

import kr.hs.entrydsm.score.entity.*;
import kr.hs.entrydsm.score.integrate.application.*;
import kr.hs.entrydsm.score.integrate.user.Scorer;
import kr.hs.entrydsm.score.integrate.user.ScorerRepository;
import kr.hs.entrydsm.score.usecase.dto.QueryGraduationResponse;
import kr.hs.entrydsm.score.usecase.dto.QueryQualificationExamResponse;
import kr.hs.entrydsm.score.usecase.dto.UpdateGraduationRequest;
import kr.hs.entrydsm.score.usecase.dto.UpdateQualificationExamRequest;
import kr.hs.entrydsm.score.usecase.exception.ApplicationTypeUnmatchedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ScoreServiceManager implements ScoreService {

    private final ScoreRepository scoreRepository;
    private final ScorerRepository scorerRepository;
    private final GraduationCaseRepository graduationCaseRepository;
    private final QualificationExamCaseRepository qualificationExamCaseRepository;
    private  final int TEMP_AUTHENTICATION = 0;

    @Override
    public QueryGraduationResponse queryGraduation() {
        GraduationCase graduationCase = graduationCaseRepository.findByReceiptCode(scorer().getReceiptCode())
                                                                .orElseThrow();
        return new QueryGraduationResponse(graduationCase);
    }

    @Override
    public QueryQualificationExamResponse queryQualificationExam() {
        QualificationExamCase qualificationExamCase = qualificationExamCaseRepository.findByReceiptCode(scorer().getReceiptCode())
                                                                                     .orElseThrow();
        return new QueryQualificationExamResponse(qualificationExamCase);
    }

    @Override
    public void updateGraduation(UpdateGraduationRequest request) {
        if (scorer().isQualificationExam()) throw new ApplicationTypeUnmatchedException();

        GraduationCase graduationCase = GraduationCase.builder()
                                                      .scorer(scorer())
                                                      .volunteerTime(request.getVolunteerTime())
                                                      .dayAbsenceCount(request.getDayAbsenceCount())
                                                      .lectureAbsenceCount(request.getLectureAbsenceCount())
                                                      .latenessCount(request.getLatenessCount())
                                                      .earlyLeaveCount(request.getEarlyLeaveCount())
                                                      .englishGrade(request.getEnglishGrade())
                                                      .historyGrade(request.getHistoryGrade())
                                                      .koreanGrade(request.getKoreanGrade())
                                                      .mathGrade(request.getMathGrade())
                                                      .scienceGrade(request.getScienceGrade())
                                                      .socialGrade(request.getSocialGrade())
                                                      .techAndHomeGrade(request.getTechAndHomeGrade())
                                                      .build();
        graduationCaseRepository.save(graduationCase);
        updateScore(graduationCase);
    }

    @Override
    public void updateQualificationExam(UpdateQualificationExamRequest request) {
        if (!scorer().isQualificationExam()) throw new ApplicationTypeUnmatchedException();

        QualificationExamCase qualificationExamCase = QualificationExamCase.builder()
                                                                           .scorer(scorer())
                                                                           .averageScore(request.getGedAverageScore())
                                                                           .build();
        qualificationExamCaseRepository.save(qualificationExamCase);
        updateScore(qualificationExamCase);
    }

    private void updateScore(ApplicationCase applicationCase) {
        Score score = new Score(scorer().getReceiptCode(), applicationCase);
        scoreRepository.save(score);
    }

    private Scorer scorer() {
        return scorerRepository.findByReceiptCode(TEMP_AUTHENTICATION);
    }
}
