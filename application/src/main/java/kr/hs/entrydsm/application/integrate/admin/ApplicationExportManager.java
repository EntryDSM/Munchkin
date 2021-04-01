package kr.hs.entrydsm.application.integrate.admin;

import kr.hs.entrydsm.application.entity.*;
import kr.hs.entrydsm.application.integrate.score.ScoreCalculator;
import kr.hs.entrydsm.application.usecase.dto.Applicant;
import kr.hs.entrydsm.application.usecase.exception.ApplicationNotFoundException;
import kr.hs.entrydsm.common.model.ReportCard;
import kr.hs.entrydsm.common.model.Scores;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ApplicationExportManager implements ApplicationExportRepository {

    private final ApplicationRepository applicationRepository;
    private final GraduationApplicationRepository graduationApplicationRepository;
    private final ScoreCalculator scoreCalculator;

    @Override
    public List<GraduationApplication> getApplicants() {
        List<GraduationApplication> result = new ArrayList<>();
        graduationApplicationRepository.findAll().forEach(result::add);
        result.sort(Comparator.comparing(Application::getReceiptCode));
        return result;
    }

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
