package kr.hs.entrydsm.admin.usecase.admin;

import kr.hs.entrydsm.admin.entity.schedule.Schedule;
import kr.hs.entrydsm.admin.entity.schedule.ScheduleRepository;
import kr.hs.entrydsm.admin.entity.schedule.Type;
import kr.hs.entrydsm.admin.integrate.main.MainRepository;
import kr.hs.entrydsm.admin.integrate.score.ScoreRepository;
import kr.hs.entrydsm.admin.integrate.user.UserRepository;
import kr.hs.entrydsm.admin.usecase.dto.applicant.ApplicationStatus;
import kr.hs.entrydsm.admin.usecase.dto.score.CommonScoreResponse;
import kr.hs.entrydsm.admin.usecase.dto.score.SpecialScoreResponse;
import kr.hs.entrydsm.admin.usecase.dto.applicant.ReceiptStatusResponse;
import kr.hs.entrydsm.admin.usecase.exception.ApplicationPeriodNotOverException;
import kr.hs.entrydsm.admin.usecase.exception.ScheduleNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class AdminServiceManager implements AdminService {

    private final ScoreRepository scoreRepository;
    private final MainRepository mainRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    private static final double RECRUITMENT_NUMBER_OF_PEOPLE = 72.0;
    private static final double COMMON_ADMISSION_NUMBER_OF_RECRUITMENT = 40.0;
    private static final double MEISTER_ADMISSION_NUMBER_OF_RECRUITMENT = 18.0;
    private static final double SOCIAL_ADMISSION_NUMBER_OF_RECRUITMENT = 2.0;

    @Override
    public ReceiptStatusResponse getApplyStaticsStatistics() {
        ApplicationStatus applicationStatus = scoreRepository.getScore();
        int commonCount = applicationStatus.getCommonScores().size();
        int meisterCount = applicationStatus.getMeisterScores().size();
        int socialCount = applicationStatus.getSpecialScores().size();

        int totalApplicantCount = commonCount + meisterCount + socialCount;
        int totalSubmittedApplicantCount = userRepository.countSubmittedApplicantCount();
        double totalCompetitionRate = Double.parseDouble(String.format("%.2f", totalSubmittedApplicantCount / RECRUITMENT_NUMBER_OF_PEOPLE));

        CommonScoreResponse commonScore = new CommonScoreResponse();
        SpecialScoreResponse meisterScore = new SpecialScoreResponse();
        SpecialScoreResponse socialScore = new SpecialScoreResponse();

        for(BigDecimal scoreDecimal : applicationStatus.getCommonScores()) {
            commonScore.addScore(Math.round(scoreDecimal.doubleValue()));
        }
        for(BigDecimal scoreDecimal : applicationStatus.getMeisterScores()) {
            meisterScore.addScore(Math.round(scoreDecimal.doubleValue()));
        }
        for(BigDecimal scoreDecimal : applicationStatus.getSpecialScores()) {
            socialScore.addScore(Math.round(scoreDecimal.doubleValue()));
        }

        commonScore.updateCountAndRate(commonCount, Double.parseDouble(String.format("%.2f", commonCount/COMMON_ADMISSION_NUMBER_OF_RECRUITMENT)));
        meisterScore.updateCountAndRate(meisterCount, Double.parseDouble(String.format("%.2f",meisterCount/MEISTER_ADMISSION_NUMBER_OF_RECRUITMENT)));
        socialScore.updateCountAndRate(socialCount, Double.parseDouble(String.format("%.2f",socialCount/SOCIAL_ADMISSION_NUMBER_OF_RECRUITMENT)));

        return ReceiptStatusResponse.builder()
                .totalApplicantCount(totalApplicantCount)
                .totalSubmittedApplicantCount(totalSubmittedApplicantCount)
                .totalCompetitionRate(totalCompetitionRate)
                .commonScore(commonScore)
                .meisterScore(meisterScore)
                .socialScore(socialScore)
                .build();
    }

    @Override
    public void deleteAllTables() {
        LocalDateTime now = LocalDateTime.now();
        Schedule secondAnnouncement = scheduleRepository.findByYearAndType(String.valueOf(now.getYear()), Type.SECOND_ANNOUNCEMENT)
                .orElseThrow(ScheduleNotFoundException::new);
        if(now.isBefore(secondAnnouncement.getDate())) {
            throw new ApplicationPeriodNotOverException();
        }
        mainRepository.deleteAll();
    }

}
