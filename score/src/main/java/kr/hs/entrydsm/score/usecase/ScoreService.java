package kr.hs.entrydsm.score.usecase;

import kr.hs.entrydsm.common.context.beans.Published;
import kr.hs.entrydsm.score.entity.Score;
import kr.hs.entrydsm.score.usecase.dto.QueryGraduationResponse;
import kr.hs.entrydsm.score.usecase.dto.QueryQualificationExamResponse;
import kr.hs.entrydsm.score.usecase.dto.UpdateGraduationRequest;
import kr.hs.entrydsm.score.usecase.dto.UpdateQualificationExamRequest;

@Published
public interface ScoreService {
    QueryGraduationResponse queryGraduation();
    QueryQualificationExamResponse queryQualificationExam();
    QueryGraduationResponse queryGraduationByReceiptCode(long receiptCode);
    QueryQualificationExamResponse queryQualificationExamByReceiptCode(long receiptCode);

    Score updateGraduation(UpdateGraduationRequest request);
    Score updateQualificationExam(UpdateQualificationExamRequest request);
    Score findByReceiptCode(long receiptCode);

    boolean isExistsByReceiptCode(long receiptCode);
}
