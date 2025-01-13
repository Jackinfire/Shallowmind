package view.dashboard;

import backend.AlertSystem;
import backend.DatabaseLookup;
import javafx.application.Platform;

import java.util.List;

public class LiveMonitor extends Thread {
    private PatientBox patientBox;
    private int patientID;
    public LiveMonitor(int patientID, PatientBox patientBox, StatusPanel statusPanel){

        this.patientID = patientID;
        this.patientBox = patientBox;

    }


    @Override
    public void run() {
        DatabaseLookup databaseLookup = new DatabaseLookup();
        AlertSystem alertSystem = new AlertSystem(databaseLookup);

        List<String> alertColorList = alertSystem.monitorPatientPosture(patientID);

        for (int minute = 0; minute < alertColorList.size(); minute++) {
            String alertColor = alertColorList.get(minute);

            // Update the PatientBox UI on the JavaFX Application Thread

            if (alertColor.equals("green")) {
                patientBox.setAlertColor("#328D40"); // Green color hex
            } else if (alertColor.equals("amber")) {
                patientBox.setAlertColor("#E67E22"); // Amber color hex
            } else if (alertColor.equals("red")) {
                patientBox.setAlertColor("#C0392B"); // Red color hex
            } else {
                System.err.println("Unknown alert color: " + alertColor);
            }

            // Wait for 10 seconds before the next update
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.err.println("LiveMonitor interrupted.");
                break; // Exit the loop if the thread is interrupted
            }
        }
    }

}
