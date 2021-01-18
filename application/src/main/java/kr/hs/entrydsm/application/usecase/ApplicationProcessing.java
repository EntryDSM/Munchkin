package kr.hs.entrydsm.application.usecase;

public interface ApplicationProcessing {

    void writeSelfIntroduce(Long receiptCode, String content);

    void writeStudyPlan(Long receiptCode, String content);
}
