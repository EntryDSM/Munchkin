package kr.hs.entrydsm.application.entity;

import kr.hs.entrydsm.application.entity.Applicant;

public interface ApplicationRepository {

    Optional<Application> findByReceiptCode(Long receiptCode);
}
