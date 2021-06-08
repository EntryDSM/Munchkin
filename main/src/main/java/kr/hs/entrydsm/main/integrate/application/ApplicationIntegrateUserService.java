package kr.hs.entrydsm.main.integrate.application;

import kr.hs.entrydsm.application.entity.Applicant;
import kr.hs.entrydsm.application.integrate.user.ApplicantDocsService;
import kr.hs.entrydsm.application.integrate.user.ApplicantRepository;
import kr.hs.entrydsm.application.integrate.user.ApplicantStatusService;
import kr.hs.entrydsm.common.context.beans.Published;
import org.springframework.stereotype.Service;

@Published
@Service
public class ApplicationIntegrateUserService
        implements ApplicantDocsService, ApplicantStatusService, ApplicantRepository {
    @Override
    public void writeSelfIntroduce(Long receiptCode, String content) {

    }

    @Override
    public void writeStudyPlan(Long receiptCode, String content) {

    }

    @Override
    public String getSelfIntroduce(Long receiptCode) {
        return null;
    }

    @Override
    public String getStudyPlan(Long receiptCode) {
        return null;
    }

    @Override
    public boolean isFinalSubmit(long receiptCode) {
        return false;
    }

    @Override
    public Applicant findByReceiptCode(long receiptCode) {
        return null;
    }
}
