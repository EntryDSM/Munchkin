package kr.hs.entrydsm.admin.usecase;

import kr.hs.entrydsm.admin.usecase.dto.response.ApplicantDetailResponse;
import kr.hs.entrydsm.admin.usecase.dto.response.ApplicantsResponse;
import org.springframework.data.domain.Pageable;

public interface ApplicantService {
    void updateStatus(Integer recieptCode, boolean isPrintedArrived, boolean isPaid, boolean isSubmit);
    ApplicantsResponse getApplicants(Pageable page, boolean isDaejeon, boolean isNationwide, boolean isPrintedArrived,
                                     boolean isPaid, boolean isCommon, boolean isMeister, boolean isSocial,
                                     Integer recieptCode, String schoolName, String telephoneNumber, String name);
    ApplicantDetailResponse getDetail(Integer recieptCode);
    void saveExamCode() throws Exception;
}
