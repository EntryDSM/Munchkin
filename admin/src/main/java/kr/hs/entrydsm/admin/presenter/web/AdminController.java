package kr.hs.entrydsm.admin.presenter.web;

import kr.hs.entrydsm.admin.usecase.AdminService;
import kr.hs.entrydsm.admin.usecase.ApplicantService;
import kr.hs.entrydsm.admin.usecase.dto.response.ApplicantDetailResponse;
import kr.hs.entrydsm.admin.usecase.dto.response.ApplicantsResponse;
import kr.hs.entrydsm.admin.usecase.dto.request.ScheduleRequest;
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
    @PatchMapping("/{reciept-code}")
    public void updateStatus(@PathVariable(name = "reciept-code") int recieptCode,
                             @RequestParam(required = false) boolean isPrintedArrived,
                             @RequestParam(required = false) boolean isPaid,
                             @RequestParam(required = false) boolean isSubmit) {
        applicantService.updateStatus(recieptCode, isPrintedArrived, isPaid, isSubmit);
    }

    @AdminJWTRequired
    @GetMapping("/applicants")
    public ApplicantsResponse getApplicants(Pageable page,
                                            @RequestParam(required = false, name = "is-daejeon") boolean isDaejeon,
                                            @RequestParam(required = false, name = "is-nationwide") boolean isNationwide,
                                            @RequestParam(required = false, name = "is-printed-arrived") boolean isPrintedArrived,
                                            @RequestParam(required = false, name = "is-paid") boolean isPaid,
                                            @RequestParam(required = false, name = "is-common") boolean isCommon,
                                            @RequestParam(required = false, name = "is-meiseter") boolean isMeister,
                                            @RequestParam(required = false, name = "is-social") boolean isSocial,
                                            @RequestParam(required = false, name = "reciept-code") int recieptCode,
                                            @RequestParam(required = false, name = "school-name") String schoolName,
                                            @RequestParam(required = false, name = "telephone-number") String telephoneNumber,
                                            @RequestParam(required = false) String name) {
        return applicantService.getApplicants(page, isDaejeon, isNationwide, isPrintedArrived, isPaid, isCommon,
                isMeister, isSocial, recieptCode, schoolName, telephoneNumber, name);
    }

    @AdminJWTRequired
    @GetMapping("/{reciept-code}")
    public ApplicantDetailResponse getDetail(@PathVariable(name = "reciept-code") int recieptCode) {
        return applicantService.getDetail(recieptCode);
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

}
