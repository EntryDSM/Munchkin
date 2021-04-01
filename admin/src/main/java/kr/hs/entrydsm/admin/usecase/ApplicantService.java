package kr.hs.entrydsm.admin.usecase;

import kr.hs.entrydsm.admin.usecase.dto.response.ApplicantDetailResponse;
import kr.hs.entrydsm.admin.usecase.dto.response.ApplicantsResponse;
import lombok.NonNull;
import org.springframework.data.domain.Pageable;

public interface ApplicantService {
    void updateStatus(int receiptCode, boolean isPrintedArrived, boolean isPaid, boolean isSubmit);
    ApplicantsResponse getApplicants(Pageable page, Long receiptCode,
                                     boolean isDaejeon, boolean isNationwide,
                                     String telephoneNumber, String name,
                                     boolean isCommon, boolean isMeister, boolean isSocial,
                                     boolean isPrintedArrived, boolean isPaid);
    ApplicantDetailResponse getDetail(int receiptCode);
    void saveExamCode() throws Exception;
}
