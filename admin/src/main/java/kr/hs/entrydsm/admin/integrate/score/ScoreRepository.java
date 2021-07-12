package kr.hs.entrydsm.admin.integrate.score;

import kr.hs.entrydsm.admin.usecase.dto.applicant.ApplicationStatus;
import kr.hs.entrydsm.admin.usecase.dto.excel.ExcelUserScore;
import kr.hs.entrydsm.admin.usecase.dto.score.FirstRoundSuccessfulCandidates;

public interface ScoreRepository {
    ApplicationStatus getScore();
    ExcelUserScore findUserScore(Long receiptCode);
    FirstRoundSuccessfulCandidates getSuccessfulCandidate();
}
