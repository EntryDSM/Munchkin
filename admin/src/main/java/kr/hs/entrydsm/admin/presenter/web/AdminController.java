package kr.hs.entrydsm.admin.presenter.web;

import kr.hs.entrydsm.admin.usecase.AdminService;
import kr.hs.entrydsm.admin.usecase.ApplicantService;
import kr.hs.entrydsm.admin.usecase.ExcelService;
import kr.hs.entrydsm.admin.usecase.dto.response.ApplicantsResponse;
import kr.hs.entrydsm.admin.usecase.dto.request.ScheduleRequest;
import kr.hs.entrydsm.admin.usecase.dto.response.ReceiptStatusResponse;
import kr.hs.entrydsm.admin.usecase.dto.response.ScheduleResponse;
import kr.hs.entrydsm.common.context.auth.token.AdminJWTRequired;
import kr.hs.entrydsm.common.context.beans.Published;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RequiredArgsConstructor
@Published
@RequestMapping("/admin")
@RestController
public class AdminController {

    private final ApplicantService applicantService;
    private final AdminService adminService;

    private final ExcelService excelService;

    //지원자
    @AdminJWTRequired
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/applicant/printed-arrived")
    public void updateIsprintedArrived(@RequestParam(name = "receipt-code") int receiptCode,
                             @RequestParam(required = false, name = "is-printed-arrived") boolean isPrintedArrived) {
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
    @GetMapping("/applicants/examcode")
    public void saveExamCode() throws Exception {
        applicantService.saveExamCode();
    }

    //전형 일자
    @AdminJWTRequired
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/schedules")
    public void updateSchedules(@RequestBody @Valid ScheduleRequest scheduleRequest) {
        adminService.updateSchedules(scheduleRequest);
    }

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

    @AdminJWTRequired
    @GetMapping("/excel/applicants")
    public void createAdmissionTicket() {
        excelService.createApplicantInformation();
    }

    @AdminJWTRequired
    @GetMapping("/excel/admissionTicket/{receipt-code}")
    public void createApplicantInformation(@PathVariable("receipt-code") Long receiptCode) throws IOException {
        excelService.createAdmissionTicket(receiptCode);
    }

}
