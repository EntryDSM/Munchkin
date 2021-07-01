package kr.hs.entrydsm.application.usecase.pdf;

import kr.hs.entrydsm.application.ApplicationFactory;
import kr.hs.entrydsm.application.entity.Applicant;
import kr.hs.entrydsm.application.entity.Application;
import kr.hs.entrydsm.application.entity.ApplicationRepository;
import kr.hs.entrydsm.application.integrate.score.ScoreCalculator;
import kr.hs.entrydsm.application.integrate.user.ApplicantRepository;
import kr.hs.entrydsm.application.integrate.user.ApplicantStatusService;
import kr.hs.entrydsm.application.usecase.dto.CalculatedScore;
import kr.hs.entrydsm.application.usecase.exception.ApplicationNotFoundException;
import kr.hs.entrydsm.application.usecase.exception.EducationalStatusNullException;
import kr.hs.entrydsm.application.usecase.exception.FinalSubmitRequiredException;
import kr.hs.entrydsm.common.context.auth.manager.AuthenticationManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationPdfManager implements ApplicationPdfService {

    private final ApplicationFactory applicationFactory;
    private final ApplicationPdfGenerator applicationPdfGenerator;
    private final ApplicationRepository applicationRepository;
    private final ApplicantStatusService applicantStatusService;
    private final ScoreCalculator scoreCalculator;
    private final ApplicantRepository applicantRepository;
    private final AuthenticationManager authenticationManager;

    @Override
    public byte[] getPreviewApplicationPdf() {
        long receiptCode = authenticationManager.getUserReceiptCode();
        Applicant applicant = applicantRepository.findByReceiptCode(receiptCode);

        if (applicant.getEducationalStatus() == null) {
            throw new EducationalStatusNullException();
        }

        Application application = applicationFactory.saveAndGetApplicationFrom(applicant);
        CalculatedScore calculatedScore = scoreCalculator.calculateScore(application);
        return applicationPdfGenerator.generate(applicant, calculatedScore);
    }

    @Override
    public byte[] getFinalApplicationPdf() {
        long receiptCode = authenticationManager.getUserReceiptCode();
        if (!applicantStatusService.isFinalSubmit(receiptCode)) {
            throw new FinalSubmitRequiredException();
        }

        Applicant applicant = applicantRepository.findByReceiptCode(receiptCode);
        Application application = applicationRepository.findByReceiptCode(receiptCode)
                .orElseThrow(ApplicationNotFoundException::new);
        CalculatedScore calculatedScore = scoreCalculator.calculateScore(application);
        return applicationPdfGenerator.generate(applicant, calculatedScore);
    }

}
