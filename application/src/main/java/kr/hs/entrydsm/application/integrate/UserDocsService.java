package kr.hs.entrydsm.application.integrate;

public interface UserDocsService {

    void writeSelfIntroduce(Long receiptCode, String content);

    void writeStudyPlan(Long receiptCode, String content);

    String readSelfIntroduce(Long receiptCode);

    String readStudyPlan(Long receiptCode);
}
