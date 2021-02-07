package kr.hs.entrydsm.application.usecase;

import kr.hs.entrydsm.application.domain.entity.School;
import kr.hs.entrydsm.application.domain.repository.SchoolRepository;
import kr.hs.entrydsm.application.integrate.user.UserDocsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ApplicationManager implements ApplicationProcessing {

    private final UserDocsService userDocsService;
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
}
