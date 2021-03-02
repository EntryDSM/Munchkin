package kr.hs.entrydsm.application.integrate.user;

public interface UserDocsService {

    void writeSelfIntroduce(Long receiptCode, String content);

    void writeStudyPlan(Long receiptCode, String content);

    String getSelfIntroduce(Long receiptCode);

    String getStudyPlan(Long receiptCode);
}
