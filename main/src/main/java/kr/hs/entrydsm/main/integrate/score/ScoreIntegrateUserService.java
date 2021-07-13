package kr.hs.entrydsm.main.integrate.score;

import kr.hs.entrydsm.score.integrate.user.Scorer;
import kr.hs.entrydsm.score.integrate.user.ScorerRepository;
import kr.hs.entrydsm.score.usecase.exception.ApplicationTypeUnmatchedException;
import kr.hs.entrydsm.score.usecase.exception.GradeNotFoundException;
import kr.hs.entrydsm.user.entity.user.User;
import kr.hs.entrydsm.user.entity.user.enumeration.ApplicationType;
import kr.hs.entrydsm.user.entity.user.enumeration.EducationalStatus;
import kr.hs.entrydsm.user.integrate.admin.UserExportRepository;
import kr.hs.entrydsm.user.integrate.score.UserIntegrateScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class ScoreIntegrateUserService implements ScorerRepository {

    private final UserIntegrateScoreRepository userIntegrateScoreRepository;

    @Override
    public Scorer findByReceiptCode(long receiptCode) {
        User user = userIntegrateScoreRepository.findByReceiptCode(receiptCode);
        return convertUserToScorer(user);
    }

    @Override
    public List<Scorer> findFinalSubmittedByRegionAndType(boolean isDaejeon, Scorer.ApplicationType applicationType) {
        return userIntegrateScoreRepository
                .findFinalSubmittedByRegionAndType(isDaejeon, getApplicationType(applicationType))
                .stream().map(this::convertUserToScorer)
                .collect(Collectors.toList());
    }

    private Scorer convertUserToScorer(User user) {
        return Scorer.builder()
                .receiptCode(user.getReceiptCode())
                .isDaejeon(user.getIsDaejeon())
                .applicationType(getApplicationType(user.getApplicationType()))
                .educationalStatus(getEducationalStatus(user.getEducationalStatus()))
                .build();
    }

    private ApplicationType getApplicationType(Scorer.ApplicationType applicationType) {
        return ApplicationType.valueOf(applicationType.name());
    }

    private Scorer.ApplicationType getApplicationType(ApplicationType applicationType) {
        return Scorer.ApplicationType.valueOf(applicationType.name());
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
