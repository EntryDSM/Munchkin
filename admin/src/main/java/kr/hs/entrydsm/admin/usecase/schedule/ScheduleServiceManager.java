package kr.hs.entrydsm.admin.usecase.schedule;

import kr.hs.entrydsm.admin.entity.schedule.Schedule;
import kr.hs.entrydsm.admin.entity.schedule.ScheduleRepository;
import kr.hs.entrydsm.admin.entity.schedule.Type;
import kr.hs.entrydsm.admin.infrastructure.database.ScheduleRepositoryManager;
import kr.hs.entrydsm.admin.usecase.dto.schedule.Schedules;
import kr.hs.entrydsm.admin.usecase.dto.schedule.ScheduleRequest;
import kr.hs.entrydsm.admin.usecase.dto.schedule.ScheduleResponse;
import kr.hs.entrydsm.admin.usecase.exception.ScheduleNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ScheduleServiceManager implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleRepositoryManager scheduleRepositoryManager;

    @Override
    public void updateSchedule(ScheduleRequest scheduleRequest) {
        for (Schedules schedule : scheduleRequest.getSchedules()) {
            String year = schedule.getDate().substring(0, 4);
            Schedule updateSchedule = scheduleRepository.findByYearAndType(year, Type.valueOf(schedule.getType().toString()))
                    .orElseThrow(ScheduleNotFoundException::new);
            updateSchedule.update(year, parseLocalDateTime(schedule.getType(), schedule.getDate()));
            scheduleRepositoryManager.save(updateSchedule);
        }
    }

    @Override
    public ScheduleResponse getSchedules() {
        if (scheduleRepository.findAllBy().size() == 0) {
            createSchedules();
        }
        List<Schedule> schedule = scheduleRepository.findAllBy();

        return ScheduleResponse.builder()
                .schedules(schedule.stream()
                        .map(s -> Schedules.builder()
                                .type(s.getType())
                                .date(s.getDate().toString())
                                .build()
                        ).collect(Collectors.toList()))
                .currentStatus(getCurrentStatus())
                .build();
    }

    private LocalDateTime parseLocalDateTime(Type type, String date) {
        String[] day = date.split("-");
        int hour = 0;
        if(type.equals(Type.START_DATE)) hour = 8;
        else if(type.equals(Type.END_DATE)) hour = 17;
        return LocalDateTime.of(Integer.parseInt(day[0]), Integer.parseInt(day[1]), Integer.parseInt(day[2]), hour, 0);
    }

    private String getCurrentStatus() {
        LocalDateTime now = LocalDateTime.now();
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
            return "APPLICATION_PERIOD";
        } else if(now.isBefore(firstAnnounce.getDate())) {
            return "BEFORE_FIRST_ANNOUNCEMENT";
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

    private void createSchedules() {
        String[] types = new String[]{Type.START_DATE.toString(), Type.END_DATE.toString(), Type.FIRST_ANNOUNCEMENT.toString(), Type.INTERVIEW.toString(), Type.SECOND_ANNOUNCEMENT.toString()};
        for (String type : types) {
            scheduleRepository.save(Schedule.builder()
                    .year(String.valueOf(LocalDate.now().getYear()))
                    .type(Type.valueOf(type))
                    .date(LocalDateTime.now())
                    .build());
        }
    }

}
