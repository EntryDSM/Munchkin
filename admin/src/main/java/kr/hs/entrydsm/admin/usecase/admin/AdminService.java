package kr.hs.entrydsm.admin.usecase.admin;

import kr.hs.entrydsm.admin.usecase.dto.request.ScheduleRequest;
import kr.hs.entrydsm.admin.usecase.dto.response.ReceiptStatusResponse;
import kr.hs.entrydsm.admin.usecase.dto.response.ScheduleResponse;

public interface AdminService {
    ReceiptStatusResponse getStatics();
}
