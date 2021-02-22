package kr.hs.entrydsm.application.integrate.admin;

import kr.hs.entrydsm.application.domain.entity.Application;
import kr.hs.entrydsm.application.domain.entity.GraduationApplication;
import kr.hs.entrydsm.application.domain.entity.QualificationExamApplication;
import kr.hs.entrydsm.application.domain.repository.ApplicationRepository;
import kr.hs.entrydsm.application.integrate.score.ScoreCalculator;
import kr.hs.entrydsm.common.model.GraduationReportCard;
import kr.hs.entrydsm.common.model.QualificationReportCard;
import kr.hs.entrydsm.common.model.ReportCard;
import kr.hs.entrydsm.common.model.Scores;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ApplicationExportManager implements ApplicationExportRepository {

    private final ApplicationRepository applicationRepository;
    private final ScoreCalculator scoreCalculator;

    @Override
    public ReportCard getReportCard(long receiptCode) {
        Application application = applicationRepository.findByReceiptCode(receiptCode)
                .orElseThrow(); // TODO add exception

        Scores scores = scoreCalculator.getScores(application.getReceiptCode());

        return createReportCard(application, scores);
    }

    private ReportCard createReportCard(Application application, Scores scores) {
        if (application.isGraduation()) {
            GraduationApplication graduationApplication = (GraduationApplication) application;
            return GraduationReportCard.builder()
                    .receiptCode(application.getReceiptCode())
                    .scores(scores)
                    .schoolName(graduationApplication.getSchoolName())
                    .isGraduated(graduationApplication.getIsGraduated())
                    .build();
        } else {
            QualificationExamApplication qualificationExamApplication = (QualificationExamApplication) application;
            return QualificationReportCard.builder()
                    .receiptCode(application.getReceiptCode())
                    .scores(scores)
                    .averageScore(qualificationExamApplication.getAverageScore())
                    .build();
        }
    }
}
