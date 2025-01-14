package view.editMenu;

import backend.DatabaseLookup;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;


/**
 * The PatientAdder class extends BasePatientForm and provides the UI components
 * and functionality for adding a new patient to the system.
 */
public class PatientAdder extends BasePatientForm {


    /**
     * Returns the title of the form.
     *
     * @return the title of the form.
     */
    @Override
    public String getTitle() {
        return "Patient Adder";
    }



    /**
     * Adds User interface components to the GridPane for capturing patient details.
     *
     * @param gridPane the GridPane to which components will be added.
     */
    @Override
    public void addComponents(GridPane gridPane) {
        Label title = new Label("Patient Adder");
        title.setFont(new Font(25));
        GridPane.setColumnIndex(title, 7);
        GridPane.setColumnSpan(title, 6);
        gridPane.getChildren().add(title);

        Label idLabel = new Label("Patient ID:");
        idLabel.setFont(new Font(15));
        GridPane.setColumnIndex(idLabel, 1);
        GridPane.setRowIndex(idLabel, 2);
        GridPane.setColumnSpan(idLabel, 4);
        gridPane.getChildren().add(idLabel);


        TextField idField = new TextField();
        idField.setPromptText("Enter ID");
        GridPane.setColumnIndex(idField, 4);
        GridPane.setRowIndex(idField, 2);
        GridPane.setColumnSpan(idField, 4);
        gridPane.getChildren().add(idField);


        Label nameLabel = new Label("Patient Name:");
        nameLabel.setFont(new Font(15));
        GridPane.setColumnIndex(nameLabel, 1);
        GridPane.setRowIndex(nameLabel, 4);
        GridPane.setColumnSpan(nameLabel, 3);
        gridPane.getChildren().add(nameLabel);


        TextField nameField = new TextField();
        nameField.setPromptText("Enter Name");
        GridPane.setColumnIndex(nameField, 4);
        GridPane.setRowIndex(nameField, 4);
        GridPane.setColumnSpan(nameField, 4);
        gridPane.getChildren().add(nameField);


        Label wardLabel = new Label("Ward Number:");
        wardLabel.setFont(new Font(15));
        GridPane.setColumnIndex(wardLabel, 1);
        GridPane.setRowIndex(wardLabel, 5);
        GridPane.setColumnSpan(wardLabel, 5);
        gridPane.getChildren().add(wardLabel);

        TextField wardField = new TextField();
        wardField.setPromptText("Enter Ward #");
        GridPane.setColumnIndex(wardField, 4);
        GridPane.setRowIndex(wardField, 5);
        GridPane.setColumnSpan(wardField, 4);
        gridPane.getChildren().add(wardField);

        Label roomLabel = new Label("Room Number:");
        roomLabel.setFont(new Font(15));
        GridPane.setColumnIndex(roomLabel, 1);
        GridPane.setRowIndex(roomLabel, 6);
        GridPane.setColumnSpan(roomLabel, 4);
        gridPane.getChildren().add(roomLabel);

        TextField roomField = new TextField();
        roomField.setPromptText("Enter Room #");
        GridPane.setColumnIndex(roomField, 4);
        GridPane.setRowIndex(roomField, 6);
        GridPane.setColumnSpan(roomField, 4);
        gridPane.getChildren().add(roomField);

        Label ageLabel = new Label("Patient Age:");
        ageLabel.setFont(new Font(15));
        GridPane.setColumnIndex(ageLabel, 1);
        GridPane.setRowIndex(ageLabel, 7);
        GridPane.setColumnSpan(ageLabel, 4);
        gridPane.getChildren().add(ageLabel);

        TextField ageField = new TextField();
        ageField.setPromptText("Enter Patient Age");
        GridPane.setColumnIndex(ageField, 4);
        GridPane.setRowIndex(ageField, 7);
        GridPane.setColumnSpan(ageField, 4);
        gridPane.getChildren().add(ageField);

        Label contactLabel = new Label("Emergency Contact:");
        contactLabel.setFont(new Font(15));
        GridPane.setColumnIndex(contactLabel, 1);
        GridPane.setRowIndex(contactLabel, 8);
        GridPane.setColumnSpan(contactLabel, 5);
        gridPane.getChildren().add(contactLabel);

        TextField contactField = new TextField();
        contactField.setPromptText("Enter Contact #");
        GridPane.setColumnIndex(contactField, 5);
        GridPane.setRowIndex(contactField, 8);
        GridPane.setColumnSpan(contactField, 4);
        gridPane.getChildren().add(contactField);

        Label diagnosisLabel = new Label("Diagnosis:");
        diagnosisLabel.setFont(new Font(15));
        GridPane.setColumnIndex(diagnosisLabel, 1);
        GridPane.setRowIndex(diagnosisLabel, 9);
        GridPane.setColumnSpan(diagnosisLabel, 4);
        gridPane.getChildren().add(diagnosisLabel);

        TextField diagnosisField = new TextField();
        diagnosisField.setPromptText("Enter Diagnosis");
        GridPane.setColumnIndex(diagnosisField, 5);
        GridPane.setRowIndex(diagnosisField, 9);
        GridPane.setColumnSpan(diagnosisField, 4);
        gridPane.getChildren().add(diagnosisField);

        Label diagnosisDateLabel = new Label("Diagnosis Date:");
        diagnosisDateLabel.setFont(new Font(15));
        GridPane.setColumnIndex(diagnosisDateLabel, 1);
        GridPane.setRowIndex(diagnosisDateLabel, 10);
        GridPane.setColumnSpan(diagnosisDateLabel, 4);
        gridPane.getChildren().add(diagnosisDateLabel);

        TextField diagnosisDateField = new TextField();
        diagnosisDateField.setPromptText("Enter Diagnosis");
        GridPane.setColumnIndex(diagnosisDateField, 5);
        GridPane.setRowIndex(diagnosisDateField, 10);
        GridPane.setColumnSpan(diagnosisDateField, 4);
        gridPane.getChildren().add(diagnosisDateField);

        Label doctorLabel = new Label("Doctor in Charge:");
        doctorLabel.setFont(new Font(15));
        GridPane.setColumnIndex(doctorLabel, 1);
        GridPane.setRowIndex(doctorLabel, 11);
        GridPane.setColumnSpan(doctorLabel, 4);
        gridPane.getChildren().add(doctorLabel);

        TextField doctorField = new TextField();
        doctorField.setPromptText("Enter Doctor");
        GridPane.setColumnIndex(doctorField, 5);
        GridPane.setRowIndex(doctorField, 11);
        GridPane.setColumnSpan(doctorField, 4);
        gridPane.getChildren().add(doctorField);

        Label genderLabel = new Label("Gender");
        genderLabel.setFont(new Font(15));
        GridPane.setColumnIndex(genderLabel, 1);
        GridPane.setRowIndex(genderLabel, 12);
        GridPane.setColumnSpan(genderLabel, 4);
        gridPane.getChildren().add(genderLabel);

        TextField genderField = new TextField();
        genderField.setPromptText("Enter Gender");
        GridPane.setColumnIndex(genderField, 5);
        GridPane.setRowIndex(genderField, 12);
        GridPane.setColumnSpan(genderField, 4);
        gridPane.getChildren().add(genderField);
        // Submit Button
        Button submitButton = new Button("Save patient details");
        GridPane.setColumnIndex(submitButton, 5);
        GridPane.setRowIndex(submitButton, 13);
        GridPane.setColumnSpan(submitButton,10);
        gridPane.getChildren().add(submitButton);

        submitButton.setOnAction(event -> {
            DatabaseLookup dbhelper=new DatabaseLookup();
            dbhelper.addNewPatient(nameField.getText(),Integer.valueOf(ageField.getText()),genderField.getText(),
                    contactField.getText(), diagnosisDateField.getText(),diagnosisDateField.getText(),
                    doctorField.getText(),wardField.getText(),Integer.valueOf(roomField.getText()));
        });

    }

}