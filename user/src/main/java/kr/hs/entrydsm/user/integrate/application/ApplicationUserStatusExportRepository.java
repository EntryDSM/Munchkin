package kr.hs.entrydsm.user.integrate.application;

import kr.hs.entrydsm.common.context.beans.Published;
import kr.hs.entrydsm.user.entity.status.Status;

@Published
public interface ApplicationUserStatusExportRepository {
    Status findByReceiptCode(long receiptCode);
    void finalSubmit(long receiptCode);
}
