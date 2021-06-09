package kr.hs.entrydsm.admin.presenter.web;

import kr.hs.entrydsm.admin.usecase.schedule.ScheduleService;
import kr.hs.entrydsm.admin.usecase.dto.request.ScheduleRequest;
import kr.hs.entrydsm.admin.usecase.dto.response.ScheduleResponse;
import kr.hs.entrydsm.common.context.auth.token.AdminJWTRequired;
import kr.hs.entrydsm.common.context.beans.Published;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@Published
@RequestMapping("/admin")
@RestController
public class ScheduleController {

    private final ScheduleService scheduleService;

    //스케줄 업데이트
    @AdminJWTRequired
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/schedules")
    public void updateSchedules(@RequestBody @Valid ScheduleRequest scheduleRequest) {
        scheduleService.updateSchedules(scheduleRequest);
    }

    //스케줄 보기(유저에서도 사용)
    @GetMapping("/schedules")
    public ScheduleResponse getSchedules() {
        return scheduleService.getSchedules();
    }

}
