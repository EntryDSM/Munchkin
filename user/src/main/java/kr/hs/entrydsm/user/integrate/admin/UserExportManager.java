package kr.hs.entrydsm.user.integrate.admin;

import kr.hs.entrydsm.user.entity.status.Status;
import kr.hs.entrydsm.user.entity.user.User;
import kr.hs.entrydsm.user.infrastructure.database.StatusRepositoryManager;
import kr.hs.entrydsm.user.infrastructure.database.UserRepositoryManager;
import kr.hs.entrydsm.user.usecase.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
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
                              String telephoneNumber, String name,
                              boolean isCommon, boolean isMeister, boolean isSocial,
                              Boolean isPrintedArrived) {

        String receiptCodeQuery = "%%";
        if (receiptCode != null) receiptCodeQuery = receiptCode.toString();

        String isDaejeonQuery = "null";
        if (isDaejeon) isDaejeonQuery = "1";
        if (isNationwide) isDaejeonQuery = "0";
        if (isDaejeon && isNationwide) isDaejeonQuery = "%%";

        String telephoneNumberQuery = "%" + telephoneNumber + "%";
        String nameQuery = "%" + name + "%";

        String isPrintedArrivedQuery = "%%";
        if (isPrintedArrived != null) isPrintedArrivedQuery = isPrintedArrivedQuery.toString();

        return userRepository.findAllByUserInfo(receiptCodeQuery, isDaejeonQuery, telephoneNumberQuery,
                nameQuery, isCommon, isMeister, isSocial, isPrintedArrivedQuery, page);
    }

    @Override
    public void changeExamCode(long receiptCode, String examCode) {
        userRepository.findByReceiptCode(receiptCode)
                .map(user -> user.setExamCode(examCode))
                .ifPresent(userRepository::save);
    }

    @Override
    public void changeIsSubmitFalse(long receiptCode) {
        //지원자의 isSubmit 상태를 false로 바꾸기
    }

    @Override
    public void changeIsPrintedArrived(long receiptCode, boolean isPrintedArrived) {
        userRepository.findByReceiptCode(receiptCode)
                .map(user -> user.setPrintedArrived(isPrintedArrived))
                .ifPresent(userRepository::save);
    }

    @Override
    public List<User> findAllIsSubmitTrue() {
        return statusRepository.findAllByIsSubmitTrue().stream()
                .map(Status::getUser)
                .collect(Collectors.toList());
    }

    @Override
    public List<Long> findAllReceiptCode() {
        return userRepository.findAllBy()
                .stream().map(User::getReceiptCode)
                .collect(Collectors.toList());
    }

}
