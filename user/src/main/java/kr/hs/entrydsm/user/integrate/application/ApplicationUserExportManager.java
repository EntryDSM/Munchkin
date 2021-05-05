package kr.hs.entrydsm.user.integrate.application;

import kr.hs.entrydsm.user.entity.user.User;
import kr.hs.entrydsm.user.entity.user.enumeration.ApplicationRemark;
import kr.hs.entrydsm.user.entity.user.enumeration.ApplicationType;
import kr.hs.entrydsm.user.entity.user.enumeration.EducationalStatus;
import kr.hs.entrydsm.user.infrastructure.database.UserRepositoryManager;
import kr.hs.entrydsm.user.usecase.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ApplicationUserExportManager implements ApplicationUserExportRepository {

    private final UserRepositoryManager userRepository;

    @Override
    public User findByReceiptCode(long receiptCode) {
        return userRepository.findByReceiptCode(receiptCode)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void changeApplication(long receiptCode, String educationalStatus, String applicationType, boolean isDaejeon, String applicationRemark) {
        User user = findByReceiptCode(receiptCode);
        userRepository.save(
                user.updateUserApplication(
                        EducationalStatus.valueOf(educationalStatus),
                        ApplicationType.valueOf(applicationType),
                        isDaejeon,
                        ApplicationRemark.valueOf(applicationRemark)
                )
        );
    }

}
