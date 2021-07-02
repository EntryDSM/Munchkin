package kr.hs.entrydsm.admin.integrate.user;

import kr.hs.entrydsm.admin.usecase.dto.applicant.*;
import kr.hs.entrydsm.admin.usecase.dto.excel.ExcelUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface UserRepository {
    Page<ApplicantsInformationResponse> findAll(Pageable page, Long receiptCode,
                                                boolean isDaejeon, boolean isNationwide,
                                                String telephoneNumber, String name,
                                                boolean isCommon, boolean isMeister, boolean isSocial,
                                                Boolean isPrintedArrived);
    void changeExamCode(long receiptCode, String examCode);
    List<SaveExamCodeUserResponse> findAllIsSubmitTrue();
    UserInfo getUserInfo(long receiptCode);
    void changeIsPrintedArrived(long receiptCode, boolean isPrintedArrived);
    UserNameAndTelephoneNumber getUserNameAndTel(long receiptCode);
    List<ExcelUser> findAllForExcel();
    List<Long> getUserReceiptCodes();
}