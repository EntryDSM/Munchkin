package kr.hs.entrydsm.main.integrate.application;

import kr.hs.entrydsm.application.entity.Applicant;
import kr.hs.entrydsm.application.integrate.user.ApplicantDocsService;
import kr.hs.entrydsm.application.integrate.user.ApplicantRepository;
import kr.hs.entrydsm.application.integrate.user.ApplicantStatusService;
import kr.hs.entrydsm.common.context.beans.Published;
import kr.hs.entrydsm.user.entity.status.Status;
import kr.hs.entrydsm.user.entity.user.User;
import kr.hs.entrydsm.user.integrate.application.ApplicationUserExportRepository;
import kr.hs.entrydsm.user.integrate.application.ApplicationUserStatusExportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Published
@Service
@RequiredArgsConstructor
public class ApplicationIntegrateUserService
        implements ApplicantDocsService, ApplicantStatusService, ApplicantRepository {

    private final ApplicationUserExportRepository applicationUserExportRepository;
    private final ApplicationUserStatusExportRepository applicationUserStatusExportRepository;

    @Override
    public void writeSelfIntroduce(Long receiptCode, String content) {
        applicationUserExportRepository.changeSelfIntroduce(receiptCode, content);
    }

    @Override
    public void writeStudyPlan(Long receiptCode, String content) {
        applicationUserExportRepository.changeStudyPlan(receiptCode, content);
    }

    @Override
    public String getSelfIntroduce(Long receiptCode) {
        User user = applicationUserExportRepository.findByReceiptCode(receiptCode);
        return user.getSelfIntroduce();
    }

    @Override
    public String getStudyPlan(Long receiptCode) {
        User user = applicationUserExportRepository.findByReceiptCode(receiptCode);
        return user.getStudyPlan();
    }

    @Override
    public boolean isFinalSubmit(long receiptCode) {
        Status status = applicationUserStatusExportRepository.findByReceiptCode(receiptCode);
        return status.isSubmit();
    }

    @Override
    public Applicant findByReceiptCode(long receiptCode) {
        User user = applicationUserExportRepository.findByReceiptCode(receiptCode);
        return Applicant.builder()
                .receiptCode(user.getReceiptCode())
                .telephoneNumber(user.getTelephoneNumber())
                .applicationType(user.getApplicationType().name())
                .applicationRemark(user.getApplicationRemark().name())
                .educationalStatus(user.getEducationalStatus().name())
                .isDaejeon(user.isDaejeon())
                .name(user.getName())
                .sex(user.getSex().name())
                .birthday(user.getBirthday())
                .parentName(user.getParentName())
                .parentTel(user.getParentTel())
                .address(user.getAddress())
                .postCode(user.getPostCode())
                .photoFileName(user.getPhotoFileName())
                .homeTel(user.getHomeTel())
                .selfIntroduce(user.getSelfIntroduce())
                .studyPlan(user.getStudyPlan())
                .build();
    }
}
