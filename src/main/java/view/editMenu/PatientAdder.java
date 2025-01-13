package view.editMenu;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class PatientAdder extends Application {

    @Override
    public void start(Stage primaryStage) {
        // AnchorPane as root
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
        for (int i = 0; i < 14; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setPrefHeight(30);
            gridPane.getRowConstraints().add(rowConstraints);
        }

        // Adding children to GridPane
        Label title = new Label("Patient Adder");
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

        // Add GridPane to AnchorPane
        anchorPane.getChildren().add(gridPane);

        // Set Scene
        Scene scene = new Scene(anchorPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Patient Adder");
        primaryStage.show();
    }

}