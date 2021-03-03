package kr.hs.entrydsm.application.integrate.admin;

import kr.hs.entrydsm.application.domain.entity.Application;
import kr.hs.entrydsm.application.domain.entity.GraduationApplication;
import kr.hs.entrydsm.application.domain.entity.QualificationExamApplication;
import kr.hs.entrydsm.application.domain.repository.ApplicationRepository;
import kr.hs.entrydsm.application.integrate.score.ScoreCalculator;
import kr.hs.entrydsm.application.usecase.exception.ApplicationNotFoundException;
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
                .orElseThrow(ApplicationNotFoundException::new);

        Scores scores = scoreCalculator.getScores(application.getReceiptCode());

        return createReportCard(application, scores);
    }

    private ReportCard createReportCard(Application application, Scores scores) {
        if (application.isGraduation()) {
            GraduationApplication graduationApplication = (GraduationApplication) application;
            return ReportCard.graduationBuilder()
                    .receiptCode(application.getReceiptCode())
                    .scores(scores)
                    .isGraduated(graduationApplication.getIsGraduated())
                    .schoolTel(graduationApplication.getSchoolTel())
                    .schoolName(graduationApplication.getSchoolName())
                    .volunteerTime(graduationApplication.getVolunteerTime())
                    .latenessCount(graduationApplication.getLatenessCount())
                    .earlyLeaveCount(graduationApplication.getEarlyLeaveCount())
                    .dayAbsenceCount(graduationApplication.getDayAbsenceCount())
                    .lectureAbsenceCount(graduationApplication.getLectureAbsenceCount())
                    .build();
        } else {
            QualificationExamApplication qualificationExamApplication = (QualificationExamApplication) application;
            return ReportCard.qualificationBuilder()
                    .receiptCode(application.getReceiptCode())
                    .scores(scores)
                    .averageScore(qualificationExamApplication.getAverageScore())
                    .build();
        }
    }
}
