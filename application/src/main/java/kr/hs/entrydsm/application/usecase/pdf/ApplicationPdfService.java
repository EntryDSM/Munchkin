package kr.hs.entrydsm.application.usecase.pdf;

public interface ApplicationPdfService {

    byte[] getPreviewApplicationPdf(long receiptCode);

    byte[] getFinalApplicationPdf(long receiptCode);
}
