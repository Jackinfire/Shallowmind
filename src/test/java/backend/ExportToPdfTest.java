package backend;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfTextExtractor;
import com.itextpdf.kernel.pdf.PdfWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class ExportToPdfTest {

    private Patient mockPatient;
    private exportToPdf pdfExporter;

    @BeforeEach
    public void setUp() {
        mockPatient = Mockito.mock(Patient.class);
        pdfExporter = new exportToPdf(mockPatient);
    }

    @Test
    public void testGeneratePatientDetailsPDF() throws IOException {
        when(mockPatient.getPatientId()).thenReturn(1);
        when(mockPatient.getName()).thenReturn("John Doe");
        when(mockPatient.getAge()).thenReturn(30);
        when(mockPatient.getGender()).thenReturn("Male");
        when(mockPatient.getDiagnosis()).thenReturn("Diagnosis");
        when(mockPatient.getDocInCharge()).thenReturn("Dr. Smith");
        when(mockPatient.getInterventionTime()).thenReturn(Arrays.asList("2023-09-01 10:00:00"));
        when(mockPatient.getInterventionHandledBy()).thenReturn(Arrays.asList("Nurse A"));
        when(mockPatient.getInterventionPosition()).thenReturn(Arrays.asList("left"));
        when(mockPatient.getInterventionLength()).thenReturn(Arrays.asList(10));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(outputStream);
        PdfDocument pdfDocument = new PdfDocument(writer);
        pdfDocument.close();

        File tempFile = File.createTempFile("test", ".pdf");
        pdfExporter.generatePatientDetailsPDF(tempFile.getAbsolutePath());

        PdfDocument generatedPdf = new PdfDocument(new PdfReader(new FileInputStream(tempFile)));
        String pdfContent = PdfTextExtractor.getTextFromPage(generatedPdf.getFirstPage());
        generatedPdf.close();

        assertTrue(pdfContent.contains("Patient Report"));
        assertTrue(pdfContent.contains("Patient ID: 1"));
        assertTrue(pdfContent.contains("Name: John Doe"));
        assertTrue(pdfContent.contains("Age: 30"));
        assertTrue(pdfContent.contains("Gender: Male"));
        assertTrue(pdfContent.contains("Diagnosis: Diagnosis"));
        assertTrue(pdfContent.contains("Doctor in Charge: Dr. Smith"));
        assertTrue(pdfContent.contains("2023-09-01 10:00:00 -- Stuck in left posture for 10 minutes (Handled By: Nurse A)"));

        tempFile.delete();
    }

    /* Reference taken from GitHub Copilot */
    public String convertInputStreamToString(InputStream inputStream) throws Exception {
        final int bufferSize = 1024;
        final char[] buffer = new char[bufferSize];
        final StringBuilder out = new StringBuilder();
        Reader in = new InputStreamReader(inputStream, "UTF-8");
        for (; ; ) {
            int rsz = in.read(buffer, 0, buffer.length);
            if (rsz < 0)
                break;
            out.append(buffer, 0, rsz);
        }
        return out.toString();
    }
    /* end of reference */
}
