package kr.hs.entrydsm.application.integrate.admin;

import kr.hs.entrydsm.common.context.beans.Published;
import kr.hs.entrydsm.common.model.ReportCard;

@Published
public interface ApplicationExportRepository {

    ReportCard getReportCard(long receiptCode);
}
