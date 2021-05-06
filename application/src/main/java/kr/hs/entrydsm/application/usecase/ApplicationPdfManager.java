package kr.hs.entrydsm.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationPdfManager implements ApplicationPdfService {

    private final ApplicationPdfGenerator applicationPdfGenerator;

    @Override
    public byte[] getPreviewApplicationPdf(long receiptCode) {
        return new byte[0];
    }

    @Override
    public byte[] getFinalApplicationPdf(long receiptCode) {
        return new byte[0];
    }
}
