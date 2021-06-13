package kr.hs.entrydsm.application.usecase.pdf;

public interface ApplicationPdfService {

    byte[] getPreviewApplicationPdf();

    byte[] getFinalApplicationPdf();
}
