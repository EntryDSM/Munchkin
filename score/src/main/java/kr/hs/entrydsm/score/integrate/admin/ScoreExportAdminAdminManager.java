package kr.hs.entrydsm.score.integrate.admin;

import kr.hs.entrydsm.score.entity.Score;
import kr.hs.entrydsm.score.entity.ScoreRepository;
import kr.hs.entrydsm.score.integrate.user.ScorerRepository;
import kr.hs.entrydsm.score.integrate.user.enumeration.ApplicationType;
import kr.hs.entrydsm.score.usecase.dto.ApplicantScore;
import kr.hs.entrydsm.score.usecase.dto.ApplicationStatusResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Repository
public class ScoreExportAdminAdminManager implements ScoreExportAdminRepository {
    private final ScoreRepository scoreRepository;
    private final ScorerRepository scorerRepository;

    @Override
    public ApplicationStatusResponse getApplicationStatus() {
        return ApplicationStatusResponse.builder()
                .commonScore(getScores(ApplicationType.COMMON))
                .meisterScore(getScores(ApplicationType.MEISTER))
                .specialScore(getScores(ApplicationType.SOCIAL))
                .build();
    }

    @Override
    public ApplicantScore getApplicantScore(Long receiptCode) {
        return null;
    }

    private List<BigDecimal> getScores(ApplicationType applicationType) {
        return scoreStream().filter(score -> getScorerType(score).equals(applicationType))
                .map(Score::getTotalScore).collect(Collectors.toList());
    }

    private Stream<Score> scoreStream() {
        return StreamSupport.stream(scoreRepository.findAll().spliterator(), false);
    }

    private ApplicationType getScorerType(Score score) {
        return scorerRepository.findByReceiptCode(score.getReceiptCode()).getApplicationType();
    }

}
