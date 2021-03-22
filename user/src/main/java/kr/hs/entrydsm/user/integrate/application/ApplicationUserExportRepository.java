package kr.hs.entrydsm.user.integrate.application;

import kr.hs.entrydsm.common.context.beans.Published;
import kr.hs.entrydsm.user.entity.User;

@Published
public interface ApplicationUserExportRepository {
    User findByReceiptCode(long receiptCode);
    void changeApplication(long receiptCode, String educationalStatus, String applicationType, boolean isDaejeon, String applicationRemark);

}
