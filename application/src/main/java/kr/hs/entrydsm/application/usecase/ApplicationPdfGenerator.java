package kr.hs.entrydsm.application.usecase;

import kr.hs.entrydsm.application.config.TemplateFileName;
import kr.hs.entrydsm.application.entity.Applicant;
import kr.hs.entrydsm.application.usecase.dto.Score;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class ApplicationPdfGenerator {

    private final PdfDataConverter pdfDataConverter;
    private final TemplateProcessor templateProcessor;

    public byte[] applicationPdf(Applicant applicant, Score score) {
        return generateApplicationPdf(applicant, score);
    }

    private byte[] generateApplicationPdf(Applicant applicant, Score score) {
        Map<String, Object> data = pdfDataConverter.applicationToInfo(applicant, score);

        List<String> templates = new LinkedList<>(List.of(
                TemplateFileName.APPLICATION_FOR_ADMISSION,
                TemplateFileName.INTRODUCTION,
                TemplateFileName.NON_SMOKING
        ));

        if (!applicant.isQualificationExam() && !applicant.isCommonApplicationType()) {
            templates.add(2, TemplateFileName.RECOMMENDATION);
        }

        ByteArrayOutputStream result = templates.parallelStream()
                .map(template -> templateProcessor.process(template, data))
                .map(HtmlConverter::convertHtmlToPdf)
                .reduce(PdfMerger::concat)
                .orElseGet(() -> (ByteArrayOutputStream) ByteArrayOutputStream.nullOutputStream());

        return result.toByteArray();
    }
}
