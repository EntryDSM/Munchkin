package kr.hs.entrydsm.application.usecase;

import kr.hs.entrydsm.application.integrate.UserDocsManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ApplicationManager implements ApplicationProcessing {

    private final UserDocsManager userDocsManager;

    @Override
    public void writeSelfIntroduce(Long receiptCode, String content) {
        userDocsManager.writeSelfIntroduce(receiptCode, content);
    }

    @Override
    public void writeStudyPlan(Long receiptCode, String content) {
        userDocsManager.writeStudyPlan(receiptCode, content);
    }
}
