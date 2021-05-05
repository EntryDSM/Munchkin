package kr.hs.entrydsm.main.integrate.score;

import kr.hs.entrydsm.score.integrate.user.Scorer;
import kr.hs.entrydsm.score.integrate.user.ScorerRepository;
import kr.hs.entrydsm.score.integrate.user.enumeration.ApplicationType;
import kr.hs.entrydsm.score.integrate.user.enumeration.EducationalStatus;
import kr.hs.entrydsm.user.entity.User;
import kr.hs.entrydsm.user.integrate.admin.UserExportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ScoreIntegrateUserService implements ScorerRepository {

    private final UserExportRepository userExportRepository;

    @Override
    public Scorer findByReceiptCode(long receiptCode) {
        User user = userExportRepository.findByReceiptCode(Long.valueOf(receiptCode).intValue());
        return Scorer.initializer(user.getReceiptCode(), ApplicationType.SOCIAL, EducationalStatus.PROSPECTIVE_GRADUATE);
    }
}
