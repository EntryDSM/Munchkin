package kr.hs.entrydsm.main.integrate.application;

import kr.hs.entrydsm.application.integrate.user.ApplicationApplicantRepository;
import kr.hs.entrydsm.application.usecase.dto.Application;
import kr.hs.entrydsm.application.usecase.dto.Information;
import kr.hs.entrydsm.user.entity.user.User;
import kr.hs.entrydsm.user.integrate.application.ApplicationUserExportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
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
        User user = userExportRepository.findByReceiptCode(receiptCode);
        return Information.builder()
                .name(user.getName())
                .sex(user.getSex().toString())
                .birthday(user.getBirthday())
                .parentName(user.getParentName())
                .parentTel(user.getParentTel())
                .telephoneNumber(user.getTelephoneNumber())
                .homeTel(user.getHomeTel())
                .address(user.getAddress())
                .postCode(user.getPostCode())
                .photoFileName(user.getPhotoFileName())
                .build();
    }

    @Override
    public void setPhotoFileName(Long receiptCode, String photoFileName) {

    }

    @Override
    public String getPhotoFileName(Long receiptCode) {
        User user = userExportRepository.findByReceiptCode(receiptCode);
        return user.getPhotoFileName();
    }
}
