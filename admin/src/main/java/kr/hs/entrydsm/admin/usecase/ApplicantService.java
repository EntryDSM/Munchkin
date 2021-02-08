package kr.hs.entrydsm.admin.usecase;

import kr.hs.entrydsm.admin.usecase.dto.ApplicantDetailResponse;
import kr.hs.entrydsm.admin.usecase.dto.ApplicantsResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ApplicantService {
    void updateStatus(Integer recieptCode, boolean isPrintedArrived, boolean isPaid, boolean isSubmit);
    List<ApplicantsResponse> getApplicants(Pageable page, boolean isDaejeon, boolean isNationwide, boolean isPrintedArrived,
                                           boolean isPaid, boolean isCommon, boolean isMeiseter, boolean isSocial,
                                           Integer recieptCode, String schoolName, String telephoneNumber, String name);
    ApplicantDetailResponse getDetail(Integer recieptCode);
}
