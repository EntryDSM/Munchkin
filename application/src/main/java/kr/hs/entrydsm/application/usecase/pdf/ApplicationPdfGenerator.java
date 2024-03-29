package kr.hs.entrydsm.application.usecase.pdf;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.utils.PdfMerger;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.minmaxwidth.MinMaxWidth;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import kr.hs.entrydsm.application.config.TemplateFileName;
import kr.hs.entrydsm.application.entity.Applicant;
import kr.hs.entrydsm.application.usecase.dto.CalculatedScore;
import kr.hs.entrydsm.application.usecase.dto.PdfData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class ApplicationPdfGenerator {

    private final PdfProcessor pdfProcessor;
    private final PdfDataConverter pdfDataConverter;
    private final TemplateProcessor templateProcessor;

    public byte[] generate(Applicant applicant, CalculatedScore calculatedScore) {
        return generateApplicationPdf(applicant, calculatedScore);
    }

    private byte[] generateApplicationPdf(Applicant applicant, CalculatedScore calculatedScore) {
        PdfData data = pdfDataConverter.applicationToInfo(applicant, calculatedScore);

        ByteArrayOutputStream[] outputStreams = getTemplateFileNames(applicant).parallelStream()
                .map(template -> templateProcessor.convertTemplateIntoHtmlString(template, data.toMap()))
                .map(pdfProcessor::convertHtmlToPdf)
                .toArray(ByteArrayOutputStream[]::new);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfDocument mergedDocument = new PdfDocument(new PdfWriter(outputStream));
        PdfMerger pdfMerger = new PdfMerger(mergedDocument);
        Document document = new Document(mergedDocument);

        for (ByteArrayOutputStream pdfStream : outputStreams) {
            PdfDocument pdfDoc = getPdfDocument(pdfStream);
            mergeDocument(pdfMerger, pdfDoc);
        }

        for (int i = 1; i <= mergedDocument.getNumberOfPages(); i++) {
            document.showTextAligned(new Paragraph(String.format("- %s -", i)),
                    300, 25, i, TextAlignment.CENTER, VerticalAlignment.BOTTOM, 0);
        }

        document.close();

        return outputStream.toByteArray();
    }

    private PdfDocument getPdfDocument(ByteArrayOutputStream outputStream) {
        try {
            InputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
            return new PdfDocument(new PdfReader(inputStream));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void mergeDocument(PdfMerger merger, PdfDocument document) {
        if (document != null) {
            merger.merge(document, 1, document.getNumberOfPages());
            document.close();
        }
    }

    private List<String> getTemplateFileNames(Applicant applicant) {
        List<String> result = new LinkedList<>(List.of(
                TemplateFileName.APPLICATION_FOR_ADMISSION,
                TemplateFileName.INTRODUCTION,
                TemplateFileName.NON_SMOKING
        ));

        if (!applicant.isQualificationExam() && !applicant.isCommonApplicationType())
            result.add(2, TemplateFileName.RECOMMENDATION);

        return result;
    }
}
