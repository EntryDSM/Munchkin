package kr.hs.entrydsm.application.usecase.pdf;

import kr.hs.entrydsm.application.entity.Applicant;
import kr.hs.entrydsm.application.entity.Application;
import kr.hs.entrydsm.application.entity.ApplicationRepository;
import kr.hs.entrydsm.application.integrate.score.ScoreCalculator;
import kr.hs.entrydsm.application.integrate.user.ApplicantRepository;
import kr.hs.entrydsm.application.integrate.user.ApplicantStatusService;
import kr.hs.entrydsm.application.usecase.dto.Score;
import kr.hs.entrydsm.application.usecase.exception.ApplicationNotFoundException;
import kr.hs.entrydsm.application.usecase.exception.FinalSubmitRequiredException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationPdfManager implements ApplicationPdfService {

    private final ApplicationPdfGenerator applicationPdfGenerator;
    private final ApplicationRepository applicationRepository;
    private final ApplicantStatusService applicantStatusService;
    private final ScoreCalculator scoreCalculator;
    private final ApplicantRepository applicantRepository;

    @Override
    public byte[] getPreviewApplicationPdf(long receiptCode) {
        Applicant applicant = applicantRepository.findByReceiptCode(receiptCode);
        Application application = applicationRepository.findByReceiptCode(receiptCode)
                .orElseThrow(ApplicationNotFoundException::new);
        Score score = scoreCalculator.getScore(application);
        return applicationPdfGenerator.generate(applicant, score);
    }

    @Override
    public byte[] getFinalApplicationPdf(long receiptCode) {
        if (!applicantStatusService.isFinalSubmit(receiptCode)) {
            throw new FinalSubmitRequiredException();
        }

        Applicant applicant = applicantRepository.findByReceiptCode(receiptCode);
        Application application = applicationRepository.findByReceiptCode(receiptCode)
                .orElseThrow(ApplicationNotFoundException::new);
        Score score = scoreCalculator.getScore(application);
        return applicationPdfGenerator.generate(applicant, score);
    }
}
