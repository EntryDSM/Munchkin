package kr.hs.entrydsm.admin.usecase.schedule;

import kr.hs.entrydsm.admin.usecase.dto.request.ScheduleRequest;
import kr.hs.entrydsm.admin.usecase.dto.response.ScheduleResponse;

public interface ScheduleService {
    void updateSchedules(ScheduleRequest scheduleRequest);
    ScheduleResponse getSchedules();
}

