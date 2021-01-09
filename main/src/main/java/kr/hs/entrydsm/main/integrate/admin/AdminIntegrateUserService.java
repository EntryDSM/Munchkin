package kr.hs.entrydsm.main.integrate.admin;

import kr.hs.entrydsm.admin.domain.entity.Applicant;
import kr.hs.entrydsm.admin.integrate.user.ApplicantRepository;
import kr.hs.entrydsm.user.domain.entity.User;
import kr.hs.entrydsm.user.integrate.admin.UserExportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class AdminIntegrateUserService implements ApplicantRepository {

    private final UserExportRepository userExportRepository;

    @Override
    public List<Applicant> findAll() {
        return userExportRepository.findAll().stream()
                .map(user -> Applicant.builder()
                        .receiptCode(user.getReceiptCode())
                        .name(user.getName())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public Applicant findByReceiptCode(int receiptCode) {
        User user = userExportRepository.findByReceiptCode(receiptCode);
        return Applicant.builder()
                    .receiptCode(user.getReceiptCode())
                    .name(user.getName())
                    .build();
    }

}
