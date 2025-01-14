package view.editMenu;

import backend.DatabaseLookup;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class EditHomeMenu extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Root AnchorPane
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefSize(623, 423);

        // GridPane
        GridPane gridPane = new GridPane();
        gridPane.setPrefSize(623, 423);
        AnchorPane.setTopAnchor(gridPane, 5.0);
        AnchorPane.setBottomAnchor(gridPane, 5.0);
        AnchorPane.setLeftAnchor(gridPane, 10.0);
        AnchorPane.setRightAnchor(gridPane, 10.0);

        // Column Constraints
        for (int i = 0; i < 18; i++) {
            ColumnConstraints colConstraints = new ColumnConstraints();
            colConstraints.setPrefWidth(100);
            gridPane.getColumnConstraints().add(colConstraints);
        }

        // Row Constraints
        for (int i = 0; i < 11; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setPrefHeight(30);
            gridPane.getRowConstraints().add(rowConstraints);
        }

        // Adding children to GridPane

        // Label
        Label titleLabel = new Label("Patient Editor");
        titleLabel.setPrefSize(144, 30);
        titleLabel.setFont(new Font(25));
        GridPane.setColumnIndex(titleLabel, 7);
        GridPane.setColumnSpan(titleLabel, 6);
        gridPane.getChildren().add(titleLabel);


        // Edit Patient Button
        Button editPatientButton = new Button("Edit Patient");
        editPatientButton.setFont(new Font(15));
        GridPane.setRowIndex(editPatientButton, 3);
        GridPane.setColumnSpan(editPatientButton, 4);
        gridPane.getChildren().add(editPatientButton);
        editPatientButton.setOnAction(e -> {
            // Action to perform when the button is clicked
            System.out.println("Edit patient button clicked");
            openPatientEditor();
        });

        // Adding GridPane to AnchorPane
        anchorPane.getChildren().add(gridPane);

        // Setting up the Scene
        Scene scene = new Scene(anchorPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Patient Editor");
        primaryStage.show();
    }


    private void openPatientEditor() {
        PatientEditor patientEditor = new PatientEditor();
        Stage patientEditorStage = new Stage();
        patientEditor.start(patientEditorStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
