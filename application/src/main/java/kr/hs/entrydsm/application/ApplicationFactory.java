package kr.hs.entrydsm.application;

import kr.hs.entrydsm.application.entity.*;
import kr.hs.entrydsm.application.usecase.exception.ApplicationNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApplicationFactory {

    private final GraduationApplicationRepository graduationApplicationRepository;
    private final QualificationExamApplicationRepository qualificationExamApplicationRepository;

    public Application saveAndGetApplicationFrom(Applicant applicant) {
        Application result;
        if (applicant.isGraduation())
            result = saveGraduationApplicationIfNotExists(applicant.getReceiptCode());
        else
            result = saveQualificationExamApplicationIfNotExists(applicant.getReceiptCode());
        return result;
    }

    private GraduationApplication saveGraduationApplicationIfNotExists(long receiptCode) {
        GraduationApplication graduationApplication = createGraduationApplicationIfNotExists(receiptCode);
        graduationApplicationRepository.save(graduationApplication);
        return graduationApplication;
    }

    private QualificationExamApplication saveQualificationExamApplicationIfNotExists(long receiptCode) {
        QualificationExamApplication qualificationExamApplication = createQualificationExamApplicationIfNotExists(receiptCode);
        qualificationExamApplicationRepository.save(qualificationExamApplication);
        return qualificationExamApplication;
    }

    public GraduationApplication createGraduationApplicationIfNotExists(long receiptCode) {
        GraduationApplication result;
        if (graduationApplicationRepository.existsByReceiptCode(receiptCode))
            result = graduationApplicationRepository.findByReceiptCode(receiptCode)
                    .orElseThrow(ApplicationNotFoundException::new);
        else
            result = new GraduationApplication(receiptCode);
        return result;
    }

    public QualificationExamApplication createQualificationExamApplicationIfNotExists(long receiptCode) {
        QualificationExamApplication result;
        if (qualificationExamApplicationRepository.existsByReceiptCode(receiptCode))
            result = qualificationExamApplicationRepository.findByReceiptCode(receiptCode)
                    .orElseThrow(ApplicationNotFoundException::new);
        else
            result = new QualificationExamApplication(receiptCode);
        return result;
    }
}
