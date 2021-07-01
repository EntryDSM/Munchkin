package kr.hs.entrydsm.application.integrate.admin;

import kr.hs.entrydsm.application.ApplicationFactory;
import kr.hs.entrydsm.application.entity.*;
import kr.hs.entrydsm.application.integrate.score.ScoreCalculator;
import kr.hs.entrydsm.application.integrate.user.ApplicantRepository;
import kr.hs.entrydsm.application.usecase.dto.Applicant;
import kr.hs.entrydsm.application.usecase.dto.CalculatedScore;
import kr.hs.entrydsm.application.usecase.dto.ReportCard;
import kr.hs.entrydsm.application.usecase.exception.ApplicationNotFoundException;
import kr.hs.entrydsm.application.usecase.exception.EducationalStatusNullException;
import kr.hs.entrydsm.application.usecase.exception.NullGradeExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ApplicationExportAdminManager implements ApplicationExportAdminRepository {

    private final ApplicationFactory applicationFactory;
    private final ApplicantRepository applicantRepository;
    private final GraduationApplicationRepository graduationApplicationRepository;
    private final ScoreCalculator scoreCalculator;

    @Override
    public List<Applicant> getApplicants() {
        List<GraduationApplication> graduationApplications = new ArrayList<>();
        graduationApplicationRepository.findAll().forEach(graduationApplications::add);
        graduationApplications.sort(Comparator.comparing(Application::getReceiptCode));
        List<CalculatedScore> calculatedScores = new ArrayList<>();
        scoreCalculator.getAll().forEach(calculatedScores::add);
        calculatedScores.sort(Comparator.comparing(CalculatedScore::getReceiptCode));

        List<Applicant> applicants = new ArrayList<>();
        for(int i=0,size=graduationApplications.size(); i<size; i++){
            Applicant applicant = new Applicant();
            GraduationApplication graduationApplication = graduationApplications.get(i);
            CalculatedScore calculatedScore = calculatedScores.get(i);
            applicant.setGraduationApplication(graduationApplication);
            applicant.setScore(calculatedScore);
            applicants.add(applicant);
        }
        return applicants;
    }

    @Override
    public ReportCard getReportCard(long receiptCode) {
        if (applicantRepository.findByReceiptCode(receiptCode).getEducationalStatus() == null) {
            throw new EducationalStatusNullException();
        }

        Application application = getApplication(receiptCode);

        if (scoreCalculator.isAnyGradeNull(receiptCode)) {
            throw new NullGradeExistException();
        }

        CalculatedScore calculatedScore = scoreCalculator.calculateScore(application);

        return ReportCard.from(application, calculatedScore);
    }

    private Application getApplication(long receiptCode) {
        Application result;
        if (applicantRepository.findByReceiptCode(receiptCode).isGraduation()) {
            result = applicationFactory.saveGraduationApplicationIfNotExists(receiptCode);
        } else {
            result = applicationFactory.saveQualificationExamApplicationIfNotExists(receiptCode);
        }
        return result;
    }

}
