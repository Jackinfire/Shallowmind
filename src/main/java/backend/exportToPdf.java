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


    public exportToPdf(Patient patient) {
        this.patient = patient;
    }

    /**
     * Generates a PDF containing the patient's details and intervention history.
     * The PDF is saved to the "Downloads" folder or the current directory if "Downloads" doesn't exist.
     *
     * @param fileName The name of the PDF file to be created.
     */
    public void generatePatientDetailsPDF(String fileName) {
        try {
            // Determine the home directory and set the export location to the "Downloads" folder
            String homeDirectory = System.getProperty("user.home");
            File downloadsFolder = new File(homeDirectory, "Downloads");

            // Fallback to the current directory
            if (!downloadsFolder.exists()) {
                System.out.println("Downloads folder doesn't exist and instead will export to current directory");
                downloadsFolder = new File(".");
            }

            // Create the PDF file
            File outputFile = new File(downloadsFolder, fileName);
            PdfWriter writer = new PdfWriter(outputFile);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            addPersonalDetails(document);
            addInterventionHistory(document);

            // Close the document
            document.close();
        } catch (FileNotFoundException e) {
            // Handle the exception
            e.printStackTrace();
        }
    }

    /**
     * Adds the patient's personal details to the PDF document.
     *
     * @param document The PDF document to which details are added.
     */
    private void addPersonalDetails(Document document) {
        // Add the header for the report
        Text Header = new Text("Patient Report").setBold().setFontSize(20);
        document.add(new Paragraph(Header));

        // Add a subsection for patient details
        Text patientHeader = new Text("Patient Details:").setBold();
        document.add(new Paragraph(patientHeader));

        // Add patient details to the PDF
        document.add(new Paragraph("Patient ID: " + patient.getPatientId()));
        document.add(new Paragraph("Name: " + patient.getName()));
        document.add(new Paragraph("Age: " + patient.getAge()));
        document.add(new Paragraph("Gender: " + patient.getGender()));
        document.add(new Paragraph("Diagnosis: " + patient.getDiagnosis()));
        document.add(new Paragraph("Doctor in Charge: Dr " + patient.getDocInCharge()));
    }

    /**
     * Adds the patient's intervention history to the PDF document.
     *
     * @param document The PDF document to which the intervention history is added.
     */
    private void addInterventionHistory(Document document) {
        // Add a subsection header for intervention history
        Text interventionHeader = new Text("Intervention History:").setBold();
        document.add(new Paragraph(interventionHeader));

        // Retrieve data for intervention history
        List<String> interventionTimes = patient.getInterventionTime();
        List<String> handledByList = patient.getInterventionHandledBy();
        List<String> positionList = patient.getInterventionPosition();
        List<Integer> lengthList = patient.getInterventionLength();

        // Check if the data is valid and complete
        if (interventionTimes != null && !interventionTimes.isEmpty()
                && handledByList != null && !handledByList.isEmpty()
                && positionList != null && !positionList.isEmpty()
                && lengthList != null && !lengthList.isEmpty()) {

            // Loop through the intervention history and add each record to the document
            for (int i = 0; i < interventionTimes.size(); i++) {
                String time = interventionTimes.get(i);
                String handledBy = handledByList.get(i);
                String position = positionList.get(i);
                int length = lengthList.get(i);

                document.add(new Paragraph(time + " -- Stuck in " + position
                        + " posture for " + length + " minutes (Handled By: " + handledBy + ")"));
            }
        } else {
            // Add a fallback message if the intervention history is incomplete or missing
            document.add(new Paragraph("No intervention history available or intervention history fault. Please check database."));
        }
    }
}
