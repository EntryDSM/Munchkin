package kr.hs.entrydsm.application.usecase.pdf;

import kr.hs.entrydsm.application.config.TemplateFileName;
import kr.hs.entrydsm.application.entity.Applicant;
import kr.hs.entrydsm.application.usecase.dto.CalculatedScore;
import kr.hs.entrydsm.application.usecase.dto.PdfData;
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

    public byte[] generate(Applicant applicant, CalculatedScore calculatedScore) {
        return generateApplicationPdf(applicant, calculatedScore);
    }

    private byte[] generateApplicationPdf(Applicant applicant, CalculatedScore calculatedScore) {
        PdfData data = pdfDataConverter.applicationToInfo(applicant, calculatedScore);

        ByteArrayOutputStream result = getTemplateFileNames(applicant).parallelStream()
                .map(template -> templateProcessor.convertTemplateIntoHtmlString(template, data.toMap()))
                .map(PdfProcessor::convertHtmlToPdf)
                .reduce(PdfProcessor::concat)
                .orElseGet(() -> (ByteArrayOutputStream) ByteArrayOutputStream.nullOutputStream());

        return result.toByteArray();
    }

    private List<String> getTemplateFileNames(Applicant applicant) {
        List<String> result = new LinkedList<>(List.of(
                TemplateFileName.APPLICATION_FOR_ADMISSION,
                TemplateFileName.INTRODUCTION,
                TemplateFileName.NON_SMOKING
        ));

        if (!applicant.isQualificationExam() && !applicant.isCommonApplicationType()) {
            result.add(2, TemplateFileName.RECOMMENDATION);
        }

        return result;
    }
}
