package kr.hs.entrydsm.application.integrate.admin;

import kr.hs.entrydsm.common.model.ReportCard;

public interface ApplicationExportRepository {

    ReportCard getReportCard(long receiptCode);
}
