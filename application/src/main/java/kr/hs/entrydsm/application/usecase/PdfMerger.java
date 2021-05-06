package kr.hs.entrydsm.application.usecase;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class PdfMerger {

    public static ByteArrayOutputStream concat(ByteArrayOutputStream first, ByteArrayOutputStream second) {
        InputStream firstInputStream = new ByteArrayInputStream(first.toByteArray());
        InputStream secondInputStream = new ByteArrayInputStream(second.toByteArray());

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfDocument mergedDocument = new PdfDocument(new PdfWriter(outputStream));
        com.itextpdf.kernel.utils.PdfMerger merger = new com.itextpdf.kernel.utils.PdfMerger(mergedDocument);

        PdfDocument firstDocument = null;
        PdfDocument secondDocument = null;

        try {
            firstDocument = new PdfDocument(new PdfReader(firstInputStream));
            secondDocument = new PdfDocument(new PdfReader(secondInputStream));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (firstDocument != null) {
            merger.merge(firstDocument, 1, firstDocument.getNumberOfPages());
            firstDocument.close();
        }

        if (secondDocument != null) {
            merger.merge(secondDocument, 1, secondDocument.getNumberOfPages());
            secondDocument.close();
        }

        mergedDocument.close();
        return outputStream;
    }
}
