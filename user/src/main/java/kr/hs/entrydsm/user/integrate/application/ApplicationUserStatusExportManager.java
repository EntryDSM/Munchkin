package kr.hs.entrydsm.user.integrate.application;

import kr.hs.entrydsm.user.entity.status.Status;
import org.springframework.stereotype.Repository;

@Repository
public class ApplicationUserStatusExportManager implements ApplicationUserStatusExportRepository {

    @Override
    public Status findByReceiptCode(long receiptCode) {
        return null; // TODO 구현
    }
}
