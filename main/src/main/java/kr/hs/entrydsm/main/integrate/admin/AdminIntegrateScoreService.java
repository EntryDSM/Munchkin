package kr.hs.entrydsm.main.integrate.admin;

import kr.hs.entrydsm.admin.integrate.score.ScoreRepository;
import kr.hs.entrydsm.admin.usecase.dto.ApplicationStatus;
import kr.hs.entrydsm.score.integrate.admin.ScoreExportRepository;
import kr.hs.entrydsm.score.usecase.dto.ApplicationStatusResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AdminIntegrateScoreService implements ScoreRepository {

    private final ScoreExportRepository scoreExportRepository;

    @Override
    public ApplicationStatus getScore() {
        ApplicationStatusResponse statusResponse = scoreExportRepository.getApplicationStatus();

        return ApplicationStatus.builder()
                .commonScore(statusResponse.getCommonScore())
                .meisterScore(statusResponse.getMeisterScore())
                .specialScore(statusResponse.getSpecialScore())
                .build();
    }

}
