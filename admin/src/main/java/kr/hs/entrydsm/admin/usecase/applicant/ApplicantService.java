package kr.hs.entrydsm.admin.usecase.applicant;

import kr.hs.entrydsm.admin.usecase.dto.response.ApplicantsResponse;
import org.springframework.data.domain.Pageable;

public interface ApplicantService {
    void changeIsPrintedArrived(long receiptCode, boolean isPrintedArrived);
    ApplicantsResponse getApplicants(Pageable page, Long receiptCode,
                                     boolean isDaejeon, boolean isNationwide,
                                     String telephoneNumber, String name,
                                     boolean isCommon, boolean isMeister, boolean isSocial,
                                     Boolean isPrintedArrived);
    Object getDetail(int receiptCode);
    void saveExamCode() throws Exception;
}
