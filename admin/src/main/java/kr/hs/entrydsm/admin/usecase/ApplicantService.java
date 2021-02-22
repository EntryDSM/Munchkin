package kr.hs.entrydsm.admin.usecase;

import kr.hs.entrydsm.admin.usecase.dto.response.ApplicantDetailResponse;
import kr.hs.entrydsm.admin.usecase.dto.response.ApplicantsResponse;
import org.springframework.data.domain.Pageable;

public interface ApplicantService {
    void updateStatus(int receiptCode, boolean isPrintedArrived, boolean isPaid, boolean isSubmit);
    ApplicantsResponse getApplicants(Pageable page, boolean isDaejeon, boolean isNationwide, boolean isPrintedArrived,
                                     boolean isPaid, boolean isCommon, boolean isMeister, boolean isSocial,
                                     int receiptCode, String schoolName, String telephoneNumber, String name);
    ApplicantDetailResponse getDetail(int receiptCode);
    void saveExamCode() throws Exception;
}
