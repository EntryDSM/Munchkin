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
                       boolean isPrintedArrived, boolean isPaid);
    void changeExamCode(long receiptCode, String examCode);
    List<User> findAllIsSubmitTrue();
}
