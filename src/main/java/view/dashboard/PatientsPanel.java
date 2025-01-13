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


public class PatientsPanel extends ScrollPane {

    public PatientsPanel(StatusPanel statusPanel){

        // Make the background and borders of the scrolling pane transparent
        this.setStyle("-fx-background: #081c44; -fx-border-color: #081c44;");

        // Set size of the scrolling pane
        this.setMinSize(WindowDimensions.windowWidth*0.8 + 20,WindowDimensions.windowHeight*0.7);
        this.setMaxSize(WindowDimensions.windowWidth*0.8 + 20,WindowDimensions.windowHeight*0.7);

        // Create a DatabaseLookup instance to get total number of patients
        DatabaseLookup databaseLookup = new DatabaseLookup();
        int numberOfPatients = databaseLookup.getNumberOfPatients();

        // Create panel that will hold the patient boxes
        VBox panel = new VBox();

        int numberOfRows = Math.round(numberOfPatients/2);
        panel.setMinSize(WindowDimensions.windowWidth*0.8,numberOfRows * ((WindowDimensions.windowHeight * 0.125)+10)+30);
        panel.setMaxSize(WindowDimensions.windowWidth*0.8,numberOfRows * ((WindowDimensions.windowHeight * 0.125)+10)+30);
        panel.setSpacing(10); // Set spacing between patient boxes
        panel.setStyle("-fx-background-color: #33516d;" +
                "-fx-background-radius: 15;");

        panel.setAlignment(Pos.TOP_CENTER);



        List<PatientBox> patientBoxList = new ArrayList<>();
        List<Patient> patientList = new ArrayList<>();

        // Loop to create lists of all patients and a patient box for each patient
        for (int i = 1; i <= numberOfPatients; i++){
            Patient patient = new Patient(i);
            PatientBox patientBox = new PatientBox(patient);

            patientList.add(patient);
            patientBoxList.add(patientBox);
        }

        // Start monitor for all patients
        LiveMonitor monitor = new LiveMonitor(statusPanel,patientBoxList,patientList);
        monitor.start();

        for (int i = 0; i < numberOfPatients; i = i+2) {

            PatientBox patientBox1 = patientBoxList.get(i);

            // Create a Hbox which will contain one or two patient boxes
            HBox hBox1 = new HBox();
            hBox1.getChildren().addAll(patientBox1); // Add patient box to HBox

            // This checks if i+1 is out of bounds, if it is not, it adds the second patient box to the HBox
            if (i + 1 < numberOfPatients) {
                PatientBox patientBox2 = patientBoxList.get(i + 1);
                hBox1.getChildren().add(patientBox2);
            }

            hBox1.setSpacing(10); // Sets horizontal spacing between patient boxes

            // Add HBox to the patients panel
            panel.getChildren().addAll(hBox1);

        }
        // Loop
        panel.setPadding(new Insets(20,20,20,20));

        this.setContent(panel);
        this.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Remove horizontal scroll bar




    }

}
