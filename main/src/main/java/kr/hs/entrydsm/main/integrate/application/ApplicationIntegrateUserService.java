package kr.hs.entrydsm.main.integrate.application;

import kr.hs.entrydsm.application.integrate.user.UserDocsService;
import kr.hs.entrydsm.common.context.beans.Published;
import org.springframework.stereotype.Service;

@Published
@Service
public class ApplicationIntegrateUserService implements UserDocsService {
    @Override
    public void writeSelfIntroduce(Long receiptCode, String content) {

    }

    @Override
    public void writeStudyPlan(Long receiptCode, String content) {

    }

    @Override
    public String getSelfIntroduce(Long receiptCode) {
        return null;
    }

    @Override
    public String getStudyPlan(Long receiptCode) {
        return null;
    }
}
