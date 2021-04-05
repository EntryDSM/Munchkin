package kr.hs.entrydsm.main.integrate.application;

import kr.hs.entrydsm.application.integrate.user.ApplicantExportService;
import kr.hs.entrydsm.application.usecase.dto.Application;
import kr.hs.entrydsm.application.usecase.dto.Information;
import kr.hs.entrydsm.common.context.beans.Published;
import kr.hs.entrydsm.user.integrate.application.ApplicationUserExportManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Published
@Service
public class ApplicationIntegrateApplicantService implements ApplicantExportService {

    private final ApplicationUserExportManager userExportManager;

    @Override
    public void writeApplicationType(Long receiptCode, Application application) {

    }

    @Override
    public void writeInformation(Long receiptCode, Information information) {
        userExportManager.changeInformation(receiptCode, information.getName(), information.getSex(),
                information.getBirthday(), information.getParentName(), information.getParentTel(),
                information.getTelephoneNumber(), information.getHomeTel(), information.getAddress(),
                information.getPostCode(), information.getPhotoFileName());
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
