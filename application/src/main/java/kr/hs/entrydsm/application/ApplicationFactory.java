package kr.hs.entrydsm.application;

import kr.hs.entrydsm.application.entity.GraduationApplication;
import kr.hs.entrydsm.application.entity.GraduationApplicationRepository;
import kr.hs.entrydsm.application.entity.QualificationExamApplication;
import kr.hs.entrydsm.application.entity.QualificationExamApplicationRepository;
import kr.hs.entrydsm.application.usecase.exception.ApplicationNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApplicationFactory {

    private final GraduationApplicationRepository graduationApplicationRepository;
    private final QualificationExamApplicationRepository qualificationExamApplicationRepository;

    public GraduationApplication saveGraduationApplicationIfNotExists(long receiptCode) {
        GraduationApplication graduationApplication = createGraduationApplicationIfNotExists(receiptCode);
        graduationApplicationRepository.save(graduationApplication);
        return graduationApplication;
    }

    public QualificationExamApplication saveQualificationExamApplicationIfNotExists(long receiptCode) {
        QualificationExamApplication qualificationExamApplication = createQualificationExamApplicationIfNotExists(receiptCode);
        qualificationExamApplicationRepository.save(qualificationExamApplication);
        return qualificationExamApplication;
    }

    public GraduationApplication createGraduationApplicationIfNotExists(long receiptCode) {
        if (graduationApplicationRepository.existsByReceiptCode(receiptCode)) {
            return graduationApplicationRepository.findByReceiptCode(receiptCode)
                    .orElseThrow(ApplicationNotFoundException::new);
        } else {
            return new GraduationApplication(receiptCode);
        }
    }

    public QualificationExamApplication createQualificationExamApplicationIfNotExists(long receiptCode) {
        if (qualificationExamApplicationRepository.existsByReceiptCode(receiptCode)) {
            return qualificationExamApplicationRepository.findByReceiptCode(receiptCode)
                    .orElseThrow(ApplicationNotFoundException::new);
        } else {
            return new QualificationExamApplication(receiptCode);
        }
    }
}
