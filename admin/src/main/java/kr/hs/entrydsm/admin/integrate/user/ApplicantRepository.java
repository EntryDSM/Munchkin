package kr.hs.entrydsm.admin.integrate.user;

import kr.hs.entrydsm.admin.usecase.dto.Applicant;
import kr.hs.entrydsm.admin.usecase.dto.ExcelUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ApplicantRepository {
    Page<Applicant> findAll(Pageable page, Long receiptCode,
                            boolean isDaejeon, boolean isNationwide,
                            String telephoneNumber, String name,
                            boolean isCommon, boolean isMeister, boolean isSocial,
                            boolean isPrintedArrived, boolean isPaid);
    void changeExamCode(long receiptCode, String examCode);
    List<Applicant> findAllIsSubmitTrue();
    Applicant getUserInfo(long receiptCode);
    void changeIsPrintedArrived(int receiptCode, boolean isPrintedArrived);
    List<ExcelUser> findAllForExcel();
}