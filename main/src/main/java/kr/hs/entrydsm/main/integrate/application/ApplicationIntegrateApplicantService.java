package kr.hs.entrydsm.main.integrate.application;

import kr.hs.entrydsm.application.integrate.user.ApplicationApplicantRepository;
import kr.hs.entrydsm.application.usecase.dto.application.request.ApplicationRequest;
import kr.hs.entrydsm.application.usecase.dto.application.response.ApplicationResponse;
import kr.hs.entrydsm.application.usecase.dto.application.request.InformationRequest;
import kr.hs.entrydsm.application.usecase.dto.application.response.InformationResponse;
import kr.hs.entrydsm.user.entity.user.User;
import kr.hs.entrydsm.user.integrate.application.ApplicationUserExportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Service
public class ApplicationIntegrateApplicantService implements ApplicationApplicantRepository {

    private final ApplicationUserExportRepository userExportRepository;

    @Override
    public void writeApplicationType(Long receiptCode, ApplicationRequest application) {
        userExportRepository.changeApplication(receiptCode, application.getEducationalStatus(),
                application.getApplicationType(), application.isDaejeon(), application.getApplicationRemark());

    }

    @Override
    public void writeInformation(Long receiptCode, InformationRequest information) {
        LocalDate birthday = null;
        if(information.getBirthday() != null)
            birthday = LocalDate.parse(information.getBirthday(), DateTimeFormatter.ofPattern("yyyyMMdd")
                                                                                    .withZone(ZoneId.of("Asia/Seoul")));

        userExportRepository.changeInformation(receiptCode, information.getName(), information.getSex(),
                birthday, information.getParentName(), information.getParentTel(),
                information.getTelephoneNumber(), information.getHomeTel(), information.getAddress(),
                information.getPostCode(), information.getPhotoFileName(), information.getDetailAddress());
    }

    @Override
    public ApplicationResponse getApplicationType(Long receiptCode) {
        User user = userExportRepository.findByReceiptCode(receiptCode);
        return ApplicationResponse.builder()
                .educationalStatus(stringValueOf(user.getEducationalStatus()))
                .applicationType(stringValueOf(user.getApplicationType()))
                .applicationRemark(stringValueOf(user.getApplicationRemark()))
                .isDaejeon(user.isDaejeon())
                .build();
    }

    @Override
    public InformationResponse getInformation(Long receiptCode) {
        User user = userExportRepository.findByReceiptCode(receiptCode);
        return InformationResponse.builder()
                .name(user.getName())
                .sex(stringValueOf(user.getSex()))
                .birthday(stringValueOf(user.getBirthday()))
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
