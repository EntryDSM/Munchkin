package kr.hs.entrydsm.score.usecase;

import kr.hs.entrydsm.score.entity.Score;
import kr.hs.entrydsm.score.usecase.dto.QueryGraduationResponse;
import kr.hs.entrydsm.score.usecase.dto.QueryQualificationExamResponse;
import kr.hs.entrydsm.score.usecase.dto.UpdateGraduationRequest;
import kr.hs.entrydsm.score.usecase.dto.UpdateQualificationExamRequest;

public interface ScoreService {
    QueryGraduationResponse queryGraduation();
    QueryQualificationExamResponse queryQualificationExam();

    Score updateGraduation(UpdateGraduationRequest request);
    Score updateQualificationExam(UpdateQualificationExamRequest request);
}
