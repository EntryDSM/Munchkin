package kr.hs.entrydsm.admin.presenter.web;

import kr.hs.entrydsm.admin.usecase.excel.ExcelService;
import kr.hs.entrydsm.common.context.auth.token.AdminJWTRequired;
import kr.hs.entrydsm.common.context.beans.Published;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequiredArgsConstructor
@Published
@RequestMapping("/admin/excel")
@RestController
public class ExcelController {

    private final ExcelService excelService;

    @AdminJWTRequired
    @GetMapping("/admission-ticket/{receipt-code}")
    public void createAdmissionTicket(@PathVariable("receipt-code") Long receiptCode) throws IOException {
        excelService.createAdmissionTicket(receiptCode);
    }

    @AdminJWTRequired
    @GetMapping("/applicants")
    public void createApplicantInformation() throws IOException {
        excelService.createApplicantInformation();
    }

    @AdminJWTRequired
    @GetMapping
    public void getAllExcels() throws IOException {
        excelService.getAllExcels();
    }

}
