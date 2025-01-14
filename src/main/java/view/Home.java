package view;

import javafx.application.Application;
import javafx.application.Platform;
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
import view.editMenu.PatientEditor;

/**
 * The Home class extends Application and provides the UI components
 * and functionality for choosing which view to use the app.
 */
public class Home extends Application {
    private Button healthcareProfessionalButton; // Button for Healthcare Professional view
    private Button hospitalAdminButton; // Button for Hospital Admin view

    /**
     * The start method sets up the primary stage and initializes the user interface components.
     *
     * @param primaryStage The primary stage for the application.
     */
    @Override
    public void start(Stage primaryStage) {
        // Root AnchorPane
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefSize(623, 423); // Set preferred size for the anchor pane
        anchorPane.setStyle("-fx-background-color: #051d40;"); // Set background color for the anchor pane

        // Create a GridPane for layout
        GridPane gridPane = new GridPane();
        gridPane.setPrefSize(623, 423); // Set preferred size for the grid pane
        // Set anchor positions for the grid pane within the anchor pane
        AnchorPane.setTopAnchor(gridPane, 5.0);
        AnchorPane.setBottomAnchor(gridPane, 5.0);
        AnchorPane.setLeftAnchor(gridPane, 10.0);
        AnchorPane.setRightAnchor(gridPane, 10.0);

        // Divide the GridPane into 2 columns and 2 rows
        for (int i = 0; i < 2; i++) {
            ColumnConstraints colConstraints = new ColumnConstraints();
            colConstraints.setPercentWidth(50.0); // Each column takes up 50% of the width
            gridPane.getColumnConstraints().add(colConstraints);
        }

        for (int i = 0; i < 2; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setPercentHeight(50.0); // Each row takes up 50% of the height
            gridPane.getRowConstraints().add(rowConstraints);
        }

        // Add the logo to the top-left corner of the GridPane
        ImageView logoView = new ImageView(new Image("file:src/main/resources/logo.png"));
        logoView.setPreserveRatio(true); // Preserve the aspect ratio of the image
        logoView.setFitHeight(100); // Set the height of the logo
        logoView.setTranslateX(-50);  // Translate 50 pixels to the left
        GridPane.setColumnIndex(logoView, 0); // Place in column 0
        GridPane.setRowIndex(logoView, 0); // Place in row 0
        GridPane.setHalignment(logoView, HPos.RIGHT); // Align the logo to the right
        gridPane.getChildren().add(logoView);

        // Add a title to the top-right corner of the GridPane
        Label titleLabel = new Label("Welcome to MOPPU! \nPlease select your user:");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 18)); // Set font and size
        titleLabel.setTextFill(javafx.scene.paint.Color.WHITE); // Set text color
        titleLabel.setTranslateX(-50);  // Translate 50 pixels to the left
        GridPane.setColumnIndex(titleLabel, 1); // Place in column 1
        GridPane.setRowIndex(titleLabel, 0); // Place in row 0
        gridPane.getChildren().add(titleLabel);

        // Add Healthcare Professional button to the bottom-left corner
        healthcareProfessionalButton = new Button("Healthcare Professional");
        healthcareProfessionalButton.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 14)); // Set font and size
        healthcareProfessionalButton.setMaxSize(230, 190); // Set maximum size
        healthcareProfessionalButton.setStyle("-fx-background-radius: 15;"); // Add rounded corners
        healthcareProfessionalButton.setTranslateY(-50);  // Translate 50 pixels up
        GridPane.setHalignment(healthcareProfessionalButton, HPos.CENTER); // Center horizontally
        GridPane.setValignment(healthcareProfessionalButton, VPos.CENTER); // Center vertically
        GridPane.setColumnIndex(healthcareProfessionalButton, 0); // Place in column 0
        GridPane.setRowIndex(healthcareProfessionalButton, 1); // Place in row 1
        gridPane.getChildren().add(healthcareProfessionalButton);
        healthcareProfessionalButton.setOnAction(e -> {
            // Open Healthcare Professional view when clicked
            openHealthcareProfessionalView();
        });

        // Add Hospital Admin button to the bottom-right corner
        hospitalAdminButton = new Button("Hospital Admin");
        hospitalAdminButton.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 14)); // Set font and size
        hospitalAdminButton.setMaxSize(230, 190); // Set maximum size
        hospitalAdminButton.setStyle("-fx-background-radius: 15;"); // Add rounded corners
        hospitalAdminButton.setTranslateY(-50);  // Translate 50 pixels up
        GridPane.setHalignment(hospitalAdminButton, HPos.CENTER); // Center horizontally
        GridPane.setValignment(hospitalAdminButton, VPos.CENTER); // Center vertically
        GridPane.setColumnIndex(hospitalAdminButton, 1); // Place in column 1
        GridPane.setRowIndex(hospitalAdminButton, 1); // Place in row 1
        gridPane.getChildren().add(hospitalAdminButton);
        hospitalAdminButton.setOnAction(e -> {
            // Open Hospital Admin view when clicked
            openAdminView();
        });

        // Add the GridPane to the AnchorPane
        anchorPane.getChildren().add(gridPane);

        // Set up the Scene
        Scene scene = new Scene(anchorPane);
        primaryStage.setScene(scene); // Set the scene for the primary stage
        primaryStage.setTitle("MOPPU"); // Set the title of the window
        primaryStage.show(); // Show the primary stage

        // Handle close request for the application
        primaryStage.setOnCloseRequest(event -> {
            Platform.exit(); // Shut down JavaFX runtime
            System.exit(0); // Force JVM shutdown to ensure all threads have stopped
        });
    }

    /**
     * Opens the Healthcare Professional View by launching the Patient Monitor App.
     */
    private void openHealthcareProfessionalView() {
        healthcareProfessionalButton.setDisable(true); // Disable buttons to prevent multiple instances
        hospitalAdminButton.setDisable(true);
        PatientMonitorApp healthcareProfessionalView = new PatientMonitorApp(); // Create a new PatientMonitorApp instance
        Stage healthcareProfessionalViewStage = new Stage(); // Create a new stage for the view
        healthcareProfessionalView.start(healthcareProfessionalViewStage); // Start the view
        healthcareProfessionalViewStage.setOnCloseRequest(event -> {
            // Re-enable buttons when the view is closed
            healthcareProfessionalButton.setDisable(false);
            hospitalAdminButton.setDisable(false);
        });
    }

    /**
     * Opens the Hospital Admin View by launching the Patient Editor.
     */
    private void openAdminView() {
        healthcareProfessionalButton.setDisable(true); // Disable buttons to prevent multiple instances
        hospitalAdminButton.setDisable(true);
        PatientEditor patientEditor = new PatientEditor(); // Create a new PatientEditor instance
        Stage patientEditorStage = new Stage(); // Create a new stage for the editor
        patientEditor.start(patientEditorStage); // Start the editor
        patientEditorStage.setOnCloseRequest(event -> {
            // Re-enable buttons when the editor is closed
            healthcareProfessionalButton.setDisable(false);
            hospitalAdminButton.setDisable(false);
        });
    }

    /**
     * The main entry point of the application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        launch(args); // Launch the JavaFX application
    }
}
