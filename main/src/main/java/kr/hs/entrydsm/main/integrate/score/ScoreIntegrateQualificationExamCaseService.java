package kr.hs.entrydsm.main.integrate.score;

import kr.hs.entrydsm.score.integrate.application.QualificationExamCase;
import kr.hs.entrydsm.score.integrate.application.QualificationExamCaseRepository;

import java.util.Optional;

public class ScoreIntegrateQualificationExamCaseService implements QualificationExamCaseRepository {
    @Override
    public Optional<QualificationExamCase> findByReceiptCode(long receiptCode) {
        return Optional.empty();
    }

    @Override
    public void save(QualificationExamCase qualificationExamCase) {

    }
}
