package kr.hs.entrydsm.user.integrate.application;

import kr.hs.entrydsm.common.context.beans.Published;
import kr.hs.entrydsm.user.entity.User;

import java.time.LocalDate;

@Published
public interface ApplicationUserExportRepository {
    User findByReceiptCode(long receiptCode);
    void changeApplication(long receiptCode, String educationalStatus, String applicationType, boolean isDaejeon, String applicationRemark);
    void changeInformation(long receiptCode, String name, String sex, LocalDate birthday, String parentName, String parentTel, String telephoneNumber, String homeTel, String address, String postCode, String photoFileName);

}
