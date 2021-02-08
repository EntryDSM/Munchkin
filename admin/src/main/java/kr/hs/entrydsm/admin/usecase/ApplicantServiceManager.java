package kr.hs.entrydsm.admin.usecase;

import kr.hs.entrydsm.admin.domain.entity.Applicant;
import kr.hs.entrydsm.admin.domain.repository.AdminRepository;
import kr.hs.entrydsm.admin.integrate.user.ApplicantRepository;
import kr.hs.entrydsm.admin.security.auth.AuthenticationFacade;
import kr.hs.entrydsm.admin.usecase.dto.ApplicantDetailResponse;
import kr.hs.entrydsm.admin.usecase.dto.ApplicantsInformationResponse;
import kr.hs.entrydsm.admin.usecase.dto.ApplicantsResponse;
import kr.hs.entrydsm.admin.usecase.exception.ApplicantNotFoundException;
import kr.hs.entrydsm.admin.usecase.exception.UserNotAccessibleException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ApplicantServiceManager implements ApplicantService {

    private final ApplicantRepository applicantRepository;
    private final AdminRepository adminRepository;

    private final AuthenticationFacade authenticationFacade;

    @Override
    public void updateStatus(Integer recieptCode, boolean isPrintedArrived, boolean isPaid, boolean isSubmit) {
        if(!authenticationFacade.isLogin()) {
            throw new UserNotAccessibleException();
        }

        Applicant applicant = applicantRepository.findByReceiptCode(recieptCode);

        if(applicant != null) {
            applicant.updateStatus(isPrintedArrived, isPaid, isSubmit);
        }
        else {
            throw new ApplicantNotFoundException();
        }
        //공지메세지에서 보내주기
    }

    @Override
    public List<ApplicantsResponse> getApplicants(Integer size, Integer page, boolean isDaejeon, boolean isNationwide,
                                                  boolean isPrintedArrived, boolean isPaid, boolean isCommon,
                                                  boolean isMeiseter, boolean isSocial, Integer recieptCode,
                                                  String schoolName, String telephoneNumber, String name) {
        if(!authenticationFacade.isLogin()) {
            throw new UserNotAccessibleException();
        }

        Page<Applicant> applicants = applicantRepository.findAll();
        List<ApplicantsInformationResponse> applicantsInformationResponses= new ArrayList<>();

        
        for (Applicant applicant : applicants) {
            applicantsInformationResponses.add(
                    ApplicantsInformationResponse.builder()
                            .receiptCode(applicant.getReceiptCode())
                            .name(applicant.getName())
                            .isDaejeon(applicant.isDaejeon())
                            .isPrintedArrived(applicant.isPrintedArrived())
                            .isPaid(applicant.isPaid())
                            .isSubmit(applicant.isSubmit())
                            .build()
            );
        }

        return (List<ApplicantsResponse>) ApplicantsResponse.builder()
                .totalElements((int)applicants.getTotalElements())
                .totalPages(applicants.getTotalPages())
                .applicantsInformationResponses(applicantsInformationResponses)
                .build();
    }

    @Override
    public ApplicantDetailResponse getDetail(Integer recieptCode) {
        if(!authenticationFacade.isLogin()) {
            throw new UserNotAccessibleException();
        }
        return null;
    }
}
