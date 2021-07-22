package kr.hs.entrydsm.user.integrate.score;

import kr.hs.entrydsm.user.entity.user.User;
import kr.hs.entrydsm.user.entity.user.enumeration.ApplicationType;
import kr.hs.entrydsm.user.infrastructure.database.UserRepositoryManager;
import kr.hs.entrydsm.user.usecase.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    public List<User> findCandidatesByRegionAndType(boolean isDaejeon, ApplicationType applicationType) {
        return userRepository.findAllByIsDaejeonAndApplicationType(isDaejeon, applicationType);
    }

}
