package kr.hs.entrydsm.admin.usecase;

import kr.hs.entrydsm.admin.usecase.dto.request.ScheduleRequest;
import kr.hs.entrydsm.admin.usecase.dto.response.ScheduleResponse;

public interface AdminService {
    void updateSchedules(ScheduleRequest scheduleRequest);
    ScheduleResponse getSchedules();
}
