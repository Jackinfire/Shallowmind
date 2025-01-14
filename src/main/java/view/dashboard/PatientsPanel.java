package view.dashboard;

import backend.DatabaseLookup;
import backend.Patient;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import view.utils.WindowDimensions;

import java.util.ArrayList;
import java.util.List;

/**
 * The PatientsPanel class represents a scrollable panel containing patient boxes
 * for displaying patient information. It also initializes a LiveMonitor thread to
 * monitor patient statuses and updates.
 */
public class PatientsPanel extends ScrollPane {

    private LiveMonitor liveMonitor; // LiveMonitor instance to monitor patient statuses.

    /**
     * Constructor for PatientsPanel.
     *
     * @param statusPanel The StatusPanel to update the status counts (green, amber, red).
     * @param alertsPanel The AlertsPanel to display alerts for patients in critical states.
     */
    public PatientsPanel(StatusPanel statusPanel, AlertsPanel alertsPanel) {

        // Make the background and borders of the scrolling pane the same colour as the background.
        this.setStyle("-fx-background: #081c44; -fx-border-color: #081c44;");

        // Set size of the scrolling pane.
        this.setMinSize(WindowDimensions.windowWidth * 0.8 + 20, WindowDimensions.windowHeight * 0.7);
        this.setMaxSize(WindowDimensions.windowWidth * 0.8 + 20, WindowDimensions.windowHeight * 0.7);

        // Create a DatabaseLookup instance to get the total number of patients.
        DatabaseLookup databaseLookup = new DatabaseLookup();
        int numberOfPatients = databaseLookup.getNumberOfPatients();

        // Create a panel (VBox) to hold the patient boxes.
        VBox panel = new VBox();

        // Calculate the number of rows required for the patients' layout.
        int numberOfRows = Math.round(numberOfPatients / 2.0f);
        panel.setMinSize(WindowDimensions.windowWidth * 0.8,
                numberOfRows * ((WindowDimensions.windowHeight * 0.125) + 10) + 30);
        panel.setMaxSize(WindowDimensions.windowWidth * 0.8,
                numberOfRows * ((WindowDimensions.windowHeight * 0.125) + 10) + 30);

        // Set spacing between rows and style for the panel.
        panel.setSpacing(10);
        panel.setStyle("-fx-background-color: #33516d;" +
                "-fx-background-radius: 15;");
        panel.setAlignment(Pos.TOP_CENTER);

        // Lists to hold PatientBox and Patient objects.
        List<PatientBox> patientBoxList = new ArrayList<>();
        List<Patient> patientList = new ArrayList<>();

        // Loop to create Patient objects and their corresponding PatientBox objects.
        for (int i = 1; i <= numberOfPatients; i++) {
            Patient patient = new Patient(i); // Create a new Patient object.
            PatientBox patientBox = new PatientBox(patient); // Create a corresponding PatientBox.

            patientList.add(patient); // Add the patient to the patient list.
            patientBoxList.add(patientBox); // Add the patient box to the patient box list.
        }

        // Start the LiveMonitor thread to monitor all patients.
        this.liveMonitor = new LiveMonitor(statusPanel, alertsPanel, patientBoxList, patientList);
        liveMonitor.start();

        // Loop through the patient boxes in pairs to add them to rows (HBox).
        for (int i = 0; i < numberOfPatients; i = i + 2) {

            // Get the first patient box for the row.
            PatientBox patientBox1 = patientBoxList.get(i);

            // Create an HBox to hold one or two patient boxes.
            HBox hBox1 = new HBox();
            hBox1.getChildren().addAll(patientBox1); // Add the first patient box to the HBox.

            // Check if the next patient box exists, and if so, add it to the HBox.
            if (i + 1 < numberOfPatients) {
                PatientBox patientBox2 = patientBoxList.get(i + 1);
                hBox1.getChildren().add(patientBox2);
            }

            // Set spacing between patient boxes in the HBox.
            hBox1.setSpacing(10);

            // Add the HBox to the panel (VBox).
            panel.getChildren().addAll(hBox1);
        }

        // Add padding around the panel content.
        panel.setPadding(new Insets(20, 20, 20, 20));

        // Set the content of the scrollable pane to the panel.
        this.setContent(panel);

        // Remove the horizontal scroll bar.
        this.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    }

    /**
     * Getter method to retrieve the LiveMonitor instance.
     *
     * @return The LiveMonitor instance monitoring the patient statuses.
     */
    public LiveMonitor getLiveMonitor() {
        return this.liveMonitor;
    }

}
