package kr.hs.entrydsm.admin.usecase.admin;

import kr.hs.entrydsm.admin.entity.schedule.Schedule;
import kr.hs.entrydsm.admin.entity.schedule.ScheduleRepository;
import kr.hs.entrydsm.admin.entity.schedule.Type;
import kr.hs.entrydsm.admin.integrate.main.MainRepository;
import kr.hs.entrydsm.admin.integrate.score.ScoreRepository;
import kr.hs.entrydsm.admin.usecase.dto.applicant.ApplicationStatus;
import kr.hs.entrydsm.admin.usecase.dto.score.CommonScoreResponse;
import kr.hs.entrydsm.admin.usecase.dto.score.SpecialScoreResponse;
import kr.hs.entrydsm.admin.usecase.dto.applicant.ReceiptStatusResponse;
import kr.hs.entrydsm.admin.usecase.exception.ApplicationPeriodNotOverException;
import kr.hs.entrydsm.admin.usecase.exception.ScheduleNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class AdminServiceManager implements AdminService {

    private final ScoreRepository scoreRepository;
    private final MainRepository mainRepository;
    private final ScheduleRepository scheduleRepository;

    private static final double RECRUITMENT_NUMBER_OF_PEOPLE = 80.0;
    private static final double COMMON_ADMISSION_NUMBER_OF_RECRUITMENT = 40.0;
    private static final double MEISTER_ADMISSION_NUMBER_OF_RECRUITMENT = 36.0;
    private static final double SOCIAL_ADMISSION_NUMBER_OF_RECRUITMENT = 4.0;

    @Override
    public ReceiptStatusResponse getApplyStaticsStatistics() {
        ApplicationStatus applicationStatus = scoreRepository.getScore();
        int commonCount = applicationStatus.getCommonScores().size();
        int meisterCount = applicationStatus.getMeisterScores().size();
        int socialCount = applicationStatus.getSpecialScores().size();

        int totalApplicantCount = commonCount + meisterCount + socialCount;
        double totalCompetitionRate = totalApplicantCount / RECRUITMENT_NUMBER_OF_PEOPLE;

        CommonScoreResponse commonScore = new CommonScoreResponse();
        SpecialScoreResponse meisterScore = new SpecialScoreResponse();
        SpecialScoreResponse socialScore = new SpecialScoreResponse();

        for(BigDecimal scoreDecimal : applicationStatus.getCommonScores()) {
            double score = Double.parseDouble(String.valueOf(scoreDecimal));
            commonScore.addScore(Math.round(score));
        }

        for(BigDecimal scoreDecimal : applicationStatus.getMeisterScores()) {
            double score = Double.parseDouble(String.valueOf(scoreDecimal));
            meisterScore.addScore(Math.round(score));
        }

        for(BigDecimal scoreDecimal : applicationStatus.getSpecialScores()) {
            double score = Double.parseDouble(String.valueOf(scoreDecimal));
            meisterScore.addScore(Math.round(score));
        }

        return ReceiptStatusResponse.builder()
                .totalApplicantCount(totalApplicantCount)
                .totalCompetitionRate(totalCompetitionRate)
                .commonScore(commonScore)
                .meisterScore(meisterScore)
                .socialScore(socialScore)
                .commonCount(commonCount)
                .commonCompetitionRate(commonCount/COMMON_ADMISSION_NUMBER_OF_RECRUITMENT)
                .meisterCount(meisterCount)
                .meisterCompetitionRate(meisterCount/MEISTER_ADMISSION_NUMBER_OF_RECRUITMENT)
                .socialCount(socialCount)
                .socialCompetitionRate(socialCount/SOCIAL_ADMISSION_NUMBER_OF_RECRUITMENT)
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
