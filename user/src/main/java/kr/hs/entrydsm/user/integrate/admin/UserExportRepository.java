package kr.hs.entrydsm.user.integrate.admin;

import kr.hs.entrydsm.common.context.beans.Published;
import kr.hs.entrydsm.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Published
public interface UserExportRepository {
    User findByReceiptCode(int receiptCode);
    Page<User> findAll(Pageable page, boolean isDaejeon, boolean isNationwide,
                       boolean isPrintedArrived, boolean isPaid, boolean isCommon,
                       boolean isMeister, boolean isSocial, int receiptCode,
                       String schoolName, String telephoneNumber, String name);
    void changeExamCode(long receiptCode, String examCode);
    List<User> findAllIsSubmitTrue();
    List<User> findAllForExcel();
    long getTotalStudent();
<<<<<<< HEAD
    void changeIsPrintedArrived(int receiptCode, boolean isPrintedArrived);
    void changeIsPaid(int receiptCode, boolean isPaid);
    void changeIsSubmit(int receiptCode, boolean isSubmit);
=======
>>>>>>> 236a950... [ADD] integrate 함수 추가
}
