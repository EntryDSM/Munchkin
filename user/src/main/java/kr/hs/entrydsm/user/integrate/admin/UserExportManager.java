package kr.hs.entrydsm.user.integrate.admin;

import kr.hs.entrydsm.user.entity.status.Status;
import kr.hs.entrydsm.user.entity.user.User;
import kr.hs.entrydsm.user.infrastructure.database.StatusRepositoryManager;
import kr.hs.entrydsm.user.infrastructure.database.UserRepositoryManager;
import kr.hs.entrydsm.user.usecase.exception.UserNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class UserExportManager implements UserExportRepository {

    private final UserRepositoryManager userRepository;
    private final StatusRepositoryManager statusRepository;

    @Override
    public User findByReceiptCode(int receiptCode) {
        return userRepository.findByReceiptCode(receiptCode)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public Page<User> findAll(Pageable page, Long receiptCode,
                              boolean isDaejeon, boolean isNationwide,
                              @NonNull String telephoneNumber, @NonNull String name,
                              boolean isCommon, boolean isMeister, boolean isSocial,
                              boolean isPrintedArrived, boolean isPaid) {

        String receiptCodeQuery = "%%";
        if (receiptCode != null) receiptCodeQuery = receiptCode.toString();

        String isDaejeonQuery = "null";
        if (isDaejeon) isDaejeonQuery = "1";
        if (isNationwide) isDaejeonQuery = "0";
        if (isDaejeon && isNationwide) isDaejeonQuery = "%%";

        String telephoneNumberQuery = "%" + telephoneNumber + "%";
        String nameQuery = "%" + name + "%";

        return userRepository.findAllByUserInfo(receiptCodeQuery, isDaejeonQuery, telephoneNumberQuery,
                nameQuery, isCommon, isMeister, isSocial, isPrintedArrived, isPaid, page);
    }

    @Override
    public void changeExamCode(long receiptCode, String examCode) {
        userRepository.findByReceiptCode(receiptCode)
                .map(user -> user.setExamCode(examCode))
                .ifPresent(userRepository::save);
    }

    @Override
    public List<User> findAllIsSubmitTrue() {
        return statusRepository.findAllByIsSubmitTrue().stream()
                .map(Status::getUser)
                .collect(Collectors.toList());
    }

    @Override
    public List<User> findAllForExcel() { //수험번호, 접수 번호, 전형 유형, 추가 유형, 지역, 이름, 생년월일, 주소, 학생 전화번호, 자기소개서, 학업 계획서, 보호자 이름, 보호자 전화번호 필요함
        return userRepository.findAllBy();
    }

}
