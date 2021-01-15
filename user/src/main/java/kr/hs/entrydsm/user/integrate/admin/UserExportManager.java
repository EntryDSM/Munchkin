package kr.hs.entrydsm.user.integrate.admin;

import kr.hs.entrydsm.user.domain.entity.User;
import kr.hs.entrydsm.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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
    public List<User> findAll() {
        return userRepository.findAllBy();
    }

}
