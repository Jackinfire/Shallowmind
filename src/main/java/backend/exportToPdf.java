package backend;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;

import java.io.FileNotFoundException;
import java.util.List;
import java.io.File;
import java.nio.file.Paths;

public class exportToPdf {
    private Patient patient;

    public exportToPdf(Patient patient){
        this.patient = patient;
    }

    public void generatePatientDetailsPDF(String fileName) {
        try {
            String userHome = System.getProperty("user.home");
            File downloadsFolder = new File(userHome, "Downloads");

            // Create the file path to the Downloads folder
            File outputFile = new File(downloadsFolder, fileName);

            PdfWriter writer = new PdfWriter(outputFile);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);
            addPersonalDetails(document);
            addInterventionHistory(document);

            document.close();
            System.out.println("PDF created successfully.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void addPersonalDetails(Document document) {
        Text boldText = new Text("Patient Details:").setBold();
        document.add(new Paragraph(boldText));
        document.add(new Paragraph("ID: " + patient.getPatientId()));
        document.add(new Paragraph("Name: " + patient.getName()));
        document.add(new Paragraph("Age: " + patient.getAge()));
        document.add(new Paragraph("Gender: " + patient.getGender()));
        document.add(new Paragraph("Diagnosis: " + patient.getDiagnosis()));
        document.add(new Paragraph("Doctor in Charge: " + patient.getDocInCharge()));
    }

    private void addInterventionHistory(Document document) {
        Text boldText = new Text("Intervention History:").setBold();
        document.add(new Paragraph(boldText));

        List<String> interventionTimes = patient.getInterventionTime();
        List<String> handledByList = patient.getInterventionHandledBy();
        List<String> positionList = patient.getInterventionPosition();
        List<Integer> lengthList = patient.getInterventionLength();

        // Check if both lists are not null or empty, and have the same size
        if (interventionTimes != null && !interventionTimes.isEmpty() && handledByList != null && !handledByList.isEmpty() && positionList != null && !positionList.isEmpty() && lengthList != null && !lengthList.isEmpty() ) {
            for (int i = 0; i < interventionTimes.size(); i++) {
                String time = interventionTimes.get(i);
                String handledBy = handledByList.get(i);
                String position = positionList.get(i);
                int length = lengthList.get(i);

                // Combine the Intervention Time and Handled By into one line
                document.add(new Paragraph( time + "    Handled By: " + handledBy + " -- Stuck in " + position + " posture for " + length + " minutes"));
            }
        } else {
            document.add(new Paragraph("No intervention history available."));
        }
    }

}
