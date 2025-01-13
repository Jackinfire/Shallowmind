package backend;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;

import java.io.FileNotFoundException;
import java.util.List;
import java.io.File;

public class exportToPdf {
    private Patient patient;

    public exportToPdf(Patient patient){
        this.patient = patient;
    }

    public void generatePatientDetailsPDF(String fileName) {
        try {
            String homeDirectory = System.getProperty("user.home");
            File downloadsFolder = new File(homeDirectory, "Downloads");

            if (!downloadsFolder.exists()) {
                System.out.println("Downloads folder doesn't exist and instead will export to current directory");
                downloadsFolder = new File("."); // Current directory
            }

            File outputFile = new File(downloadsFolder, fileName);
            PdfWriter writer = new PdfWriter(outputFile);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);
            addPersonalDetails(document);
            addInterventionHistory(document);
            document.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    private void addPersonalDetails(Document document) {
        Text Header = new Text("Patient Report").setBold().setFontSize(20);
        document.add(new Paragraph(Header));
        Text patientHeader = new Text("Patient Details:").setBold();
        document.add(new Paragraph(patientHeader));
        document.add(new Paragraph("Patient ID: " + patient.getPatientId()));
        document.add(new Paragraph("Name: " + patient.getName()));
        document.add(new Paragraph("Age: " + patient.getAge()));
        document.add(new Paragraph("Gender: " + patient.getGender()));
        document.add(new Paragraph("Diagnosis: " + patient.getDiagnosis()));
        document.add(new Paragraph("Doctor in Charge: Dr " + patient.getDocInCharge()));
    }

    private void addInterventionHistory(Document document) {
        Text interventionHeader = new Text("Intervention History:").setBold();
        document.add(new Paragraph(interventionHeader));
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
                document.add(new Paragraph( time + " -- Stuck in " + position + " posture for " + length + " minutes (Handled By: " + handledBy + ")"));
            }
        } else {
            document.add(new Paragraph("No intervention history available or intervention history fault. Please check database."));
        }
    }

}
