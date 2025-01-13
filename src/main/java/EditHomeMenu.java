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

        // Add Patient Button
        Button addPatientButton = new Button("Add Patient");
        addPatientButton.setPrefSize(111, 29);
        addPatientButton.setFont(new Font(15));
        GridPane.setRowIndex(addPatientButton, 1);
        GridPane.setColumnSpan(addPatientButton, 3);
        gridPane.getChildren().add(addPatientButton);
        addPatientButton.setOnAction(e -> {
            // Action to perform when the button is clicked
            System.out.println("Add patient button clicked");
            openPatientAdder();
        });


        // Delete Patient Button
        Button deletePatientButton = new Button("Delete Patient");
        deletePatientButton.setPrefSize(115, 29);
        deletePatientButton.setFont(new Font(15));
        GridPane.setRowIndex(deletePatientButton, 2);
        GridPane.setColumnSpan(deletePatientButton, 4);
        gridPane.getChildren().add(deletePatientButton);
        deletePatientButton.setOnAction(e -> {
            // Action to perform when the button is clicked
            System.out.println("Delete patient button clicked");
            openPatientDeleter();
        });

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

    private void openPatientAdder() {
        PatientEditor patientAdder = new PatientEditor();
        Stage patientAdderStage = new Stage();
        patientAdder.start(patientAdderStage);
    }

    private void openPatientEditor() {
        PatientEditor patientEditor = new PatientEditor();
        Stage patientEditorStage = new Stage();
        patientEditor.start(patientEditorStage);
    }

    public void openPatientDeleter(){
        PatientEditor patientDeleter = new PatientEditor();
        Stage patientDeleterStage = new Stage();
        patientDeleter.start(patientDeleterStage);
    }
    public static void main(String[] args) {
        launch(args);
    }
}
