package kr.hs.entrydsm.admin.usecase.schedule;

import kr.hs.entrydsm.admin.usecase.dto.schedule.ScheduleRequest;
import kr.hs.entrydsm.admin.usecase.dto.schedule.ScheduleResponse;

public interface ScheduleService {
    void updateSchedule(ScheduleRequest scheduleRequest);
    ScheduleResponse getSchedules();
}

