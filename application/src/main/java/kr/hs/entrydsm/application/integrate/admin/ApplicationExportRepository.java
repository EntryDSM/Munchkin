package kr.hs.entrydsm.application.integrate.admin;

import kr.hs.entrydsm.common.context.ReportCard;
import kr.hs.entrydsm.common.context.beans.Published;

@Published
public interface ApplicationExportRepository {

    ReportCard getReportCardByReceiptCode(long receiptCode);
}
