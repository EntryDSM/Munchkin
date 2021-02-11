package kr.hs.entrydsm.admin.integrate.user;

import kr.hs.entrydsm.admin.domain.entity.Applicant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ApplicantRepository {
    Page<Applicant> findAll(Pageable page);
    Applicant findByReceiptCode(int receiptCode);
}
