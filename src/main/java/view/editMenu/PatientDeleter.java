package view.editMenu;

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

    }

}