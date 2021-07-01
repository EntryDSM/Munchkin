package kr.hs.entrydsm.application.integrate.admin;

import kr.hs.entrydsm.application.entity.GraduationApplication;
import kr.hs.entrydsm.application.entity.QualificationExamApplication;
import kr.hs.entrydsm.application.usecase.dto.Applicant;
import kr.hs.entrydsm.application.usecase.dto.ReportCard;
import kr.hs.entrydsm.common.context.beans.Published;

import java.util.List;
import java.util.Optional;

@Published
public interface ApplicationExportAdminRepository {
    List<Applicant> getApplicants();
    ReportCard getReportCard(long receiptCode);
}
