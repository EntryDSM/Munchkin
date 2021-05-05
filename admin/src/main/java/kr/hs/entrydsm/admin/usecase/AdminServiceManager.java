package kr.hs.entrydsm.admin.usecase;

import kr.hs.entrydsm.admin.entity.admin.Admin;
import kr.hs.entrydsm.admin.entity.schedule.Schedule;
import kr.hs.entrydsm.admin.entity.admin.Permission;
import kr.hs.entrydsm.admin.entity.admin.AdminRepository;
import kr.hs.entrydsm.admin.entity.schedule.ScheduleRepository;
import kr.hs.entrydsm.admin.usecase.dto.request.ScheduleRequest;
import kr.hs.entrydsm.admin.usecase.dto.response.ScheduleResponse;
import kr.hs.entrydsm.admin.usecase.dto.Schedules;
import kr.hs.entrydsm.admin.usecase.exception.AdminNotFoundException;
import kr.hs.entrydsm.admin.usecase.exception.UserNotAccessibleException;
import kr.hs.entrydsm.common.context.auth.manager.AuthenticationManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminServiceManager implements AdminService {

    private final AdminRepository adminRepository;
    private final ScheduleRepository scheduleRepository;

    private final AuthenticationManager authenticationManager;

    //스케줄 관련 api
    @Override
    public void updateSchedules(ScheduleRequest scheduleRequest) {
        Admin admin = adminRepository.findById(authenticationManager.getAdminId())
                .orElseThrow(AdminNotFoundException::new);
        Schedule schedule = new Schedule();

        if(admin.getPermission().equals(Permission.TEACHER)) {
            schedule.update(scheduleRequest);
        }
        else {
            throw new UserNotAccessibleException();
        }
    }

    @Override
    public ScheduleResponse getSchedules() {
        List<Schedule> schedule = scheduleRepository.findAllBy();
        List<Schedules> schedules = new ArrayList<>();

        for (Schedule s : schedule) {
            schedules.add(
                    Schedules.builder()
                            .year(s.getYear())
                            .type(s.getType())
                            .date(s.getDate())
                            .build()
            );
        }

        return ScheduleResponse.builder()
                .schedules(schedules)
                .build();
    }

}
