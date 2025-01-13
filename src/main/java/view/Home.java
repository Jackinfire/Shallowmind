package view;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import view.dashboard.PatientMonitorApp;
import view.editMenu.EditHomeMenu;

public class Home extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Root AnchorPane
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefSize(623, 423);
        anchorPane.setStyle("-fx-background-color: #051d40;");

        // GridPane
        GridPane gridPane = new GridPane();
        gridPane.setPrefSize(623, 423);
        AnchorPane.setTopAnchor(gridPane, 5.0);
        AnchorPane.setBottomAnchor(gridPane, 5.0);
        AnchorPane.setLeftAnchor(gridPane, 10.0);
        AnchorPane.setRightAnchor(gridPane, 10.0);

        // Column Constraints
        for (int i = 0; i < 2; i++) {
            ColumnConstraints colConstraints = new ColumnConstraints();
            colConstraints.setPercentWidth(50.0);
            gridPane.getColumnConstraints().add(colConstraints);
        }

// Row Constraints (2 rows, each taking 50% of the height)
        for (int i = 0; i < 2; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setPercentHeight(50.0);
            gridPane.getRowConstraints().add(rowConstraints);
        }

        ImageView logoView = new ImageView(new Image("file:src/main/resources/logo.png")); //
        logoView.setPreserveRatio(true);
        logoView.setFitHeight(100);  // Adjust as needed
        GridPane.setColumnIndex(logoView, 0);
        GridPane.setRowIndex(logoView, 0);
        GridPane.setHalignment(logoView, HPos.RIGHT);
        gridPane.getChildren().add(logoView);

        // Label
        Label titleLabel = new Label("Welcome to MOPPU \n Select your user:");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 18));
        titleLabel.setTextFill(javafx.scene.paint.Color.WHITE);
        GridPane.setColumnIndex(titleLabel, 1);
        GridPane.setRowIndex(titleLabel, 0);
        gridPane.getChildren().add(titleLabel);

        // Add Patient Button
        Button addPatientButton = new Button("Healthcare Professional View");
        addPatientButton.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 14));
        addPatientButton.setMaxSize(230, 190);
        GridPane.setHalignment(addPatientButton, HPos.CENTER);
        GridPane.setValignment(addPatientButton, VPos.CENTER);
        GridPane.setColumnIndex(addPatientButton, 0);
        GridPane.setRowIndex(addPatientButton, 1);
        gridPane.getChildren().add(addPatientButton);
        addPatientButton.setOnAction(e -> {
            // Action to perform when the button is clicked
            System.out.println("Healthcare View Clicked");
            openHealthcareView();
        });


        // Delete Patient Button
        Button deletePatientButton = new Button("Hospital Admin View");
        deletePatientButton.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 14));
        deletePatientButton.setMaxSize(230, 190);
        GridPane.setHalignment(deletePatientButton, HPos.CENTER);
        GridPane.setValignment(deletePatientButton, VPos.CENTER);
        GridPane.setColumnIndex(deletePatientButton, 1);
        GridPane.setRowIndex(deletePatientButton, 1);
        gridPane.getChildren().add(deletePatientButton);
        deletePatientButton.setOnAction(e -> {
            // Action to perform when the button is clicked
            System.out.println("Hospital Admin View Clicked");
            openAdminView();
        });


        // Adding GridPane to AnchorPane
        anchorPane.getChildren().add(gridPane);

        // Setting up the Scene
        Scene scene = new Scene(anchorPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Patient Editor");
        primaryStage.show();
    }

    private void openHealthcareView() {
        PatientMonitorApp healthcareView = new PatientMonitorApp();
        Stage patientMonitorAppStage = new Stage();
        healthcareView.start(patientMonitorAppStage);
    }

    private void openAdminView() {
        EditHomeMenu adminView = new EditHomeMenu();
        Stage patientEditorStage = new Stage();
        adminView.start(patientEditorStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
