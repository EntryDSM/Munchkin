package kr.hs.entrydsm.main.integrate.application;

import kr.hs.entrydsm.application.integrate.user.ApplicationApplicantRepository;
import kr.hs.entrydsm.application.usecase.dto.Application;
import kr.hs.entrydsm.application.usecase.dto.Information;
import kr.hs.entrydsm.common.context.beans.Published;
import kr.hs.entrydsm.user.entity.User;
import kr.hs.entrydsm.user.integrate.application.ApplicationUserExportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Component
public class ApplicationIntegrateApplicantService implements ApplicationApplicantRepository {

    private ApplicationUserExportRepository userExportRepository;

    @Override
    public void writeApplicationType(Long receiptCode, Application application) {
        userExportRepository.changeApplication(receiptCode, application.getEducationalStatus(),
                application.getApplicationType(), application.isDaejeon(), application.getApplicationRemark());

    }

    @Override
    public void writeInformation(Long receiptCode, Information information) {
        userExportRepository.changeInformation(receiptCode, information.getName(), information.getSex(),
                information.getBirthday(), information.getParentName(), information.getParentTel(),
                information.getTelephoneNumber(), information.getHomeTel(), information.getAddress(),
                information.getPostCode(), information.getPhotoFileName());
    }

    @Override
    public Application getApplicationType(Long receiptCode) {
        User user = userExportRepository.findByReceiptCode(receiptCode);
        return Application.builder()
                .educationalStatus(user.getEducationalStatus().toString())
                .applicationType(user.getApplicationType().toString())
                .applicationRemark(user.getApplicationRemark().toString())
                .isDaejeon(user.isDaejeon())
                .build();
    }

    @Override
    public Information getInformation(Long receiptCode) {
        return null;
    }
}
