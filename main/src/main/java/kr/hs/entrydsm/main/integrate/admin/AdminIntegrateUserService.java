package kr.hs.entrydsm.main.integrate.admin;

import kr.hs.entrydsm.admin.domain.entity.Applicant;
import kr.hs.entrydsm.admin.integrate.user.ApplicantRepository;
import kr.hs.entrydsm.user.domain.entity.User;
import kr.hs.entrydsm.user.integrate.admin.UserExportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AdminIntegrateUserService implements ApplicantRepository {

    private final UserExportRepository userExportRepository;

    @Override
    public Page<Applicant> findAll() {
        return (Page<Applicant>) userExportRepository.findAll().stream()
                .map(user -> Applicant.builder()
                        .receiptCode(user.getReceiptCode())
                        .name(user.getName())
                        .isDaejeon(user.isDaejeon())
                        .applicationType(String.valueOf(user.getApplicationType()))
                        .isPaid(user.getStatus().isPaid())
                        .isPrintedArrived(user.getStatus().isPrintedArrived())
                        .isSubmit(user.getStatus().isSubmit())
                        .build());
    }


    @Override
    public Applicant findByReceiptCode(int receiptCode) {
        User user = userExportRepository.findByReceiptCode(receiptCode);
        //검정고시 + 졸업 지원자들 정보
        return Applicant.builder()
                    .receiptCode(user.getReceiptCode())
                    .name(user.getName())
                    .photoFileName(user.getPhotoFileName())
                    .applicationType(String.valueOf(user.getApplicationType()))
                    .address(user.getAddress())
                    .birthDate(user.getBirthday())
                    .isPaid(user.getStatus().isPaid())
                    .isPrintedArrived(user.getStatus().isPrintedArrived())
                    .isSubmit(user.getStatus().isSubmit())
                    .telephoneNumber(user.getTelephoneNumber())
                    .parentTel(user.getParentTel())
                    .homeTel(user.getHomeTel())
                    .selfIntroduce(user.getSelfIntroduce())
                    .studyPlan(user.getStudyPlan())
                    .build();
    }

}
