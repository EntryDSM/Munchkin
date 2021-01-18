package kr.hs.entrydsm.application.integrate;

public interface UserDocsWriter {

    void writeSelfIntroduce(Long receiptCode, String content);
    void writeStudyPlan(Long receiptCode, String content);
}
