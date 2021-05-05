package kr.hs.entrydsm.admin.integrate.score;

import kr.hs.entrydsm.admin.usecase.dto.ApplicationStatus;

public interface ScoreRepository {
    ApplicationStatus getScore();
}
