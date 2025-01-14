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
import view.editMenu.PatientEditor;

public class Home extends Application {
    private Button healthcareProfessionalButton;
    private Button hospitalAdminButton;

    @Override
    public void start(Stage primaryStage) {
        // Root AnchorPane
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefSize(623, 423);
        anchorPane.setStyle("-fx-background-color: #051d40;");
        GridPane gridPane = new GridPane();
        gridPane.setPrefSize(623, 423);
        AnchorPane.setTopAnchor(gridPane, 5.0);
        AnchorPane.setBottomAnchor(gridPane, 5.0);
        AnchorPane.setLeftAnchor(gridPane, 10.0);
        AnchorPane.setRightAnchor(gridPane, 10.0);

// Divide the pane into 4
        for (int i = 0; i < 2; i++) {
            ColumnConstraints colConstraints = new ColumnConstraints();
            colConstraints.setPercentWidth(50.0);
            gridPane.getColumnConstraints().add(colConstraints);
        }

        for (int i = 0; i < 2; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setPercentHeight(50.0);
            gridPane.getRowConstraints().add(rowConstraints);
        }

// Add shallowmind logo
        ImageView logoView = new ImageView(new Image("file:src/main/resources/logo.png"));
        logoView.setPreserveRatio(true);
        logoView.setFitHeight(100);  // Adjust as needed
        GridPane.setColumnIndex(logoView, 0);
        GridPane.setRowIndex(logoView, 0);
        GridPane.setHalignment(logoView, HPos.RIGHT);
        gridPane.getChildren().add(logoView);

// Title
        Label titleLabel = new Label("Welcome to MOPPU \n Select your user:");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 18));
        titleLabel.setTextFill(javafx.scene.paint.Color.WHITE);
        GridPane.setColumnIndex(titleLabel, 1);
        GridPane.setRowIndex(titleLabel, 0);
        gridPane.getChildren().add(titleLabel);

// Add healthcareProfessional Button
        healthcareProfessionalButton = new Button("Healthcare Professional View");
        healthcareProfessionalButton.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 14));
        healthcareProfessionalButton.setMaxSize(230, 190);
        GridPane.setHalignment(healthcareProfessionalButton, HPos.CENTER);
        GridPane.setValignment(healthcareProfessionalButton, VPos.CENTER);
        GridPane.setColumnIndex(healthcareProfessionalButton, 0);
        GridPane.setRowIndex(healthcareProfessionalButton, 1);
        gridPane.getChildren().add(healthcareProfessionalButton);
        healthcareProfessionalButton.setOnAction(e -> {
            // Action to perform when the button is clicked
            System.out.println("Healthcare View Clicked");
            openHealthcareProfessionalView();
        });


// add hospitalAdmin Button
        hospitalAdminButton = new Button("Hospital Admin View");
        hospitalAdminButton.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 14));
        hospitalAdminButton.setMaxSize(230, 190);
        GridPane.setHalignment(hospitalAdminButton, HPos.CENTER);
        GridPane.setValignment(hospitalAdminButton, VPos.CENTER);
        GridPane.setColumnIndex(hospitalAdminButton, 1);
        GridPane.setRowIndex(hospitalAdminButton, 1);
        gridPane.getChildren().add(hospitalAdminButton);
        hospitalAdminButton.setOnAction(e -> {
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

    private void openHealthcareProfessionalView() {
        healthcareProfessionalButton.setDisable(true);
        hospitalAdminButton.setDisable(true);
        PatientMonitorApp healthcareProfessionalView = new PatientMonitorApp();
        Stage healthcareProfessionalViewStage = new Stage();
        healthcareProfessionalView.start(healthcareProfessionalViewStage);
        healthcareProfessionalViewStage.setOnCloseRequest(event -> {
            healthcareProfessionalButton.setDisable(false);
            hospitalAdminButton.setDisable(false);
        });
    }


    private void openAdminView() {
        healthcareProfessionalButton.setDisable(true);
        hospitalAdminButton.setDisable(true);
        PatientEditor patientEditor = new PatientEditor();
        Stage patientEditorStage = new Stage();
        patientEditor.start(patientEditorStage);
        patientEditorStage.setOnCloseRequest(event -> {
            healthcareProfessionalButton.setDisable(false);
            hospitalAdminButton.setDisable(false);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
    }