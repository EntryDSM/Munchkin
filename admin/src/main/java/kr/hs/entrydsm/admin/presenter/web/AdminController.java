package kr.hs.entrydsm.admin.presenter.web;

import kr.hs.entrydsm.admin.usecase.AdminService;
import kr.hs.entrydsm.admin.usecase.ApplicantService;
import kr.hs.entrydsm.admin.usecase.dto.response.ApplicantDetailResponse;
import kr.hs.entrydsm.admin.usecase.dto.response.ApplicantsResponse;
import kr.hs.entrydsm.admin.usecase.dto.request.ScheduleRequest;
import kr.hs.entrydsm.admin.usecase.dto.response.ReceiptStatusResponse;
import kr.hs.entrydsm.admin.usecase.dto.response.ScheduleResponse;
import kr.hs.entrydsm.common.context.auth.token.AdminJWTRequired;
import kr.hs.entrydsm.common.context.beans.Published;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Published
@RequestMapping("/admin")
@RestController
public class AdminController {

    private final ApplicantService applicantService;
    private final AdminService adminService;

    //지원자
    @AdminJWTRequired
    @PatchMapping("/applicant/printed-arrived")
    public void updateIsprintedArrived(@RequestParam(name = "receipt-code") int receiptCode,
                             @RequestParam(required = false, name = "is-printed-arrived") boolean isPrintedArrived) {
        applicantService.changeIsPrintedArrived(receiptCode, isPrintedArrived);
    }

    @AdminJWTRequired
    @GetMapping("/applicants")
    public ApplicantsResponse getApplicants(Pageable page,
                                            @RequestParam(required = false, name = "reciept-code") Long receiptCode,
                                            @RequestParam(name = "is-daejeon") boolean isDaejeon,
                                            @RequestParam(name = "is-nationwide") boolean isNationwide,
                                            @RequestParam(required = false, name = "telephone-number") String telephoneNumber,
                                            @RequestParam(required = false) String name,
                                            @RequestParam(name = "is-common") boolean isCommon,
                                            @RequestParam(name = "is-meiseter") boolean isMeister,
                                            @RequestParam(name = "is-social") boolean isSocial,
                                            @RequestParam(required = false, name = "is-printed-arrived") boolean isPrintedArrived,
                                            @RequestParam(required = false, name = "is-paid") boolean isPaid) {
        return applicantService.getApplicants(page, receiptCode, isDaejeon, isNationwide, telephoneNumber, name, isCommon, isMeister, isSocial, isPrintedArrived, isPaid);
    }

    @AdminJWTRequired
    @GetMapping("/{receipt-code}")
    public ApplicantDetailResponse getDetail(@PathVariable(name = "receipt-code") int recieptCode) {
        return applicantService.getDetail(recieptCode);
    }

    @AdminJWTRequired
    @GetMapping("/applicants/examcode")
    public void saveExamCode() throws Exception {
        applicantService.saveExamCode();
    }

    //전형 일자
    @PatchMapping("/schedules")
    public void updateSchedules(@RequestBody @Validated ScheduleRequest scheduleRequest) {
        adminService.updateSchedules(scheduleRequest);
    }

    @AdminJWTRequired
    @GetMapping("/schedules")
    public ScheduleResponse getSchedules() {
        return adminService.getSchedules();
    }

    //접수 현황 통계
    @AdminJWTRequired
    @GetMapping("/statics")
    public ReceiptStatusResponse getStatics() {
        return adminService.getStatics();
    }

}
