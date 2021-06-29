package kr.hs.entrydsm.admin.usecase.schedule;

import kr.hs.entrydsm.admin.entity.schedule.Schedule;
import kr.hs.entrydsm.admin.entity.schedule.ScheduleRepository;
import kr.hs.entrydsm.admin.entity.schedule.Type;
import kr.hs.entrydsm.admin.infrastructure.database.ScheduleRepositoryManager;
import kr.hs.entrydsm.admin.usecase.dto.Schedules;
import kr.hs.entrydsm.admin.usecase.dto.request.ScheduleRequest;
import kr.hs.entrydsm.admin.usecase.dto.response.ScheduleResponse;
import kr.hs.entrydsm.admin.usecase.exception.ScheduleNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ScheduleServiceManager implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleRepositoryManager scheduleRepositoryManager;

    @Override //스케줄 업데이트
    public void updateSchedules(ScheduleRequest scheduleRequest) {
        String year = scheduleRequest.getDate().substring(1, 4);
        Schedule schedule = scheduleRepository.findByYearAndType(year, scheduleRequest.getType())
                .orElseThrow(ScheduleNotFoundException::new);

        schedule.update(year, scheduleRequest.getDate());
        scheduleRepositoryManager.save(schedule);
    }

    @Override //스케줄 보여주기
    public ScheduleResponse getSchedules() {
        List<Schedule> schedule = scheduleRepository.findAllBy();
        List<Schedules> schedules = new ArrayList<>();

        for (Schedule s : schedule) {
            schedules.add(
                    Schedules.builder()
                            .type(s.getType())
                            .date(s.getDate().toString())
                            .build()
            );
        }

        return ScheduleResponse.builder()
                .schedules(schedules)
                .currentStatus(getCurrentStatus())
                .build();
    }

    private String getCurrentStatus() {
        LocalDate now = LocalDate.now();
        String year = String.valueOf(now.getYear());

        Schedule startDate = scheduleRepository.findByYearAndType(year, Type.START_DATE)
                .orElseThrow(ScheduleNotFoundException::new);
        Schedule endDate = scheduleRepository.findByYearAndType(year, Type.END_DATE)
                .orElseThrow(ScheduleNotFoundException::new);
        Schedule firstAnnounce = scheduleRepository.findByYearAndType(year, Type.FIRST_ANNOUNCEMENT)
                .orElseThrow(ScheduleNotFoundException::new);
        Schedule secondAnnounce = scheduleRepository.findByYearAndType(year, Type.SECOND_ANNOUNCEMENT)
                .orElseThrow(ScheduleNotFoundException::new);
        Schedule interview = scheduleRepository.findByYearAndType(year, Type.INTERVIEW)
                .orElseThrow(ScheduleNotFoundException::new);

        if(now.isBefore(startDate.getDate())) {
            return "NOT_APPLICATION_PERIOD";
        } else if(!now.isAfter(endDate.getDate())) {
            return startDate.getType().toString(); //START_DATE
        } else if(now.isBefore(firstAnnounce.getDate())) {
            return "BEFORE_FIRST_ANNOUNCEMENT"; //서류 합격 발표 전
        } else if(now.isEqual(firstAnnounce.getDate())) {
            return firstAnnounce.getType().toString();
        } else if(now.isBefore(interview.getDate())) {
            return "BEFORE_INTERVIEW";
        } else if(now.isEqual(interview.getDate())) {
            return interview.getType().toString();
        } else if(now.isBefore(secondAnnounce.getDate())) {
            return "BEFORE_SECOND_ANNOUNCEMENT";
        } else if(now.isEqual(secondAnnounce.getDate())) {
            return secondAnnounce.getType().toString();
        } else {
            return "END";
        }
    }

}
