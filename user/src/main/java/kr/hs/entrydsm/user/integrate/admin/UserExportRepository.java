package kr.hs.entrydsm.user.integrate.admin;

import kr.hs.entrydsm.common.context.beans.Published;
import kr.hs.entrydsm.user.domain.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

@Published
public interface UserExportRepository {
    User findByReceiptCode(int receiptCode);
    Page<User> findAll();
    void changeExamCode(long receiptCode, String examCode);
    List<User> findAllIsSubmitTrue();
}
