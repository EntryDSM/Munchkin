package kr.hs.entrydsm.application.usecase;

import kr.hs.entrydsm.application.integrate.UserDocsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ApplicationManager implements ApplicationProcessing {

    private final UserDocsService userDocsService;

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
}
