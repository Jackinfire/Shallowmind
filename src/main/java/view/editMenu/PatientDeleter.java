package view.editMenu;

import backend.DatabaseLookup;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class PatientDeleter extends BasePatientForm {

    @Override
    public String getTitle() {
        return "Patient Deleter";
    }

    @Override
    public void addComponents(GridPane gridPane) {
        Label title = new Label("Patient Deleter");
        title.setFont(new Font(25));
        GridPane.setColumnIndex(title, 7);
        GridPane.setColumnSpan(title, 6);
        gridPane.getChildren().add(title);



        Label idLabel = new Label("Patient ID:");
        idLabel.setFont(new Font(15));
        GridPane.setColumnIndex(idLabel, 1);
        GridPane.setRowIndex(idLabel, 3);
        GridPane.setColumnSpan(idLabel, 4);
        gridPane.getChildren().add(idLabel);


        TextField idField = new TextField();
        idField.setPromptText("Enter ID");
        GridPane.setColumnIndex(idField, 4);
        GridPane.setRowIndex(idField, 3);
        GridPane.setColumnSpan(idField, 4);
        gridPane.getChildren().add(idField);

        // Submit Button
        Button submitButton = new Button("Delete patient details");
        GridPane.setColumnIndex(submitButton, 4);
        GridPane.setRowIndex(submitButton, 4);
        GridPane.setColumnSpan(submitButton,10);
        gridPane.getChildren().add(submitButton);

        submitButton.setOnAction(event -> {
            DatabaseLookup dbhelper=new DatabaseLookup();
            dbhelper.deletePatientData(Integer.valueOf(idField.getText()));
        });
    }

}