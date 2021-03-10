package kr.hs.entrydsm.main.integrate.application;

import kr.hs.entrydsm.application.integrate.user.ApplicantExportService;
import kr.hs.entrydsm.application.usecase.dto.Application;
import kr.hs.entrydsm.application.usecase.dto.Information;
import kr.hs.entrydsm.common.context.beans.Published;
import org.springframework.stereotype.Service;

@Published
@Service
public class ApplicationIntegrateApplicantService implements ApplicantExportService {
    @Override
    public void writeApplicationType(Long receiptCode, Application application) {

    }

    @Override
    public void writeInformation(Long receiptCode, Information information) {

    }

    @Override
    public Application getApplicationType(Long receiptCode) {
        return null;
    }

    @Override
    public Information getInformation(Long receiptCode) {
        return null;
    }
}
