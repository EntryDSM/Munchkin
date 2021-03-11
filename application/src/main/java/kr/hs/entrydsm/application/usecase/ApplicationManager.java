package kr.hs.entrydsm.application.usecase;

import kr.hs.entrydsm.application.entity.School;
import kr.hs.entrydsm.application.entity.SchoolRepository;
import kr.hs.entrydsm.application.integrate.user.ApplicantExportRepository;
import kr.hs.entrydsm.application.integrate.user.UserDocsService;
import kr.hs.entrydsm.application.usecase.dto.Application;
import kr.hs.entrydsm.application.usecase.dto.Information;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ApplicationManager implements ApplicationProcessing {

    private final UserDocsService userDocsService;
    private final ApplicantExportRepository applicantRepository;
    private final SchoolRepository schoolRepository;

    @Override
    public void writeSelfIntroduce(Long receiptCode, String content) {
        userDocsService.writeSelfIntroduce(receiptCode, content);
    }

    @Override
    public void writeStudyPlan(Long receiptCode, String content) {
        userDocsService.writeStudyPlan(receiptCode, content);
    }

    @Override
    public String getSelfIntroduce(Long receiptCode) {
        return userDocsService.getSelfIntroduce(receiptCode);
    }

    @Override
    public String getStudyPlan(Long receiptCode) {
        return userDocsService.getStudyPlan(receiptCode);
    }

    @Override
    public Page<School> getSchoolsByInformation(String information, Pageable pageable) {
        return schoolRepository.findByInformationContains(information, pageable);
    }

    @Override
    public void writeApplicationType(Long receiptCode, Application applicationRequest) {
        applicantRepository.writeApplicationType(receiptCode, applicationRequest);
    }

    @Override
    public void writeInformation(Long receiptCode, Information information) {
        applicantRepository.writeInformation(receiptCode, information);
    }

    @Override
    public Application getApplicationType(Long receiptCode) {
        return applicantRepository.getApplicationType(receiptCode);
    }

    @Override
    public Information getInformation(Long receiptCode) {
        return applicantRepository.getInformation(receiptCode);
    }
}
