package kr.hs.entrydsm.admin.presenter.web;

import kr.hs.entrydsm.admin.usecase.ExcelService;
import kr.hs.entrydsm.common.context.beans.Published;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequiredArgsConstructor
@Published
@RequestMapping("/admin")
@RestController
public class AdminExcelController {

    private final ExcelService excelService;

    @GetMapping("/excel/admissionTicket/{receiptCode}")
    public void createAdmissionTicket(@PathVariable int receiptCode) throws IOException {
        excelService.createAdmissionTicket(receiptCode);
    }

    @GetMapping("/excel/applicantInfo")
    public void createApplicantInformation() {
        excelService.createApplicantInformation();
    }

}
