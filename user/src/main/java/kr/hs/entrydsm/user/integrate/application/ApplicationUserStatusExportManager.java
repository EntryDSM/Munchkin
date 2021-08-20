package kr.hs.entrydsm.user.integrate.application;

import kr.hs.entrydsm.user.entity.status.Status;
import kr.hs.entrydsm.user.entity.status.StatusRepository;
import kr.hs.entrydsm.user.entity.user.User;
import kr.hs.entrydsm.user.infrastructure.database.UserRepositoryManager;
import kr.hs.entrydsm.user.usecase.exception.StatusNotFoundException;
import kr.hs.entrydsm.user.usecase.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ApplicationUserStatusExportManager implements ApplicationUserStatusExportRepository {

    private final UserRepositoryManager userRepository;
    private final StatusRepository statusRepository;

    @Override
    public Status findByReceiptCode(long receiptCode) {
        return userRepository.findByReceiptCode(receiptCode)
                .map(User::getStatus)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void finalSubmit(long receiptCode) {
        Status status = statusRepository.findByReceiptCode(receiptCode)
                .orElseThrow(StatusNotFoundException::new);

        status.finalSubmit();
        statusRepository.save(status);
    }
}
