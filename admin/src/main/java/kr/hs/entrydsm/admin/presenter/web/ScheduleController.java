package kr.hs.entrydsm.admin.presenter.web;

import kr.hs.entrydsm.admin.usecase.schedule.ScheduleService;
import kr.hs.entrydsm.admin.usecase.dto.schedule.ScheduleRequest;
import kr.hs.entrydsm.admin.usecase.dto.schedule.ScheduleResponse;
import kr.hs.entrydsm.common.context.auth.token.AdminJWTRequired;
import kr.hs.entrydsm.common.context.beans.Published;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@Published
@RequestMapping("/admin/schedules")
@RestController
public class ScheduleController {

    private final ScheduleService scheduleService;

    @AdminJWTRequired
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping
    public void updateSchedules(@RequestBody @Valid ScheduleRequest scheduleRequest) {
        scheduleService.updateSchedule(scheduleRequest);
    }

    @GetMapping
    public ScheduleResponse getSchedules() {
        return scheduleService.getSchedules();
    }

}
