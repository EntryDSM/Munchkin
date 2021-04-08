package kr.hs.entrydsm.score.integrate.admin;

import kr.hs.entrydsm.score.usecase.dto.ApplicationStatusResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ScoreExportManager implements ScoreExportRepository{

    @Override
    public ApplicationStatusResponse getApplicationStatus() {
        return null;
    }
    
}
