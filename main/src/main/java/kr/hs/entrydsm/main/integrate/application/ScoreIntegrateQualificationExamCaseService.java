package kr.hs.entrydsm.main.integrate.application;

import kr.hs.entrydsm.application.entity.QualificationExamApplication;
import kr.hs.entrydsm.score.integrate.application.QualificationExamCase;
import kr.hs.entrydsm.score.integrate.application.QualificationExamCaseRepository;
import kr.hs.entrydsm.score.integrate.user.ScorerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ScoreIntegrateQualificationExamCaseService implements QualificationExamCaseRepository {

    private final ScorerRepository scorerRepository;

    @Override
    public Optional<QualificationExamCase> findByReceiptCode(long receiptCode) {
//        QualificationExamApplication qualificationExamApplication =
//                applicationExportRepository.getQualificationExamApplication(receiptCode);
//        QualificationExamCase qualificationExamCase = QualificationExamCase.builder()
//                .scorer(scorerRepository.findByReceiptCode(receiptCode))
//                .build();
//
//        return Optional.of(qualificationExamCase);
        return null;
    }

    @Override
    public void save(QualificationExamCase qualificationExamCase) {
//        applicationExportRepository.saveQualificationExamApplication(
//                new QualificationExamApplication(1, null)
//        );
    }
}
