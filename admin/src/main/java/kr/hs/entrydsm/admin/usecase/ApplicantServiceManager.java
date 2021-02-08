package kr.hs.entrydsm.admin.usecase;

import kr.hs.entrydsm.admin.domain.entity.Applicant;
import kr.hs.entrydsm.admin.integrate.user.ApplicantRepository;
import kr.hs.entrydsm.admin.usecase.dto.ApplicantDetailResponse;
import kr.hs.entrydsm.admin.usecase.dto.ApplicantsResponse;
import kr.hs.entrydsm.admin.usecase.exception.ApplicantNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ApplicantServiceManager implements ApplicantService {

    private final ApplicantRepository applicantRepository;

    @Override
    public void updateStatus(Integer recieptCode, boolean isPrintedArrived, boolean isPaid, boolean isSubmit) {
        Applicant applicant = applicantRepository.findByReceiptCode(recieptCode);

        if(applicant != null) {
            applicant.updateStatus(isPrintedArrived, isPaid, isSubmit);
        }
        else {
            throw new ApplicantNotFoundException();
        }
    }

    @Override
    public List<ApplicantsResponse> getApplicants(Pageable page, boolean isDaejeon, boolean isNationwide, boolean isPrintedArrived, boolean isPaid, boolean isCommon, boolean isMeiseter, boolean isSocial, Integer recieptCode, String schoolName, String telephoneNumber, String name) {
        return null;
    }

    @Override
    public ApplicantDetailResponse getDetail(Integer recieptCode) {
        return null;
    }
}
