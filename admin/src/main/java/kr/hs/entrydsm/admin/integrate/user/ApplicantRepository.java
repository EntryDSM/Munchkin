package kr.hs.entrydsm.admin.integrate.user;

import kr.hs.entrydsm.admin.domain.entity.Applicant;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ApplicantRepository {
    Page<Applicant> findAll();
    Applicant findByReceiptCode(int receiptCode);
    void changeExamCode(long receiptCode, String examCode);
    List<Applicant> findAllUser();
}
