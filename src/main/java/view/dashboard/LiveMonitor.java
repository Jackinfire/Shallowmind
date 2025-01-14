package view.dashboard;

import backend.AlertSystem;
import backend.DatabaseLookup;
import backend.Patient;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.List;

/**
 * The LiveMonitor class is responsible for continuously monitoring patient statuses,
 * updating the UI, and managing alerts. This class extends Thread to allow concurrent execution.
 */
public class LiveMonitor extends Thread {

    private List<PatientBox> patientBoxes; // List of patient boxes displayed in the UI
    private List<Patient> patients; // List of patients being monitored
    private StatusPanel statusPanel; // Panel for displaying patient status counts
    private AlertsPanel alertsPanel; // Panel for displaying alerts
    private volatile boolean running = true; // Flag to control thread execution

    /**
     * Constructor for LiveMonitor.
     *
     * @param statusPanel   The StatusPanel for updating patient status counts.
     * @param alertsPanel   The AlertsPanel for displaying posture-related alerts.
     * @param patientBoxes  The list of PatientBox objects representing patients in the UI.
     * @param patients      The list of Patient objects being monitored.
     */
    public LiveMonitor(StatusPanel statusPanel, AlertsPanel alertsPanel, List<PatientBox> patientBoxes, List<Patient> patients) {
        this.patientBoxes = patientBoxes;
        this.patients = patients;
        this.statusPanel = statusPanel;
        this.alertsPanel = alertsPanel;
    }

    /**
     * Stops the monitoring thread gracefully by setting the running flag to false
     * and interrupting the thread if it's sleeping.
     */
    public void stopMonitor() {
        this.running = false; // Set the flag to false
        this.interrupt(); // Interrupt if the thread is sleeping
    }

    @Override
    public void run() {
        DatabaseLookup databaseLookup = new DatabaseLookup();
        AlertSystem alertSystem = new AlertSystem(databaseLookup);

        // Retrieve alert data for all patients
        List<List<String>> allPatientAlerts = new ArrayList<>();
        for (Patient patient : patients) {
            allPatientAlerts.add(alertSystem.monitorPatientPosture(patient.getPatientId()));
        }

        // Retrieve posture data for all patients
        List<List<String>> allPatientPostures = new ArrayList<>();
        for (Patient patient : patients) {
            allPatientPostures.add(patient.getPosture());
        }

        int numMinutes = allPatientAlerts.get(0).size(); // Assuming all patients have the same number of alerts
        int numPatients = patients.size();

        // Iterate over time intervals (minutes)
        for (int minute = 0; minute < numMinutes; minute++) {
            if (!running) break; // Exit the loop if the thread is stopped

            // Clear alerts on the alert panel on the JavaFX Application Thread
            Platform.runLater(() -> alertsPanel.clearAlerts());

            int greenCount = 0;
            int amberCount = 0;
            int redCount = 0;

            // Process each patient's data
            for (int i = 0; i < numPatients; i++) {
                Patient patient = patients.get(i);
                PatientBox patientBox = patientBoxes.get(i);

                String alertColor = allPatientAlerts.get(i).get(minute);
                String posture = allPatientPostures.get(i).get(minute);

                // Update the PatientBox UI on the JavaFX Application Thread
                Platform.runLater(() -> {
                    switch (alertColor) {
                        case "green":
                            patientBox.setAlertColor("#328D40");
                            break;
                        case "amber":
                            patientBox.setAlertColor("#E67E22");
                            alertsPanel.createAlert("amber", patient);
                            break;
                        case "red":
                            patientBox.setAlertColor("#C0392B");
                            alertsPanel.createAlert("red", patient);
                            break;
                        default:
                            System.err.println("Unknown alert color: " + alertColor);
                    }
                });

                // Update the posture image on the JavaFX Application Thread
                Platform.runLater(() -> {
                    switch (posture) {
                        case "left":
                            patientBox.setPostureImage("left");
                            break;
                        case "right":
                            patientBox.setPostureImage("right");
                            break;
                        case "middle":
                            patientBox.setPostureImage("straight");
                            break;
                        default:
                            System.err.println("Unknown posture color: " + posture);
                    }
                });

                // Count the alert color for the status panel
                switch (alertColor) {
                    case "green":
                        greenCount++;
                        break;
                    case "amber":
                        amberCount++;
                        break;
                    case "red":
                        redCount++;
                        break;
                }
            }

            // Update the StatusPanel counters
            int finalGreenCount = greenCount;
            int finalAmberCount = amberCount;
            int finalRedCount = redCount;

            Platform.runLater(() -> {
                statusPanel.updateGreenCount(finalGreenCount);
                statusPanel.updateAmberCount(finalAmberCount);
                statusPanel.updateRedCount(finalRedCount);
            });

            // Wait for 1 minute before the next update
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                // Thread interrupted intentionally, stop gracefully
                break; // Exit the loop if the thread is interrupted
            }
        }
    }

    /**
     * Gracefully stops the monitoring process by setting the running flag to false.
     */
    public void stopMonitoring() {
        running = false;
    }
}
