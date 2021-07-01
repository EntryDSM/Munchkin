package kr.hs.entrydsm.application.integrate.admin;

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
    private final ApplicantRepository applicantRepository;
    private final GraduationApplicationRepository graduationApplicationRepository;
    private final QualificationExamApplicationRepository qualificationExamApplicationRepository;
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
        Application application = null;
        
        if (applicantRepository.findByReceiptCode(receiptCode).getEducationalStatus() == null) {
            throw new EducationalStatusNullException();
        }

        switch (applicantRepository.findByReceiptCode(receiptCode).getEducationalStatus()) {
            case EducationalStatus.GRADUATE:
            case EducationalStatus.PROSPECTIVE_GRADUATE:
                application = getGraduationApplication(receiptCode);
                break;
            case EducationalStatus.QUALIFICATION_EXAM:
                application = getQualificationExamApplication(receiptCode);
        }

        if (scoreCalculator.isAnyGradeNull(receiptCode)) {
            throw new NullGradeExistException();
        }

        CalculatedScore calculatedScore = scoreCalculator.getScore(application);

        return ReportCard.from(application, calculatedScore);
    }

    private GraduationApplication getGraduationApplication(long receiptCode) {
        GraduationApplication graduationApplication;
        if(graduationApplicationRepository.findByReceiptCode(receiptCode).isPresent()){
            return  graduationApplicationRepository.findByReceiptCode(receiptCode)
                    .orElseThrow(ApplicationNotFoundException::new);
        }else {
            graduationApplication = new GraduationApplication();
            graduationApplication.setReceiptCode(receiptCode);
            graduationApplicationRepository.save(graduationApplication);
            return graduationApplication;
        }
    }

    private QualificationExamApplication getQualificationExamApplication(long receiptCode) {
        QualificationExamApplication qualificationExamApplication;
        if(qualificationExamApplicationRepository.findByReceiptCode(receiptCode).isPresent()){
            return qualificationExamApplicationRepository.findByReceiptCode(receiptCode)
                    .orElseThrow(ApplicationNotFoundException::new);
        }else {
            qualificationExamApplication = new QualificationExamApplication();
            qualificationExamApplication.setReceiptCode(receiptCode);
            qualificationExamApplicationRepository.save(qualificationExamApplication);
            return qualificationExamApplication;
        }
    }
}
