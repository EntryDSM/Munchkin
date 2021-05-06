package kr.hs.entrydsm.application.integrate.user;

import kr.hs.entrydsm.application.entity.Applicant;

public interface ApplicantRepository {

    Applicant findByReceiptCode(long receiptCode);
}
