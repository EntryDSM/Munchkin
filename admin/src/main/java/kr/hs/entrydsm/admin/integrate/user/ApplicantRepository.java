package kr.hs.entrydsm.admin.integrate.user;

import kr.hs.entrydsm.admin.domain.entity.Applicant;

import java.util.List;

public interface ApplicantRepository {
    List<Applicant> findAll();
    Applicant findByReceiptCode(int receiptCode);
}
