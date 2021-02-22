package kr.hs.entrydsm.admin.integrate.user;

import kr.hs.entrydsm.admin.domain.entity.Applicant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ApplicantRepository {

    Page<Applicant> findAll(Pageable page, boolean isDaejeon, boolean isNationwide,
                            boolean isPrintedArrived, boolean isPaid, boolean isCommon,
                            boolean isMeister, boolean isSocial, int receiptCode,
                            String schoolName, String telephoneNumber, String name);
    void changeExamCode(long receiptCode, String examCode);
    List<Applicant> findAllIsSubmitTrue();
    Applicant getUserInfo(long receiptCode);
    void changeStatus(int receiptCode, boolean isPrintedArrived, boolean isPaid, boolean isSubmit);

}
