package kr.hs.entrydsm.admin.integrate.score;

import kr.hs.entrydsm.admin.usecase.dto.ApplicationStatus;
import kr.hs.entrydsm.admin.usecase.dto.ExcelUserScore;

public interface ScoreRepository {
    ApplicationStatus getScore();
    ExcelUserScore findUserScore(Long receiptCode);
}
