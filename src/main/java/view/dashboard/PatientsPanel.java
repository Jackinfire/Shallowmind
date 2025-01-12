package view.dashboard;

import backend.Patient;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;



public class PatientsPanel extends VBox {

    public PatientsPanel(){
        this.setStyle("-fx-background-color: #33516d;" +
                "-fx-background-radius: 15;");


        this.setPrefSize(1200,430);
        this.setSpacing(10); // Set spacing between patient boxes

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

        this.getChildren().addAll(hBox1,hBox2);
        this.setPadding(new Insets(20,20,20,20));

    }

    // Each hbox should contain two patient boxes

//    private HBox createPatientHBox(){


}
