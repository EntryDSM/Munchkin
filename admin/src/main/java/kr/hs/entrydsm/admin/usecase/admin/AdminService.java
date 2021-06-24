package kr.hs.entrydsm.admin.usecase.admin;

import kr.hs.entrydsm.admin.usecase.dto.response.ReceiptStatusResponse;

public interface AdminService {
    ReceiptStatusResponse getStatics();
    void deleteAll();
}
