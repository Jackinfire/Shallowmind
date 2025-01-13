package view.dashboard;

import backend.DatabaseLookup;
import backend.Patient;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import view.utils.WindowDimensions;


public class PatientsPanel extends ScrollPane {

    public PatientsPanel(){

        this.setStyle("-fx-background: transparent; -fx-border-color: transparent;");
        this.setMinSize(WindowDimensions.windowWidth*0.8 + 20,WindowDimensions.windowHeight*0.7);
        this.setMaxSize(WindowDimensions.windowWidth*0.8 + 20,WindowDimensions.windowHeight*0.7);

        VBox panel = new VBox();
        panel.setMinSize(WindowDimensions.windowWidth*0.8 + 20,WindowDimensions.windowHeight*0.7);
        panel.setMaxSize(WindowDimensions.windowWidth*0.8 + 20,WindowDimensions.windowHeight*0.7);
        panel.setSpacing(10); // Set spacing between patient boxes
        panel.setStyle("-fx-background-color: #33516d;" +
                "-fx-background-radius: 15;");

        panel.setAlignment(Pos.CENTER);

        DatabaseLookup databaseLookup = new DatabaseLookup();

        int numberOfPatients = databaseLookup.getNumberOfPatients();
        for (int i = 1; i <= numberOfPatients; i = i+2) {

            Patient patient1 = new Patient(i); // Create a new Patient with ID 'i'
            PatientBox patientBox1 = new PatientBox(patient1);
            LiveMonitor monitor1 = new LiveMonitor(i,patientBox1);
            monitor1.start();

            Patient patient2 = new Patient(i+1);
            PatientBox patientBox2 = new PatientBox(patient2);
            LiveMonitor monitor2 = new LiveMonitor(i+1,patientBox2);
            monitor2.start();

            HBox hBox1 = new HBox();
            hBox1.getChildren().addAll(patientBox1,patientBox2);
            hBox1.setSpacing(10);

            panel.getChildren().addAll(hBox1);

        }
        panel.setPadding(new Insets(20,20,20,20));

        this.setContent(panel);
        this.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Remove horizontal scroll bar




    }

}
