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

    @Override
    public List<User> findAllForExcel() { //수험번호, 접수 번호, 전형 유형, 추가 유형, 지역, 이름, 생년월일, 주소, 학생 전화번호, 자기소개서, 학업 계획서, 보호자 이름, 보호자 전화번호 필요함
        return null;
    }

}
