package backend;

public class Main {
    public static void main(String[] args) {
        // 1. Create (or inject) a backend.DatabaseLookup instance
        DatabaseLookup dbLookup = new DatabaseLookup();

        // 2. Create the backend.AlertSystem object
        AlertSystem alertSystem = new AlertSystem(dbLookup);

        // 3. Check the posture status for patient ID = 1
        String finalStatus = alertSystem.monitorPatientPosture(4);

        // 4. Print the resulting status
        System.out.println("Final alert status for Patient 4: " + finalStatus);


        // Patient ID to fetch
        int patientId = 3;

        // Create a Patient instance
        Patient patient = new Patient(patientId);

        // Retrieve the age of the patient
        Integer age = patient.getAge();

        // Display the result
        if (age != null) {
            System.out.println("The age of the patient with ID " + patientId + " is: " + age);
        } else {
            System.out.println("Patient with ID " + patientId + " not found.");
        }


        System.out.println(patient.getPatientDetails());
        exportToPdf pdfGenerator = new exportToPdf();
        pdfGenerator.generatePatientDetailsPDF("patient_details.pdf", patient);a
    }
}

