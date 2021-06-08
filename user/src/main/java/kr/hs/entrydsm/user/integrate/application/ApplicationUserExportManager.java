package kr.hs.entrydsm.user.integrate.application;

import kr.hs.entrydsm.user.entity.user.User;
import kr.hs.entrydsm.user.entity.user.enumeration.ApplicationRemark;
import kr.hs.entrydsm.user.entity.user.enumeration.ApplicationType;
import kr.hs.entrydsm.user.entity.user.enumeration.EducationalStatus;
import kr.hs.entrydsm.user.infrastructure.database.UserRepositoryManager;
import kr.hs.entrydsm.user.usecase.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

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
    public void changeApplication(long receiptCode, String educationalStatus, String applicationType,
                                  boolean isDaejeon,String applicationRemark) {
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

    @Override
    public void changeInformation(long receiptCode, String name, String sex, LocalDate birthday,
                                  String parentName, String parentTel, String telephoneNumber, String homeTel,
                                  String address, String postCode, String photoFileName) {
        User user = findByReceiptCode(receiptCode);
        user.updateInformation(name, sex, birthday, parentName, parentTel, telephoneNumber,
                homeTel, address, postCode, photoFileName);
        userRepository.save(user);
    }

    @Override
    public void changePhotoFileName(long receiptCode, String photoFileName) {
        User user = findByReceiptCode(receiptCode);
        user.updatePhotoFileName(photoFileName);
        userRepository.save(user);
    }

    @Override
    public void changeSelfIntroduce(long receiptCode, String content) {
        // TODO 구현 - 자기소개 컬럼 업데이트
    }

    @Override
    public void changeStudyPlan(long receiptCode, String content) {
        // TODO 구현 - 학업계획 컬럼 업데이트
    }

}
