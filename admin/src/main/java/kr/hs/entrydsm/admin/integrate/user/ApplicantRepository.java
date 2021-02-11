package kr.hs.entrydsm.admin.integrate.user;

import kr.hs.entrydsm.admin.domain.entity.Applicant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ApplicantRepository {
    Page<Applicant> findAll(Pageable page);
    Applicant findByReceiptCode(int receiptCode);
    void changeExamCode(long receiptCode, String examCode);
    List<Applicant> findAllIsSubmitTrue();
}
