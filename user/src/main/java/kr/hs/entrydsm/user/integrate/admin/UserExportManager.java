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
    public User findByReceiptCode(long receiptCode) {
        return userRepository.findByReceiptCode(receiptCode)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public Page<User> findAll(Pageable page, Long receiptCode,
                              boolean isDaejeon, boolean isNationwide,
                              String telephoneNumber, String name,
                              boolean isCommon, boolean isMeister, boolean isSocial,
                              boolean inOfHeadcount, boolean outOfHeadcount,
                              Boolean isPrintedArrived) {

        String receiptCodeQuery = "%%";
        if (receiptCode != null) receiptCodeQuery = "%" + receiptCode.toString() + "%";

        Boolean isDaejeonQuery;
        if (isDaejeon && isNationwide) isDaejeonQuery = null;
        else isDaejeonQuery = isDaejeon;

        String telephoneNumberQuery = "%" + telephoneNumber + "%";
        String nameQuery = "%" + name + "%";

        Boolean isPrintedArrivedQuery = null;
        if (isPrintedArrived != null) isPrintedArrivedQuery = isPrintedArrived;

        return userRepository.findAllByUserInfo(receiptCodeQuery, isDaejeonQuery, telephoneNumberQuery,
                nameQuery, inOfHeadcount, outOfHeadcount, isCommon, isMeister, isSocial, isPrintedArrivedQuery, page);
    }

    @Override
    public void changeExamCode(long receiptCode, String examCode) {
        userRepository.findByReceiptCode(receiptCode)
                .map(user -> user.setExamCode(examCode))
                .ifPresent(userRepository::save);
    }

    @Override
    public void changeIsSubmitFalse(long receiptCode) {
        User user = findByReceiptCode(receiptCode);
        user.setSubmitFalse();
        userRepository.save(user);
    }

    @Override
    public void changeIsFirstRoundPass(List<Long> receiptCodes) {
        for (Long receiptCode : receiptCodes) {
            userRepository.findByReceiptCode(receiptCode)
                    .map(User::setFirstRoundPass)
                    .ifPresent(userRepository::save);
        }
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
    public List<User> findAllPassStatusTrue() {
        return statusRepository.findAllByIsFirstRoundPassTrue()
                .stream().map(Status::getUser)
                .collect(Collectors.toList());
    }

}
