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
    void changeIsSubmitFalse(long receiptCode);
    List<SaveExamCodeUserResponse> findAllPassStatusTrue();
    UserInfo getUserInfo(long receiptCode);
    void changeIsPrintedArrived(long receiptCode, boolean isPrintedArrived);
    UserNameAndEmail getUserNameAndEmail(long receiptCode);
    List<ExcelUser> findAllForExcel();
}