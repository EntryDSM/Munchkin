package kr.hs.entrydsm.application.integrate.user;

import kr.hs.entrydsm.application.usecase.dto.Application;
import kr.hs.entrydsm.application.usecase.dto.Information;
import kr.hs.entrydsm.common.context.beans.Published;

@Published
public interface ApplicantExportRepository {
    void writeApplicationType(Long receiptCode, Application application);
    void writeInformation(Long receiptCode, Information information);
    Application getApplicationType(Long receiptCode);
    Information getInformation(Long receiptCode);
}
