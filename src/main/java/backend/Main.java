package backend;

public class Main {
    public static void main(String[] args) {
        // 1. Create (or inject) a backend.DatabaseLookup instance
        DatabaseLookup dbLookup = new DatabaseLookup();

        // 2. Create the backend.AlertSystem object
        AlertSystem alertSystem = new AlertSystem(dbLookup);

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

    }
}

