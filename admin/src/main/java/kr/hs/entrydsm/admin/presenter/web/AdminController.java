package kr.hs.entrydsm.admin.presenter.web;

import kr.hs.entrydsm.admin.usecase.ApplicantService;
import kr.hs.entrydsm.admin.usecase.dto.ApplicantDetailResponse;
import kr.hs.entrydsm.admin.usecase.dto.ApplicantsResponse;
import kr.hs.entrydsm.common.context.beans.Published;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@Published
@RequestMapping("/admin")
@RestController
public class AdminController {

    private final ApplicantService applicantService;

    @PatchMapping("/{reciept-code}")
    public void updateStatus(@PathVariable(name = "reciept-code") Integer recieptCode,
                             @RequestParam(required = false) boolean isPrintedArrived,
                             @RequestParam(required = false) boolean isPaid,
                             @RequestParam(required = false) boolean isSubmit) {
        applicantService.updateStatus(recieptCode, isPrintedArrived, isPaid, isSubmit);
    }

    @GetMapping("/applicants")
    public List<ApplicantsResponse> getApplicants(Pageable page,
                                                 @RequestParam(required = false, name = "is-daejeon") boolean isDaejeon,
                                                 @RequestParam(required = false, name = "is-nationwide") boolean isNationwide,
                                                 @RequestParam(required = false, name = "is-printed-arrived") boolean isPrintedArrived,
                                                 @RequestParam(required = false, name = "is-paid") boolean isPaid,
                                                 @RequestParam(required = false, name = "is-common") boolean isCommon,
                                                 @RequestParam(required = false, name = "is-meiseter") boolean isMeiseter,
                                                 @RequestParam(required = false, name = "is-social") boolean isSocial,
                                                 @RequestParam(required = false, name = "reciept-code") Integer recieptCode,
                                                 @RequestParam(required = false, name = "school-name") String schoolName,
                                                 @RequestParam(required = false, name = "telephone-number") String telephoneNumber,
                                                 @RequestParam(required = false) String name) {
        return applicantService.getApplicants(page, isDaejeon, isNationwide, isPrintedArrived, isPaid, isCommon,
                isMeiseter, isSocial, recieptCode, schoolName, telephoneNumber, name);
    }

    @GetMapping("/{reciept-code}")
    public ApplicantDetailResponse getDetail(@PathVariable(name = "reciept-code") Integer recieptCode) {
        return applicantService.getDetail(recieptCode);
    }


}
