package kr.hs.entrydsm.application.integrate.admin;

import kr.hs.entrydsm.application.entity.*;
import kr.hs.entrydsm.application.integrate.score.ScoreCalculator;
import kr.hs.entrydsm.application.usecase.dto.Applicant;
import kr.hs.entrydsm.application.usecase.dto.Score;
import kr.hs.entrydsm.application.usecase.dto.SubjectScore;
import kr.hs.entrydsm.application.usecase.exception.ApplicationNotFoundException;
import kr.hs.entrydsm.application.usecase.dto.ReportCard;
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
    public List<Applicant> getApplicants() {
        List<GraduationApplication> graduationApplications = new ArrayList<>();
        graduationApplicationRepository.findAll().forEach(graduationApplications::add);
        graduationApplications.sort(Comparator.comparing(Application::getReceiptCode));
        List<Score> scores = new ArrayList<>();
        scoreCalculator.getAll().forEach(scores::add);
        scores.sort(Comparator.comparing(Score::getReceiptCode));

        List<Applicant> applicants = new ArrayList<>();
        for(int i=0,size=graduationApplications.size(); i<size; i++){
            Applicant applicant = new Applicant();
            GraduationApplication graduationApplication = graduationApplications.get(i);
            Score score = scores.get(i);
            applicant.setGraduationApplication(graduationApplication);
            applicant.setScore(score);
            applicants.add(applicant);
        }
        return applicants;
    }

    @Override
    public ReportCard getReportCard(long receiptCode) {
        Application application = applicationRepository.findByReceiptCode(receiptCode)
                .orElseThrow(ApplicationNotFoundException::new);

        Score score = scoreCalculator.getScore(application);

        return createReportCard(application, score);
    }

    private ReportCard createReportCard(Application application, Score score) {
        if (application.isGraduation()) {
            GraduationApplication graduationApplication = (GraduationApplication) application;
            return ReportCard.graduationBuilder()
                    .receiptCode(application.getReceiptCode())
                    .score(score)
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
                    .score(score)
                    .averageScore(qualificationExamApplication.getAverageScore())
                    .build();
        }
    }
}
