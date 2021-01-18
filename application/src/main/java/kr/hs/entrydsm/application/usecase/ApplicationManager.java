package kr.hs.entrydsm.application.usecase;

import kr.hs.entrydsm.application.integrate.UserDocsWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ApplicationManager implements ApplicationProcessing {

    private final UserDocsWriter userDocsWriter;

    @Override
    public void writeSelfIntroduce(Long receiptCode, String content) {
        userDocsWriter.writeSelfIntroduce(receiptCode, content);
    }

    @Override
    public void writeStudyPlan(Long receiptCode, String content) {
        userDocsWriter.writeStudyPlan(receiptCode, content);
    }
}
