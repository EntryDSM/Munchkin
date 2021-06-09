package kr.hs.entrydsm.admin.presenter.web;

import kr.hs.entrydsm.admin.usecase.ApplicantService;
import kr.hs.entrydsm.admin.usecase.dto.response.ApplicantsResponse;
import kr.hs.entrydsm.common.context.auth.token.AdminJWTRequired;
import kr.hs.entrydsm.common.context.beans.Published;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Published
@RequestMapping("/admin")
@RestController
public class ApplicantController {

    private final ApplicantService applicantService;

    //지원자
    @AdminJWTRequired
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/applicant/printed-arrived")
    public void updateIsprintedArrived(@RequestParam(name = "receipt-code") int receiptCode,
                                       @RequestParam(name = "is-printed-arrived") boolean isPrintedArrived) {
        applicantService.changeIsPrintedArrived(receiptCode, isPrintedArrived);
    }

    @AdminJWTRequired
    @GetMapping("/applicant")
    public Object getDetail(@RequestParam(name = "receipt-code") int receiptCode) {
        return applicantService.getDetail(receiptCode);
    }

    @AdminJWTRequired
    @GetMapping("/applicants")
    public ApplicantsResponse getApplicants(Pageable page,
                                            @RequestParam(required = false, name = "receipt-code") Long receiptCode,
                                            @RequestParam(name = "is-daejeon") boolean isDaejeon,
                                            @RequestParam(name = "is-nationwide") boolean isNationwide,
                                            @RequestParam(required = false, name = "telephone-number") String telephoneNumber,
                                            @RequestParam(required = false) String name,
                                            @RequestParam(name = "is-common") boolean isCommon,
                                            @RequestParam(name = "is-meiseter") boolean isMeister,
                                            @RequestParam(name = "is-social") boolean isSocial,
                                            @RequestParam(required = false, name = "is-printed-arrived") boolean isPrintedArrived) {
        return applicantService.getApplicants(page, receiptCode, isDaejeon, isNationwide, telephoneNumber, name, isCommon, isMeister, isSocial, isPrintedArrived);
    }

    @AdminJWTRequired
    @GetMapping("/applicants/examcode")
    public void saveExamCode() throws Exception {
        applicantService.saveExamCode() ;
    }


}
