package kr.hs.entrydsm.application.integrate.admin;

import java.net.MalformedURLException;

import kr.hs.entrydsm.application.ApplicationFactory;
import kr.hs.entrydsm.application.entity.Applicant;
import kr.hs.entrydsm.application.entity.Application;
import kr.hs.entrydsm.application.entity.GraduationApplication;
import kr.hs.entrydsm.application.entity.GraduationApplicationRepository;
import kr.hs.entrydsm.application.integrate.score.ScoreCalculator;
import kr.hs.entrydsm.application.integrate.user.ApplicantRepository;
import kr.hs.entrydsm.application.integrate.user.ApplicantStatusService;
import kr.hs.entrydsm.application.usecase.dto.CalculatedScore;
import kr.hs.entrydsm.application.usecase.dto.MiddleSchoolInfo;
import kr.hs.entrydsm.application.usecase.dto.ReportCard;
import kr.hs.entrydsm.application.usecase.image.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ApplicationExportAdminManager implements ApplicationExportAdminRepository {

    private final ApplicationFactory applicationFactory;
    private final ApplicantRepository applicantRepository;
    private final ApplicantStatusService applicantStatusService;
    private final GraduationApplicationRepository graduationApplicationRepository;
    private final ScoreCalculator scoreCalculator;
    private final ImageService imageService;

    @Override
    public ReportCard getReportCard(long receiptCode) {
        Applicant applicant = applicantRepository.findByReceiptCode(receiptCode);
        Application application = applicationFactory.saveAndGetApplicationFrom(applicant);
        CalculatedScore calculatedScore = getCalculatedScore(application);
        return ReportCard.from(application, calculatedScore);
    }

    private CalculatedScore getCalculatedScore(Application application) {
        if (applicantStatusService.isFinalSubmit(application.getReceiptCode())) {
            return scoreCalculator.calculateScore(application);
        }
        return CalculatedScore.emptyScore(application.getReceiptCode());
    }

    @Override
    public MiddleSchoolInfo getMiddleSchoolInfo(long receiptCode) {
        GraduationApplication application =
                graduationApplicationRepository.findByReceiptCode(receiptCode).orElse(null);
        if(application == null)
        	return null;
        return MiddleSchoolInfo.builder()
                .middleSchool(application.getSchoolName())
                .middleSchoolStudentNumber(application.getStudentNumber())
                .yearOfGraduation(application.getGraduatedAt().getYear())
                .build();
    }

    @Override
    public String getFileUrl(String photoFileName) throws MalformedURLException {
        return imageService.generateObjectUrl(photoFileName);
    }

}
