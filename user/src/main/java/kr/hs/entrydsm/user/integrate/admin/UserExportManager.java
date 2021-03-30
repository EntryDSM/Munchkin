package kr.hs.entrydsm.user.integrate.admin;

import kr.hs.entrydsm.user.entity.status.Status;
import kr.hs.entrydsm.user.entity.user.User;
import kr.hs.entrydsm.user.infrastructure.database.StatusRepositoryManager;
import kr.hs.entrydsm.user.infrastructure.database.UserRepositoryManager;
import kr.hs.entrydsm.user.usecase.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public Page<User> findAll(Pageable page, long receiptCode,
                              boolean isDaejeon, boolean isNationwide,
                              String telephoneNumber, String name,
                              boolean isCommon, boolean isMeister, boolean isSocial,
                              boolean isPrintedArrived, boolean isPaid) {
        return userRepository.findAllBy(PageRequest.of(0, 10));
    }

    @Override
    public void changeExamCode(long receiptCode, String examCode) {
        userRepository.findByReceiptCode(receiptCode)
                .map(user -> user.setExamCode(examCode))
                .ifPresent(user -> userRepository.save(user));
    }

    @Override
    public List<User> findAllIsSubmitTrue() {
        return statusRepository.findAllByIsSubmitTrue().stream()
                .map(Status::getUser)
                .collect(Collectors.toList());
    }

}
