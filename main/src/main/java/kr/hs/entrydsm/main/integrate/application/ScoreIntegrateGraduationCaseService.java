package kr.hs.entrydsm.main.integrate.application;

import kr.hs.entrydsm.application.entity.GraduationApplication;
import kr.hs.entrydsm.score.integrate.application.GraduationCase;
import kr.hs.entrydsm.score.integrate.application.GraduationCaseRepository;
import kr.hs.entrydsm.score.integrate.user.ScorerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ScoreIntegrateGraduationCaseService implements GraduationCaseRepository {

    private final ScorerRepository scorerRepository;

    @Override
    public Optional<GraduationCase> findByReceiptCode(long receiptCode) {
//        GraduationApplication application = applicationExportRepository.getGraduationApplication(receiptCode);
//        GraduationCase graduationCase = GraduationCase.builder()
//                .scorer(scorerRepository.findByReceiptCode(receiptCode))
//                .build();
//
//        return Optional.of(graduationCase);
        return null;
    }

    @Override
    public void save(GraduationCase graduationCase) {
//        applicationExportRepository.saveGraduationApplication(
//                GraduationApplication.builder()
//                .build()
//        );
    }
}
