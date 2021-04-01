package kr.hs.entrydsm.admin.integrate.user;

import kr.hs.entrydsm.admin.usecase.dto.Applicant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ApplicantRepository {

    Page<Applicant> findAll(Pageable page, Long receiptCode,
                            boolean isDaejeon, boolean isNationwide,
                            String telephoneNumber, String name,
                            boolean isCommon, boolean isMeister, boolean isSocial,
                            boolean isPrintedArrived, boolean isPaid);
    void changeExamCode(long receiptCode, String examCode);
    List<Applicant> findAllIsSubmitTrue();
    Applicant getUserInfo(long receiptCode);
    void changeStatus(int receiptCode, boolean isPrintedArrived, boolean isPaid, boolean isSubmit);

}
