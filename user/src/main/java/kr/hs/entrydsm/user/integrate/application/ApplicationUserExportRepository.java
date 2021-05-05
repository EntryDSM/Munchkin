package kr.hs.entrydsm.user.integrate.application;

import kr.hs.entrydsm.common.context.beans.Published;
import kr.hs.entrydsm.user.entity.user.User;

@Published
public interface ApplicationUserExportRepository {
    User findByReceiptCode(long receiptCode); // receiptCode를 이용해서 User를 찾는 함수
    void changeApplication(long receiptCode, String educationalStatus, String applicationType, boolean isDaejeon, String applicationRemark); //

}
