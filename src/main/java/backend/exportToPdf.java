package backend;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.FileNotFoundException;
import java.util.List;

public class exportToPdf {

    public void generatePatientDetailsPDF(String fileName, Patient patient) {
        try {
            PdfWriter writer = new PdfWriter(fileName);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            addPersonalDetails(document, patient);
            addInterventionHistory(document, patient);

            document.close();
            System.out.println("PDF created successfully.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void addPersonalDetails(Document document, Patient patient) {
        document.add(new Paragraph("Patient Details"));
        document.add(new Paragraph("ID: " + patient.getPatientId()));
        document.add(new Paragraph("Name: " + patient.getName()));
        document.add(new Paragraph("Age: " + patient.getAge()));
        document.add(new Paragraph("Gender: " + patient.getGender()));
        document.add(new Paragraph("Diagnosis: " + patient.getDiagnosis()));
        document.add(new Paragraph("Doctor in Charge: " + patient.getDocInCharge()));
    }

    private void addInterventionHistory(Document document, Patient patient) {
        document.add(new Paragraph("Intervention History"));

        List<String> interventionTimes = patient.getInterventionTime();
        List<String> handledByList = patient.getInterventionHandledBy();

        // Check if both lists are not null or empty, and have the same size
        if (interventionTimes != null && !interventionTimes.isEmpty() && handledByList != null && !handledByList.isEmpty()) {
            for (int i = 0; i < interventionTimes.size(); i++) {
                String time = interventionTimes.get(i);
                String handledBy = handledByList.get(i);

                // Combine the Intervention Time and Handled By into one line
                document.add(new Paragraph("Intervention Time: " + time + "    Handled By: " + handledBy));
            }
        } else {
            document.add(new Paragraph("No intervention history available."));
        }
    }

}