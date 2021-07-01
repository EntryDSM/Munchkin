package kr.hs.entrydsm.application.integrate.admin;

import kr.hs.entrydsm.application.ApplicationFactory;
import kr.hs.entrydsm.application.entity.*;
import kr.hs.entrydsm.application.integrate.score.ScoreCalculator;
import kr.hs.entrydsm.application.integrate.user.ApplicantRepository;
import kr.hs.entrydsm.application.usecase.dto.CalculatedScore;
import kr.hs.entrydsm.application.usecase.dto.MiddleSchoolInfo;
import kr.hs.entrydsm.application.usecase.dto.ReportCard;
import kr.hs.entrydsm.application.usecase.exception.ApplicationNotFoundException;
import kr.hs.entrydsm.application.usecase.exception.EducationalStatusNullException;
import kr.hs.entrydsm.application.usecase.exception.NullGradeExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ApplicationExportAdminManager implements ApplicationExportAdminRepository {

    private final ApplicationFactory applicationFactory;
    private final ApplicantRepository applicantRepository;
    private final GraduationApplicationRepository graduationApplicationRepository;
    private final ScoreCalculator scoreCalculator;

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

    @Override
    public MiddleSchoolInfo getMiddleSchoolInfo(long receiptCode) {
        GraduationApplication application =
                graduationApplicationRepository.findByReceiptCode(receiptCode).orElseThrow();
        return MiddleSchoolInfo.builder()
                .middleSchool(application.getSchoolName())
                .middleSchoolStudentNumber(application.getSchoolName())
                .yearOfGraduation(application.getGraduatedAt().getYear())
                .build();
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
