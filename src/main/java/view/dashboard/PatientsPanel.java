package view.dashboard;

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
        Patient patient1 = new Patient(1);
        PatientBox patientBox1 = new PatientBox(patient1);

        Patient patient2 = new Patient(2);
        PatientBox patientBox2 = new PatientBox(patient2);

        Patient patient3 = new Patient(3);
        PatientBox patientBox3 = new PatientBox(patient3);

        Patient patient4 = new Patient(4);
        PatientBox patientBox4 = new PatientBox(patient4);

        HBox hBox1 = new HBox();
        hBox1.getChildren().addAll(patientBox1,patientBox2);
        hBox1.setSpacing(10);

        HBox hBox2 = new HBox();
        hBox2.getChildren().addAll(patientBox3,patientBox4);
        hBox2.setSpacing(10);

        panel.getChildren().addAll(hBox1,hBox2);
        panel.setPadding(new Insets(20,20,20,20));

        this.setContent(panel);
        this.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Remove horizontal scroll bar


    }

}
