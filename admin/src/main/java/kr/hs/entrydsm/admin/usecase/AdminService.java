package kr.hs.entrydsm.admin.usecase;

import kr.hs.entrydsm.admin.usecase.dto.ScheduleRequest;
import kr.hs.entrydsm.admin.usecase.dto.ScheduleResponse;

public interface AdminService {
    void updateSchedules(ScheduleRequest scheduleRequest);
    ScheduleResponse getSchedules();
}
