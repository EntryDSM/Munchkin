package kr.hs.entrydsm.main.integrate.score;

import kr.hs.entrydsm.score.integrate.application.GraduationCase;
import kr.hs.entrydsm.score.integrate.application.GraduationCaseRepository;

import java.util.Optional;

public class ScoreIntegrateGraduationCaseService implements GraduationCaseRepository {
    @Override
    public Optional<GraduationCase> findByReceiptCode(long receiptCode) {
        return Optional.empty();
    }

    @Override
    public void save(GraduationCase graduationCase) {

    }
}
