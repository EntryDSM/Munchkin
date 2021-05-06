package kr.hs.entrydsm.score.integrate.admin;

import kr.hs.entrydsm.score.usecase.dto.ApplicantScore;
import kr.hs.entrydsm.score.usecase.dto.ApplicationStatusResponse;

public interface ScoreExportRepository {
    ApplicationStatusResponse getApplicationStatus();
    ApplicantScore getApplicantScore(Long receiptCode);
}
