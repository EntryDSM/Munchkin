package kr.hs.entrydsm.admin.usecase;

import kr.hs.entrydsm.admin.integrate.score.ScoreRepository;
import kr.hs.entrydsm.admin.usecase.dto.ApplicationStatus;
import kr.hs.entrydsm.admin.usecase.dto.CommonScore;
import kr.hs.entrydsm.admin.usecase.dto.response.ReceiptStatusResponse;
import kr.hs.entrydsm.admin.usecase.dto.SpecialScore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class AdminServiceManager implements AdminService {

    private final ScoreRepository scoreRepository;

    private static final Integer RECRUITMENT_NUMBER_OF_PEOPLE = 80;
    private static final Integer COMMON_ADMISSION_NUMBER_OF_RECRUITMENT = 40;
    private static final Integer MEISTER_ADMISSION_NUMBER_OF_RECRUITMENT = 36;
    private static final Integer SOCIAL_ADMISSION_NUMBER_OF_RECRUITMENT = 4;

    @Override
    public ReceiptStatusResponse getStatics() { // 접수 현황 통계
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
        for(BigDecimal scoreDecimal : applicationStatus.getCommonScore()) {
            double score = Double.parseDouble(String.valueOf(scoreDecimal));
            commonScore.addScore(Math.round(score));
        }

        //마이스터전형
        for(BigDecimal score : applicationStatus.getMeisterScore()) {
            double s = Double.parseDouble(String.valueOf(score));
            s = Math.round(s);

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
            s = Math.round(s);

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
