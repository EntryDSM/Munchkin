package kr.hs.entrydsm.admin.presenter.web;

import kr.hs.entrydsm.admin.usecase.admin.AdminService;
import kr.hs.entrydsm.admin.usecase.excel.ExcelService;
import kr.hs.entrydsm.admin.usecase.dto.response.ReceiptStatusResponse;
import kr.hs.entrydsm.common.context.auth.token.AdminJWTRequired;
import kr.hs.entrydsm.common.context.beans.Published;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequiredArgsConstructor
@Published
@RequestMapping("/admin")
@RestController
public class AdminController {

    private final AdminService adminService;
    private final ExcelService excelService;

    //접수 현황 통계
    @AdminJWTRequired
    @GetMapping("/statics")
    public ReceiptStatusResponse getStatics() {
        return adminService.getStatics();
    }

    //수험표 만들기
    @AdminJWTRequired
    @GetMapping("/excel/admission-ticket/{receipt-code}")
    public void createAdmissionTicket(@PathVariable("receipt-code") Long receiptCode) throws IOException {
        excelService.createAdmissionTicket(receiptCode);
    }

    //지원자 목록 엑셀
    @AdminJWTRequired
    @GetMapping("/excel/applicants")
    public void createApplicantInformation() throws IOException {
        excelService.createApplicantInformation();
    }

    @AdminJWTRequired
    @GetMapping("/excel")
    public void getAllExcels() throws IOException {
        excelService.getAllExcels();
    }

    @AdminJWTRequired
    @DeleteMapping("/data")
    public void deleteAll() {
        adminService.deleteAll();
    }

}
