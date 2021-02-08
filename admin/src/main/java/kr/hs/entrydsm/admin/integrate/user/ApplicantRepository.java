package kr.hs.entrydsm.admin.integrate.user;

import kr.hs.entrydsm.admin.domain.entity.Applicant;
import org.springframework.data.domain.Page;

public interface ApplicantRepository {
    Page<Applicant> findAll();
    Applicant findByReceiptCode(int receiptCode);
}
