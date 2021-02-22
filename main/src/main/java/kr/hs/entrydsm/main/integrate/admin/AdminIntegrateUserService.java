package kr.hs.entrydsm.main.integrate.admin;

import kr.hs.entrydsm.admin.domain.entity.Applicant;
import kr.hs.entrydsm.admin.integrate.user.ApplicantRepository;
import kr.hs.entrydsm.user.domain.entity.User;
import kr.hs.entrydsm.user.integrate.admin.UserExportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class AdminIntegrateUserService implements ApplicantRepository {

    private final UserExportRepository userExportRepository;

    @Override
    public Page<Applicant> findAll(Pageable pageable) {
        Page<User> users = userExportRepository.findAll(pageable);
        long totalElements = users.getTotalElements();
        List<Applicant> applicants = new ArrayList<>();
        for (User user : users) {
            applicants.add(
                    Applicant.builder()
                        .receiptCode(user.getReceiptCode())
                        .name(user.getName())
                        .isDaejeon(user.isDaejeon())
                        .applicationType(String.valueOf(user.getApplicationType()))
                        .isPaid(user.getStatus().isPaid())
                        .isPrintedArrived(user.getStatus().isPrintedArrived())
                        .isSubmit(user.getStatus().isSubmit())
                    .build()
            );
        }
        return new PageImpl<>(applicants, pageable, totalElements);
    }

    @Override
    public void changeExamCode(long receiptCode, String examCode) {
        User user = userExportRepository.findByReceiptCode((int)receiptCode);

        userExportRepository.changeExamCode(user.getReceiptCode(), examCode);
    }

    @Override
    public List<Applicant> findAllIsSubmitTrue() {
        return null;
    }

}
