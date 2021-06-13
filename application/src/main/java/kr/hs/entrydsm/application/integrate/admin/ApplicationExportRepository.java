package kr.hs.entrydsm.application.integrate.admin;

import kr.hs.entrydsm.application.usecase.dto.Applicant;
import kr.hs.entrydsm.application.usecase.dto.ReportCard;
import kr.hs.entrydsm.common.context.beans.Published;

import java.util.List;

@Published
public interface ApplicationExportRepository {
    List<Applicant> getApplicants();
    ReportCard getReportCard(long receiptCode);
}
