package kr.hs.entrydsm.application.presenter.web;

import kr.hs.entrydsm.application.usecase.pdf.ApplicationPdfService;
import kr.hs.entrydsm.common.context.beans.Published;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;

@Published
@RequiredArgsConstructor
@RestController
@RequestMapping("/pdf")
public class ApplicationPdfController {

    private final static String FILE_NAME = "대덕소프트웨어마이스터고등학교_입학원서";
    private final ApplicationPdfService applicationPdfService;

    @GetMapping(value = "/preview", produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] getPreviewApplicationPdf() {
        return applicationPdfService.getPreviewApplicationPdf();
    }

    @GetMapping(value = "/final", produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] getFinalApplicationPdf(HttpServletResponse response) {
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s.pdf\"", encodeFileName(FILE_NAME)));
        return applicationPdfService.getFinalApplicationPdf();
    }

    private String encodeFileName(String fileName) {
        return new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
    }
}
