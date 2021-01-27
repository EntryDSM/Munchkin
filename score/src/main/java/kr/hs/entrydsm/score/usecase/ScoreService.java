package kr.hs.entrydsm.score.usecase;

import kr.hs.entrydsm.score.usecase.dto.QueryGraduationResponse;
import kr.hs.entrydsm.score.usecase.dto.QueryQualificationExamResponse;
import kr.hs.entrydsm.score.usecase.dto.UpdateGraduationRequest;
import kr.hs.entrydsm.score.usecase.dto.UpdateQualificationExamRequest;

public interface ScoreService {
    QueryGraduationResponse queryGraduation();
    QueryQualificationExamResponse queryQualificationExam();

    void updateGraduation(UpdateGraduationRequest request);
    void updateQualificationExam(UpdateQualificationExamRequest request);
}
