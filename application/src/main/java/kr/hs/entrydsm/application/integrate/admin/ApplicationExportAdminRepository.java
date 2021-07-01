package kr.hs.entrydsm.application.integrate.admin;

import kr.hs.entrydsm.application.usecase.dto.ReportCard;
import kr.hs.entrydsm.common.context.beans.Published;
import kr.hs.entrydsm.application.usecase.dto.MiddleSchoolInfo;

@Published
public interface ApplicationExportAdminRepository {
    ReportCard getReportCard(long receiptCode);
    MiddleSchoolInfo getMiddleSchoolInfo(long receiptCode);
}
