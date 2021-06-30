package kr.hs.entrydsm.user.integrate.admin;

import kr.hs.entrydsm.common.context.beans.Published;
import kr.hs.entrydsm.user.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Published
public interface UserExportRepository {
    User findByReceiptCode(int receiptCode);
    Page<User> findAll(Pageable page, Long receiptCode,
                       boolean isDaejeon, boolean isNationwide,
                       String telephoneNumber, String name,
                       boolean isCommon, boolean isMeister, boolean isSocial,
                       Boolean isPrintedArrived);
    void changeExamCode(long receiptCode, String examCode);
    void changeIsPrintedArrived(long receiptCode, boolean isPrintedArrived);
    List<User> findAllIsSubmitTrue();
    List<User> findAllForExcel();
    List<Long> findAllReceiptCode(); // isSubmitTrue인 유저의 접수 번호가 필요합니다.
}
