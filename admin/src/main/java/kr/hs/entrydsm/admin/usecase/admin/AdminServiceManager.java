package kr.hs.entrydsm.admin.usecase.admin;

import kr.hs.entrydsm.admin.entity.admin.AdminRepository;
import kr.hs.entrydsm.admin.entity.schedule.Schedule;
import kr.hs.entrydsm.admin.entity.schedule.ScheduleRepository;
import kr.hs.entrydsm.admin.entity.schedule.Type;
import kr.hs.entrydsm.admin.integrate.main.MainRepository;
import kr.hs.entrydsm.admin.integrate.score.ScoreRepository;
import kr.hs.entrydsm.admin.usecase.dto.ApplicationStatus;
import kr.hs.entrydsm.admin.usecase.dto.CommonScore;
import kr.hs.entrydsm.admin.usecase.dto.SpecialScore;
import kr.hs.entrydsm.admin.usecase.dto.response.ReceiptStatusResponse;
import kr.hs.entrydsm.admin.usecase.exception.AdminNotFoundException;
import kr.hs.entrydsm.admin.usecase.exception.ApplicationPeriodNotOverException;
import kr.hs.entrydsm.admin.usecase.exception.ScheduleNotFoundException;
import kr.hs.entrydsm.common.context.auth.manager.AuthenticationManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class AdminServiceManager implements AdminService {

    private final ScoreRepository scoreRepository;
    private final AdminRepository adminRepository;
    private final AuthenticationManager authenticationManager;
    private final MainRepository mainRepository;
    private final ScheduleRepository scheduleRepository;

    private static final Integer RECRUITMENT_NUMBER_OF_PEOPLE = 80;
    private static final Integer COMMON_ADMISSION_NUMBER_OF_RECRUITMENT = 40;
    private static final Integer MEISTER_ADMISSION_NUMBER_OF_RECRUITMENT = 36;
    private static final Integer SOCIAL_ADMISSION_NUMBER_OF_RECRUITMENT = 4;

    @Override
    public ReceiptStatusResponse getApplyStaticsStatistics() {
        adminRepository.findById(authenticationManager.getAdminId())
                .orElseThrow(AdminNotFoundException::new);

        ApplicationStatus applicationStatus = scoreRepository.getScore();
        int commonCount = applicationStatus.getCommonScores().size();
        int meisterCount = applicationStatus.getMeisterScores().size();
        int socialCount = applicationStatus.getSpecialScores().size();

        int totalApplicantCount = commonCount + meisterCount + socialCount;
        double totalCompetitionRate = (double)totalApplicantCount / RECRUITMENT_NUMBER_OF_PEOPLE;

        CommonScore commonScore = new CommonScore();
        SpecialScore meisterScore = new SpecialScore();
        SpecialScore socialScore = new SpecialScore();

        for(BigDecimal scoreDecimal : applicationStatus.getCommonScores()) {
            double score = Double.parseDouble(String.valueOf(scoreDecimal));
            commonScore.addScore(Math.round(score));
        }

        for(BigDecimal scoreDecimal : applicationStatus.getMeisterScores()) {
            double score = Double.parseDouble(String.valueOf(scoreDecimal));
            score = Math.round(score);
            meisterScore.addScore(score);
        }

        for(BigDecimal scoreDecimal : applicationStatus.getSpecialScores()) {
            double score = Double.parseDouble(String.valueOf(scoreDecimal));
            score = Math.round(score);
            meisterScore.addScore(score);
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

    @Override
    public void deleteAllTables() {
        LocalDate now = LocalDate.now();
        Schedule secondAnnouncement = scheduleRepository.findByYearAndType(String.valueOf(now.getYear()), Type.SECOND_ANNOUNCEMENT)
                .orElseThrow(ScheduleNotFoundException::new);
        if(now.isBefore(secondAnnouncement.getDate())) {
            throw new ApplicationPeriodNotOverException();
        }
        mainRepository.deleteAll();
    }

}
