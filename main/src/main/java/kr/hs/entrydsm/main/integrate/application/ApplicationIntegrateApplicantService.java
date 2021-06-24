package kr.hs.entrydsm.main.integrate.application;

import kr.hs.entrydsm.application.integrate.user.ApplicationApplicantRepository;
import kr.hs.entrydsm.application.usecase.dto.Application;
import kr.hs.entrydsm.application.usecase.dto.Information;
import kr.hs.entrydsm.user.entity.user.User;
import kr.hs.entrydsm.user.integrate.application.ApplicationUserExportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Service
public class ApplicationIntegrateApplicantService implements ApplicationApplicantRepository {

    private final ApplicationUserExportRepository userExportRepository;

    @Override
    public void writeApplicationType(Long receiptCode, Application application) {
        userExportRepository.changeApplication(receiptCode, application.getEducationalStatus(),
                application.getApplicationType(), application.isDaejeon(), application.getApplicationRemark());

    }

    @Override
    public void writeInformation(Long receiptCode, Information information) {
        LocalDate birthday = null;
        if(information.getBirthday() != null)
            birthday = LocalDate.parse(information.getBirthday(), DateTimeFormatter.ofPattern("yyyyMMdd"));

        userExportRepository.changeInformation(receiptCode, information.getName(), information.getSex(),
                birthday, information.getParentName(), information.getParentTel(),
                information.getTelephoneNumber(), information.getHomeTel(), information.getAddress(),
                information.getPostCode(), information.getPhotoFileName(), information.getDetailAddress());
    }

    @Override
    public Application getApplicationType(Long receiptCode) {
        User user = userExportRepository.findByReceiptCode(receiptCode);
        return Application.builder()
                .educationalStatus(stringValueOf(user.getEducationalStatus()))
                .applicationType(stringValueOf(user.getApplicationType()))
                .applicationRemark(stringValueOf(user.getApplicationRemark()))
                .isDaejeon(user.isDaejeon())
                .build();
    }

    @Override
    public Information getInformation(Long receiptCode) {
        User user = userExportRepository.findByReceiptCode(receiptCode);
        return Information.builder()
                .name(user.getName())
                .sex(stringValueOf(user.getSex()))
                .birthday(user.getBirthday().toString())
                .parentName(user.getParentName())
                .parentTel(user.getParentTel())
                .telephoneNumber(user.getTelephoneNumber())
                .homeTel(user.getHomeTel())
                .address(user.getAddress())
                .postCode(user.getPostCode())
                .photoFileName(user.getPhotoFileName())
                .detailAddress(user.getDetailAddress())
                .build();
    }

    @Override
    public void setPhotoFileName(Long receiptCode, String photoFileName) {
        userExportRepository.changePhotoFileName(receiptCode, photoFileName);
    }

    @Override
    public String getPhotoFileName(Long receiptCode) {
        User user = userExportRepository.findByReceiptCode(receiptCode);
        return user.getPhotoFileName();
    }

    private String stringValueOf(Object object) {
        return object == null ? null : String.valueOf(object);
    }
}
