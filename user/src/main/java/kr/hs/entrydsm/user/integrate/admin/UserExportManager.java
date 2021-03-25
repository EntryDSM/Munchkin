package kr.hs.entrydsm.user.integrate.admin;

import kr.hs.entrydsm.user.entity.user.User;
import kr.hs.entrydsm.user.entity.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserExportManager implements UserExportRepository {

    private final UserRepository userRepository;

    @Override
    public User findByReceiptCode(int receiptCode) {
        return userRepository.findByReceiptCode(receiptCode)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public Page<User> findAll(Pageable page, boolean isDaejeon, boolean isNationwide,
                              boolean isPrintedArrived, boolean isPaid, boolean isCommon,
                              boolean isMeister, boolean isSocial, int receiptCode,
                              String schoolName, String telephoneNumber, String name) {
        return null;
    }

    @Override
    public void changeExamCode(long receiptCode, String examCode) {
    }

    @Override
    public List<User> findAllIsSubmitTrue() {
        return null;
    }

}
