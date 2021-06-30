package kr.hs.entrydsm.admin.presenter.web;

import kr.hs.entrydsm.admin.usecase.applicant.ApplicantService;
import kr.hs.entrydsm.admin.usecase.dto.response.ApplicantsResponse;
import kr.hs.entrydsm.common.context.auth.token.AdminJWTRequired;
import kr.hs.entrydsm.common.context.beans.Published;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Published
@RequestMapping("/admin")
@RestController
public class ApplicantController {

    private final ApplicantService applicantService;

    //지원자 원서 도착여부 상태 변경
    @AdminJWTRequired
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/applicant/printed-arrived")
    public void updateIsPrintedArrived(@RequestParam(name = "receipt-code") long receiptCode,
                                       @RequestParam(name = "is-printed-arrived") boolean isPrintedArrived) {
        applicantService.changeIsPrintedArrived(receiptCode, isPrintedArrived);
    }

    //지원자 정보 상세 보기
    @AdminJWTRequired
    @GetMapping("/applicant/{receipt-code}")
    public Object getDetail(@PathVariable("receipt-code") int receiptCode) {
        return applicantService.getDetail(receiptCode);
    }

    //지원자 목록 보기
    @AdminJWTRequired
    @GetMapping("/applicants")
    public ApplicantsResponse getApplicants(Pageable page,
                                            @RequestParam(name = "receipt-code", required = false) @Nullable Long receiptCode,
                                            @RequestParam(name = "is-daejeon") boolean isDaejeon,
                                            @RequestParam(name = "is-nationwide") boolean isNationwide,
                                            @RequestParam(name = "telephone-number", required = false) @Nullable String telephoneNumber,
                                            @RequestParam(required = false) @Nullable String name,
                                            @RequestParam(name = "is-common") boolean isCommon,
                                            @RequestParam(name = "is-meister") boolean isMeister,
                                            @RequestParam(name = "is-social") boolean isSocial,
                                            @RequestParam(name = "is-printed-arrived", required = false) @Nullable Boolean isPrintedArrived) {
        return applicantService.getApplicants(page, receiptCode, isDaejeon, isNationwide, telephoneNumber, name, isCommon, isMeister, isSocial, isPrintedArrived);
    }

    //지원자 수험번호 저장
    @AdminJWTRequired
    @GetMapping("/applicants/examcode")
    public void saveExamCode() throws Exception {
        applicantService.saveExamCode() ;
    }


}
