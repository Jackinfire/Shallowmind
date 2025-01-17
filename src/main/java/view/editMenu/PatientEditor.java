package view.editMenu;

import backend.DatabaseLookup;
import backend.Patient;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;


import java.awt.*;


/**
 * The PatientEditor class extends BasePatientForm and provides the UI components
 * and functionality for editing existing patient data.
 */
public class PatientEditor extends BasePatientForm {


    @Override
    public String getTitle() {
        return "Patient Editor";
    }
    private DatabaseLookup dbhelper=new DatabaseLookup();
    private Patient patient;

    /**
     * Adds UI components to the GridPane for fetching and editing patient details.
     *
     * @param gridPane the GridPane to which components will be added.
     */
    @Override
    public void addComponents(GridPane gridPane) {
        // Title
        Label title = new Label("Patient Editor");
        title.setFont(new Font(25));
        GridPane.setColumnIndex(title, 7);
        GridPane.setColumnSpan(title, 6);
        gridPane.getChildren().add(title);

        // Patient ID - you enter the patient ID to fetch data
        Label idLabel = new Label("Patient ID:");
        idLabel.setFont(new Font(15));
        GridPane.setColumnIndex(idLabel, 1);
        GridPane.setRowIndex(idLabel, 3);
        GridPane.setColumnSpan(idLabel, 4);
        gridPane.getChildren().add(idLabel);
        TextField idField = new TextField();
        idField.setPromptText("Enter ID");
        GridPane.setColumnIndex(idField, 5);
        GridPane.setRowIndex(idField, 3);
        GridPane.setColumnSpan(idField, 5);
        gridPane.getChildren().add(idField);

        //Submit button to fetch patient data
        Button fetchButton = new Button("Search for patient");
        GridPane.setColumnIndex(fetchButton, 11);
        GridPane.setRowIndex(fetchButton, 3);
        GridPane.setColumnSpan(fetchButton,10);
        gridPane.getChildren().add(fetchButton);

        // To input patient name
        Label nameLabel = new Label("Patient Name:");
        nameLabel.setFont(new Font(15));
        GridPane.setColumnIndex(nameLabel, 1);
        GridPane.setRowIndex(nameLabel, 2);
        GridPane.setColumnSpan(nameLabel, 4);
        gridPane.getChildren().add(nameLabel);
        TextField nameField = new TextField();
        nameField.setPromptText("Name will appear here");
        nameField.setDisable(true);
        GridPane.setColumnIndex(nameField, 5);
        GridPane.setRowIndex(nameField, 2);
        GridPane.setColumnSpan(nameField, 5);
        gridPane.getChildren().add(nameField);

        // To input patient ward
        Label wardLabel = new Label("Ward Number:");
        wardLabel.setFont(new Font(15));
        GridPane.setColumnIndex(wardLabel, 1);
        GridPane.setRowIndex(wardLabel, 4);
        GridPane.setColumnSpan(wardLabel, 4);
        gridPane.getChildren().add(wardLabel);
        TextField wardField = new TextField();
        wardField.setPromptText("Ward will appear here");
        GridPane.setColumnIndex(wardField, 5);
        GridPane.setRowIndex(wardField, 4);
        GridPane.setColumnSpan(wardField, 5);
        gridPane.getChildren().add(wardField);

        // To input patient room number
        Label roomLabel = new Label("Room Number:");
        roomLabel.setFont(new Font(15));
        GridPane.setColumnIndex(roomLabel, 1);
        GridPane.setRowIndex(roomLabel, 5);
        GridPane.setColumnSpan(roomLabel, 4);
        gridPane.getChildren().add(roomLabel);
        TextField roomField = new TextField();
        roomField.setPromptText("Room number will appear here");
        GridPane.setColumnIndex(roomField, 5);
        GridPane.setRowIndex(roomField, 5);
        GridPane.setColumnSpan(roomField, 5);
        gridPane.getChildren().add(roomField);

        // To input patient age
        Label ageLabel = new Label("Patient Age:");
        ageLabel.setFont(new Font(15));
        GridPane.setColumnIndex(ageLabel, 1);
        GridPane.setRowIndex(ageLabel, 6);
        GridPane.setColumnSpan(ageLabel, 4);
        gridPane.getChildren().add(ageLabel);
        TextField ageField = new TextField();
        ageField.setPromptText("Age will appear here");
        GridPane.setColumnIndex(ageField, 5);
        GridPane.setRowIndex(ageField, 6);
        GridPane.setColumnSpan(ageField, 5);
        gridPane.getChildren().add(ageField);

        // To input patient emergency contact
        Label contactLabel = new Label("Emergency Contact:");
        contactLabel.setFont(new Font(15));
        GridPane.setColumnIndex(contactLabel, 1);
        GridPane.setRowIndex(contactLabel, 7);
        GridPane.setColumnSpan(contactLabel, 5);
        gridPane.getChildren().add(contactLabel);
        TextField contactField = new TextField();
        contactField.setPromptText("Emergency Contact number will appear here");
        GridPane.setColumnIndex(contactField, 5);
        GridPane.setRowIndex(contactField, 7);
        GridPane.setColumnSpan(contactField, 5);
        gridPane.getChildren().add(contactField);

        // To input patient diagnosis
        Label diagnosisLabel = new Label("Diagnosis:");
        diagnosisLabel.setFont(new Font(15));
        GridPane.setColumnIndex(diagnosisLabel, 1);
        GridPane.setRowIndex(diagnosisLabel, 8);
        GridPane.setColumnSpan(diagnosisLabel, 4);
        gridPane.getChildren().add(diagnosisLabel);
        TextField diagnosisField = new TextField();
        diagnosisField.setPromptText("Diagnosis will appear here");
        GridPane.setColumnIndex(diagnosisField, 5);
        GridPane.setRowIndex(diagnosisField, 8);
        GridPane.setColumnSpan(diagnosisField, 5);
        gridPane.getChildren().add(diagnosisField);

        // To input patient doctor
        Label doctorLabel = new Label("Doctor in Charge:");
        doctorLabel.setFont(new Font(15));
        GridPane.setColumnIndex(doctorLabel, 1);
        GridPane.setRowIndex(doctorLabel, 9);
        GridPane.setColumnSpan(doctorLabel, 4);
        gridPane.getChildren().add(doctorLabel);
        TextField doctorField = new TextField();
        doctorField.setPromptText("Doctor in charge will appear here");
        GridPane.setColumnIndex(doctorField, 5);
        GridPane.setRowIndex(doctorField, 9);
        GridPane.setColumnSpan(doctorField, 5);
        gridPane.getChildren().add(doctorField);

        // Fields get filled with patient information when search for patient button is pressed
        fetchButton.setOnAction(event -> {
            try {
                int id = Integer.parseInt(idField.getText());
                patient = new Patient(id); // Initialize patient object
                // fill the fields
                nameField.setDisable(false);
                nameField.setText(patient.getName());
                ageField.setDisable(false);
                ageField.setText(String.valueOf(patient.getAge()));
                roomField.setDisable(false);
                roomField.setText(String.valueOf(patient.getRoomNumber()));
                doctorField.setDisable(false);
                doctorField.setText(patient.getDocInCharge());
                diagnosisField.setDisable(false);
                diagnosisField.setText(patient.getDiagnosis());
                contactField.setDisable(false);
                contactField.setText(patient.getContactNumber());
                wardField.setDisable(false);
                wardField.setText(patient.getWard());
            } catch (Exception e) { // in the case the patient_id doesn't exist
                String numOfPatients=String.valueOf(dbhelper.getNumberOfPatients());
                idField.setText("Invalid ID: Enter from 1 to "+numOfPatients);
            }
        });

        // Submit Button
        Button submitButton = new Button("Save patient details");
        GridPane.setColumnIndex(submitButton, 5);
        GridPane.setRowIndex(submitButton, 10);
        GridPane.setColumnSpan(submitButton,10);
        gridPane.getChildren().add(submitButton);
        Label notificationLabel = new Label();
        notificationLabel.setFont(new Font(14));
        notificationLabel.setStyle("-fx-text-fill: red;");


        // Action on Submit Button
        // Pressing it will cause it to update the database only if the inputs are valid and appropriate. They
        // are handled as below
        submitButton.setOnAction(event -> {
            notificationLabel.setText("");
            if (patient != null) {
                try { // catching errors due to wrong types of data being inputted
                    String name = nameField.getText();
                    if (name.isEmpty()) throw new IllegalArgumentException("Name cannot be empty.");
                    String ward = wardField.getText();
                    if (ward.isEmpty()) throw new IllegalArgumentException("Ward cannot be empty.");
                    String roomText = roomField.getText();
                    int roomNumber = Integer.parseInt(roomText);
                    if (roomNumber <= 0) throw new IllegalArgumentException("Room number must be a positive integer.");
                    String ageText = ageField.getText();
                    int age = Integer.parseInt(ageText);
                    if (age <= 0) throw new IllegalArgumentException("Age must be a positive integer.");
                    String contactNumber = contactField.getText();
                    if (contactNumber.isEmpty()) throw new IllegalArgumentException("Contact number cannot be empty.");
                    String diagnosis = diagnosisField.getText();
                    if (diagnosis.isEmpty()) throw new IllegalArgumentException("Diagnosis cannot be empty.");
                    String doctor = doctorField.getText();
                    if (doctor.isEmpty()) throw new IllegalArgumentException("Doctor in charge cannot be empty.");
                    patient.setName(name);
                    patient.setWard(ward);
                    patient.setRoomNumber(roomNumber);
                    patient.setAge(age);
                    patient.setContactNumber(contactNumber);
                    patient.setDiagnosis(diagnosis);
                    patient.setDocInCharge(doctor);
                    notificationLabel.setText("Patient data saved successfully.");
                    notificationLabel.setStyle("-fx-text-fill: green;");

                    }catch (NumberFormatException e) { // when the input is not an int
                    notificationLabel.setText("Room, phone number and/or age must be a number");
                    }catch(IllegalArgumentException e){ // when the input is empty or if its not a positive int
                        notificationLabel.setText(e.getMessage());
                    }
                } else {
                notificationLabel.setText("No patient data to save. Fetch the patient first.");
            }
                if (!gridPane.getChildren().contains(notificationLabel)) { // add a notification
                    gridPane.add(notificationLabel, 5, 12);
                    GridPane.setColumnSpan(notificationLabel, 10);
                }
                // notification lasts for 5 seconds
                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), e -> {
                    gridPane.getChildren().remove(notificationLabel); // Remove the notification label
                }));
                timeline.setCycleCount(1);
                timeline.play();
        });

    }
}