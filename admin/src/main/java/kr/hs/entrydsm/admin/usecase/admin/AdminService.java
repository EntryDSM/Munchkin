package kr.hs.entrydsm.admin.usecase.admin;

import kr.hs.entrydsm.admin.usecase.dto.applicant.ReceiptStatusResponse;

public interface AdminService {
    ReceiptStatusResponse getApplyStaticsStatistics();
    void deleteAllTables();
}
