package kr.hs.entrydsm.application.integrate.admin;

import kr.hs.entrydsm.application.entity.GraduationApplication;
import kr.hs.entrydsm.application.usecase.dto.Applicant;
import kr.hs.entrydsm.common.context.beans.Published;
import kr.hs.entrydsm.common.model.ReportCard;

import java.util.List;

@Published
public interface ApplicationExportRepository {
    List<Applicant> getApplicants();
    ReportCard getReportCard(long receiptCode);
}
