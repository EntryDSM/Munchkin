package kr.hs.entrydsm.admin.integrate.user;

import kr.hs.entrydsm.admin.usecase.dto.Applicant;
import kr.hs.entrydsm.admin.usecase.dto.ExcelUser;
import kr.hs.entrydsm.admin.usecase.dto.SaveExamCodeUserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ApplicantRepository {
    Page<Applicant> findAll(Pageable page, Long receiptCode,
                            boolean isDaejeon, boolean isNationwide,
                            String telephoneNumber, String name,
                            boolean isCommon, boolean isMeister, boolean isSocial,
                            Boolean isPrintedArrived);
    void changeExamCode(long receiptCode, String examCode);
    List<SaveExamCodeUserResponse> findAllIsSubmitTrue();
    Applicant getUserInfo(long receiptCode);
    void changeIsPrintedArrived(long receiptCode, boolean isPrintedArrived);
    List<ExcelUser> findAllForExcel();
    List<Long> getUserReceiptCodes();
}