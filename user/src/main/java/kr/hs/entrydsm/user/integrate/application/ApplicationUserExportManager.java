package kr.hs.entrydsm.user.integrate.application;

import kr.hs.entrydsm.user.entity.user.User;
import kr.hs.entrydsm.user.entity.user.enumeration.ApplicationRemark;
import kr.hs.entrydsm.user.entity.user.enumeration.ApplicationType;
import kr.hs.entrydsm.user.entity.user.enumeration.EducationalStatus;
import kr.hs.entrydsm.user.infrastructure.database.UserRepositoryManager;
import kr.hs.entrydsm.user.usecase.exception.InvalidEnumConstantException;
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
        EducationalStatus educationalStatusValue;
        ApplicationType applicationTypeValue;
        ApplicationRemark applicationRemarkValue;
        try{
            educationalStatusValue = EducationalStatus.valueOf(educationalStatus);
        }catch(NullPointerException e) {
            educationalStatusValue = null;
        }catch (IllegalArgumentException e) {
            throw new InvalidEnumConstantException();
        }
        try{
            applicationTypeValue = ApplicationType.valueOf(applicationType);
        }catch(NullPointerException e) {
            applicationTypeValue = null;
        }catch (IllegalArgumentException e) {
            throw new InvalidEnumConstantException();
        }
        try{
            applicationRemarkValue = ApplicationRemark.valueOf(applicationRemark);
        }catch(NullPointerException e) {
            applicationRemarkValue = null;
        }catch (IllegalArgumentException e) {
            throw new InvalidEnumConstantException();
        }

        try{
            userRepository.save(
                    user.updateUserApplication(
                            educationalStatusValue,
                            applicationTypeValue,
                            isDaejeon,
                            applicationRemarkValue
                    )
            );
        }catch (IllegalArgumentException e) {
            throw new InvalidEnumConstantException();
        }

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
        User user = findByReceiptCode(receiptCode);
        user.updateSelfIntroduce(content);
        userRepository.save(user);
    }

    @Override
    public void changeStudyPlan(long receiptCode, String content) {
        User user = findByReceiptCode(receiptCode);
        user.updateStudyPlan(content);
        userRepository.save(user);
    }

}
