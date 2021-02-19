package kr.hs.entrydsm.application.integrate.admin;

import kr.hs.entrydsm.application.domain.entity.Application;
import kr.hs.entrydsm.application.domain.entity.GraduationApplication;
import kr.hs.entrydsm.application.domain.repository.ApplicationRepository;
import kr.hs.entrydsm.application.domain.repository.SchoolRepository;
import kr.hs.entrydsm.common.context.GraduationReportCard;
import kr.hs.entrydsm.common.context.QualificationReportCard;
import kr.hs.entrydsm.common.context.ReportCard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ApplicationExportManager implements ApplicationExportRepository {

    private final ApplicationRepository applicationRepository;
    private final SchoolRepository schoolRepository;

    @Override
    public ReportCard getReportCardByReceiptCode(long receiptCode) {
        Application application = applicationRepository.findByReceiptCode(receiptCode)
                .orElseThrow(); // TODO add exception

        if (application.isGraduation()) { // TODO score service required, school repository required
            return GraduationReportCard.builder()
                    .receiptCode(application.getReceiptCode())
                    .attendanceScore()
                    .gradeScore()
                    .attendanceScore()
                    .volunteerScore()
                    .schoolName()
                    .isGraduated(((GraduationApplication) application).getIsGraduated())
                    .build();
        } else {
            return QualificationReportCard.builder()
                    .receiptCode(application.getReceiptCode())
                    .attendanceScore()
                    .gradeScore()
                    .averageScore()
                    .volunteerScore()
                    .totalScore()
                    .build();
        }
    }
}
