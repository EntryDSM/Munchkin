package kr.hs.entrydsm.admin.usecase.applicant;

import kr.hs.entrydsm.admin.usecase.dto.applicant.ApplicantsResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ApplicantService {
    void changePrintArrivedOrNot(long receiptCode, boolean isPrintedArrived);
    void changeIsSubmitFalse(long receiptCode);
    ApplicantsResponse getApplicants(Pageable page, Long receiptCode,
                                     boolean isDaejeon, boolean isNationwide,
                                     String telephoneNumber, String name,
                                     boolean isCommon, boolean isMeister, boolean isSocial,
                                     Boolean isPrintedArrived);
    ResponseEntity getDetailApplicantInfo(int receiptCode);
    void saveAllApplicantsExamCode() throws Exception;
}
