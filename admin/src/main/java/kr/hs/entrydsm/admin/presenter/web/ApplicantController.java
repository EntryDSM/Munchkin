package kr.hs.entrydsm.admin.presenter.web;

import kr.hs.entrydsm.admin.usecase.applicant.ApplicantService;
import kr.hs.entrydsm.admin.usecase.dto.applicant.ApplicantsResponse;
import kr.hs.entrydsm.common.context.auth.token.AdminJWTRequired;
import kr.hs.entrydsm.common.context.beans.Published;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Published
@RequestMapping("/admin")
@RestController
public class ApplicantController {

    private final ApplicantService applicantService;

    @AdminJWTRequired
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/applicant/{receipt-code}")
    public void changeIsPrintedArrived(@PathVariable(name = "receipt-code") long receiptCode,
                                       @RequestParam(name = "is_printed_arrived") boolean isPrintedArrived) {
        applicantService.changePrintArrivedOrNot(receiptCode, isPrintedArrived);
    }

    @AdminJWTRequired
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/applicant/status/{receipt-code}")
    public void changeIsSubmit(@PathVariable(name = "receipt-code") long receiptCode) {
        applicantService.changeIsSubmitFalse(receiptCode);
    }

    @AdminJWTRequired
    @GetMapping("/applicant/{receipt-code}")
    public ResponseEntity getDetailApplicantInfo(@PathVariable("receipt-code") int receiptCode) {
        return applicantService.getDetailApplicantInfo(receiptCode);
    }

    @AdminJWTRequired
    @GetMapping("/applicants")
    public ApplicantsResponse getApplicants(Pageable page,
                                            @RequestParam(name = "receipt_code", defaultValue = "") @Nullable Long receiptCode,
                                            @RequestParam(name = "is_daejeon") boolean isDaejeon,
                                            @RequestParam(name = "is_nationwide") boolean isNationwide,
                                            @RequestParam(name = "telephone", defaultValue = "") @Nullable String telephoneNumber,
                                            @RequestParam(defaultValue = "") @Nullable String name,
                                            @RequestParam(name = "is_common") boolean isCommon,
                                            @RequestParam(name = "is_meister") boolean isMeister,
                                            @RequestParam(name = "is_social") boolean isSocial,
                                            @RequestParam(name = "is_printed_arrived", defaultValue = "") @Nullable Boolean isPrintedArrived) {
        return applicantService.getApplicants(page, receiptCode, isDaejeon, isNationwide, telephoneNumber, name, isCommon, isMeister, isSocial, isPrintedArrived);
    }

}
