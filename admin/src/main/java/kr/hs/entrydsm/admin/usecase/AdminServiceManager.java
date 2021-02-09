package kr.hs.entrydsm.admin.usecase;

import kr.hs.entrydsm.admin.domain.entity.Admin;
import kr.hs.entrydsm.admin.domain.entity.Schedule;
import kr.hs.entrydsm.admin.domain.entity.enums.Permission;
import kr.hs.entrydsm.admin.domain.repository.AdminRepository;
import kr.hs.entrydsm.admin.domain.repository.ScheduleRepository;
import kr.hs.entrydsm.admin.security.auth.AuthenticationFacade;
import kr.hs.entrydsm.admin.usecase.dto.ScheduleRequest;
import kr.hs.entrydsm.admin.usecase.dto.ScheduleResponse;
import kr.hs.entrydsm.admin.usecase.dto.Schedules;
import kr.hs.entrydsm.admin.usecase.exception.AdminNotFoundException;
import kr.hs.entrydsm.admin.usecase.exception.UserNotAccessibleException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminServiceManager implements AdminService {

    private final AdminRepository adminRepository;
    private final ScheduleRepository scheduleRepository;

    private final AuthenticationFacade authenticationFacade;

    @Override
    public void updateSchedules(ScheduleRequest scheduleRequest) {
        Admin admin = adminRepository.findById(authenticationFacade.getUserId())
                .orElseThrow(AdminNotFoundException::new);
        Schedule schedule = new Schedule();

        if(admin.getPermission() == Permission.TEACHER) {
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

        for(Schedule s : schedule) {
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
