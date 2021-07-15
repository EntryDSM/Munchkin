package kr.hs.entrydsm.score.integrate.admin;

import kr.hs.entrydsm.common.context.beans.Published;
import kr.hs.entrydsm.score.usecase.dto.ApplicantScore;
import kr.hs.entrydsm.score.usecase.dto.ApplicationStatusResponse;
import kr.hs.entrydsm.score.usecase.dto.FirstRoundSuccessfulCandidate;

@Published
public interface ScoreExportAdminRepository {
    ApplicationStatusResponse getApplicationStatus();
    ApplicantScore getApplicantScore(Long receiptCode);
    void getSuccessfulCandidateReceiptCodes();
}
