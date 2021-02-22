package kr.hs.entrydsm.admin.integrate.user;

import kr.hs.entrydsm.admin.domain.entity.Applicant;

public interface ScoreRepository {
    Applicant getUserInfo(long receiptCode);
}
