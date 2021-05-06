package kr.hs.entrydsm.application.usecase;

public interface ApplicationPdfService {

    byte[] getPreviewApplicationPdf(long receiptCode);

    byte[] getFinalApplicationPdf(long receiptCode);
}
