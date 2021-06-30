package kr.hs.entrydsm.application.integrate.score;

import kr.hs.entrydsm.application.entity.GraduationApplication;
import kr.hs.entrydsm.application.entity.GraduationApplicationRepository;
import kr.hs.entrydsm.application.entity.QualificationExamApplication;
import kr.hs.entrydsm.application.entity.QualificationExamApplicationRepository;
import kr.hs.entrydsm.application.integrate.user.ApplicantRepository;
import kr.hs.entrydsm.application.usecase.exception.ApplicationNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ApplicationExportManager implements ApplicationExportRepository{

    private final GraduationApplicationRepository graduationApplicationRepository;
    private final QualificationExamApplicationRepository qualificationExamApplicationRepository;

    @Override
    public GraduationApplication getGraduationApplication(long receiptCode) {
        return graduationApplicationRepository.findByReceiptCode(receiptCode)
                .orElseThrow(ApplicationNotFoundException::new);
    }

    @Override
    public QualificationExamApplication getQualificationExamApplication(long receiptCode) {
        return qualificationExamApplicationRepository.findByReceiptCode(receiptCode)
                .orElseThrow(ApplicationNotFoundException::new);
    }

    @Override
    public void saveGraduationApplication(GraduationApplication application) {
        graduationApplicationRepository.save(application);
    }

    @Override
    public void saveQualificationExamApplication(QualificationExamApplication application) {
        qualificationExamApplicationRepository.save(application);
    }
}
