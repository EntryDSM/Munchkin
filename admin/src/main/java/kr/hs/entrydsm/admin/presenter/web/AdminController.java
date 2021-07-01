package kr.hs.entrydsm.admin.presenter.web;

import kr.hs.entrydsm.admin.usecase.admin.AdminService;
import kr.hs.entrydsm.admin.usecase.dto.applicant.ReceiptStatusResponse;
import kr.hs.entrydsm.common.context.auth.token.AdminJWTRequired;
import kr.hs.entrydsm.common.context.beans.Published;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Published
@RequestMapping("/admin")
@RestController
public class AdminController {

    private final AdminService adminService;

    @AdminJWTRequired
    @GetMapping("/statics")
    public ReceiptStatusResponse getStatics() {
        return adminService.getApplyStaticsStatistics();
    }

    @AdminJWTRequired
    @DeleteMapping("/data")
    public void deleteAllTables() {
        adminService.deleteAllTables();
    }

}
