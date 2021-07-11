package kr.hs.entrydsm.main.integrate.score;

import kr.hs.entrydsm.score.integrate.user.Scorer;
import kr.hs.entrydsm.score.integrate.user.ScorerRepository;
import kr.hs.entrydsm.score.usecase.exception.ApplicationTypeUnmatchedException;
import kr.hs.entrydsm.score.usecase.exception.GradeNotFoundException;
import kr.hs.entrydsm.user.entity.user.User;
import kr.hs.entrydsm.user.entity.user.enumeration.ApplicationType;
import kr.hs.entrydsm.user.entity.user.enumeration.EducationalStatus;
import kr.hs.entrydsm.user.integrate.admin.UserExportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ScoreIntegrateUserService implements ScorerRepository {

    private final UserExportRepository userExportRepository;

    @Override
    public Scorer findByReceiptCode(long receiptCode) {
        User user = userExportRepository.findByReceiptCode(receiptCode);
        return Scorer.builder().receiptCode(user.getReceiptCode())
                .applicationType(getApplicationType(user.getApplicationType()))
                .educationalStatus(getEducationalStatus(user.getEducationalStatus()))
                .build();
    }

    private Scorer.ApplicationType getApplicationType(ApplicationType applicationType) {
        switch (applicationType) {
            case COMMON:
                return Scorer.ApplicationType.COMMON;
            case MEISTER:
                return Scorer.ApplicationType.MEISTER;
            case SOCIAL:
                return Scorer.ApplicationType.SOCIAL;
            default:
                throw new ApplicationTypeUnmatchedException();
        }
    }

    private Scorer.EducationalStatus getEducationalStatus(EducationalStatus educationalStatus) {
        switch (educationalStatus) {
            case PROSPECTIVE_GRADUATE:
                return Scorer.EducationalStatus.PROSPECTIVE_GRADUATE;
            case GRADUATE:
                return Scorer.EducationalStatus.GRADUATE;
            case QUALIFICATION_EXAM:
                return Scorer.EducationalStatus.QUALIFICATION_EXAM;
            default:
                throw new GradeNotFoundException();
        }
    }
}
