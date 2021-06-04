package kr.hs.entrydsm.admin.usecase;

import kr.hs.entrydsm.admin.entity.admin.Admin;
import kr.hs.entrydsm.admin.entity.schedule.Schedule;
import kr.hs.entrydsm.admin.entity.admin.Permission;
import kr.hs.entrydsm.admin.entity.admin.AdminRepository;
import kr.hs.entrydsm.admin.entity.schedule.ScheduleRepository;
import kr.hs.entrydsm.admin.infrastructure.database.ScheduleRepositoryManager;
import kr.hs.entrydsm.admin.integrate.score.ScoreRepository;
import kr.hs.entrydsm.admin.usecase.dto.ApplicationStatus;
import kr.hs.entrydsm.admin.usecase.dto.CommonScore;
import kr.hs.entrydsm.admin.usecase.dto.request.ScheduleRequest;
import kr.hs.entrydsm.admin.usecase.dto.response.ReceiptStatusResponse;
import kr.hs.entrydsm.admin.usecase.dto.response.ScheduleResponse;
import kr.hs.entrydsm.admin.usecase.dto.Schedules;
import kr.hs.entrydsm.admin.usecase.dto.SpecialScore;
import kr.hs.entrydsm.admin.usecase.exception.AdminNotFoundException;
import kr.hs.entrydsm.admin.usecase.exception.UserNotAccessibleException;
import kr.hs.entrydsm.common.context.auth.manager.AuthenticationManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminServiceManager implements AdminService {

    private final AdminRepository adminRepository;
    private final ScheduleRepository scheduleRepository;
    private final ScheduleRepositoryManager scheduleRepositoryManager;
    private final ScoreRepository scoreRepository;

    private final AuthenticationManager authenticationManager;

    private static final Integer RECRUITMENT_NUMBER_OF_PEOPLE = 80;
    private static final Integer COMMON_ADMISSION_NUMBER_OF_RECRUITMENT = 40;
    private static final Integer MEISTER_ADMISSION_NUMBER_OF_RECRUITMENT = 36;
    private static final Integer SOCIAL_ADMISSION_NUMBER_OF_RECRUITMENT = 4;

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

        scheduleRepositoryManager.save(schedule);
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

    @Override
    public ReceiptStatusResponse getStatics() {
        ApplicationStatus applicationStatus = scoreRepository.getScore();
        int commonCount = applicationStatus.getCommonScore().size();
        int meisterCount = applicationStatus.getMeisterScore().size();
        int socialCount = applicationStatus.getSpecialScore().size();

        int totalApplicantCount = commonCount + meisterCount + socialCount;
        double totalCompetitionRate = (double)totalApplicantCount / RECRUITMENT_NUMBER_OF_PEOPLE;

        CommonScore commonScore = new CommonScore();
        SpecialScore meisterScore = new SpecialScore();
        SpecialScore socialScore = new SpecialScore();

        //일반전형 점수별 지원자 통계
        for(BigDecimal score : applicationStatus.getCommonScore()) {
            double s = Double.parseDouble(String.valueOf(score));

            if(s <= 80) commonScore.plus80();
            else if(s <= 90) commonScore.plus81_90();
            else if(s <= 100) commonScore.plus91_100();
            else if(s <= 110) commonScore.plus101_110();
            else if(s <= 120) commonScore.plus111_120();
            else if(s <= 130) commonScore.plus121_130();
            else if(s <= 140) commonScore.plus131_140();
            else if(s <= 150) commonScore.plus141_150();
        }

        //마이스터전형
        for(BigDecimal score : applicationStatus.getMeisterScore()) {
            double s = Double.parseDouble(String.valueOf(score));

            if(s <= 20) meisterScore.plus20();
            else if(s <= 30) meisterScore.plus21_30();
            else if(s <= 40) meisterScore.plus31_40();
            else if(s <= 50) meisterScore.plus41_50();
            else if(s <= 60) meisterScore.plus51_60();
            else if(s <= 70) meisterScore.plus61_70();
            else if(s <= 80) meisterScore.plus71_80();
            else if(s <= 90) meisterScore.plus81_90();
        }

        //사회통합
        //마이스터전형
        for(BigDecimal score : applicationStatus.getSpecialScore()) {
            double s = Double.parseDouble(String.valueOf(score));

            if(s <= 20) socialScore.plus20();
            else if(s <= 30) socialScore.plus21_30();
            else if(s <= 40) socialScore.plus31_40();
            else if(s <= 50) socialScore.plus41_50();
            else if(s <= 60) socialScore.plus51_60();
            else if(s <= 70) socialScore.plus61_70();
            else if(s <= 80) socialScore.plus71_80();
            else if(s <= 90) socialScore.plus81_90();
        }

        return ReceiptStatusResponse.builder()
                .totalApplicantCount(totalApplicantCount)
                .totalCompetitionRate(totalCompetitionRate)
                .commonScore(commonScore)
                .meisterScore(meisterScore)
                .socialScore(socialScore)
                .commonCount(commonCount)
                .commonCompetitionRate((double)commonCount/COMMON_ADMISSION_NUMBER_OF_RECRUITMENT)
                .meisterCount(meisterCount)
                .meisterCompetitionRate((double)meisterCount/MEISTER_ADMISSION_NUMBER_OF_RECRUITMENT)
                .socialCount(socialCount)
                .socialCompetitionRate((double)socialCount/SOCIAL_ADMISSION_NUMBER_OF_RECRUITMENT)
                .build();
    }

}
