package kr.hs.entrydsm.user.integrate.score;

import kr.hs.entrydsm.user.entity.user.User;
import kr.hs.entrydsm.user.entity.user.enumeration.ApplicationType;
import kr.hs.entrydsm.user.infrastructure.database.UserRepositoryManager;
import kr.hs.entrydsm.user.usecase.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class UserIntegrateScoreManager implements UserIntegrateScoreRepository {

    private final UserRepositoryManager userRepository;

    @Override
    public User findByReceiptCode(long receiptCode) {
        return userRepository.findByReceiptCode(receiptCode)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User findByReceiptCodeAndIsSubmitTrue(long receiptCode) {
        return userRepository.findByReceiptCode(receiptCode)
                .filter(user -> user.getStatus().getIsSubmit())
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public List<User> findCandidatesByRegionAndType(boolean isDaejeon, ApplicationType applicationType) {
        return userRepository.findAllByIsDaejeonAndApplicationTypeAndStatus_IsSubmitTrue(isDaejeon, applicationType)
                .stream().filter(user -> user.getStatus().isPrintedArrived())
                .collect(Collectors.toList());
    }

}
