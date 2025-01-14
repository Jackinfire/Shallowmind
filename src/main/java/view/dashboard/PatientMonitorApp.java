package view.dashboard;

import backend.Patient;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import view.utils.*;

import java.util.List;

/**
 * The PatientMonitorApp class serves as the main entry point for the patient monitoring dashboard.
 * It initializes the JavaFX application window, sets up the layout, and creates panels for monitoring
 * patients' posture, displaying alerts, and tracking patient statuses.
 */
public class PatientMonitorApp extends Application {

    private LiveMonitor liveMonitor; // Keep a reference to the LiveMonitor thread

    /**
     * The start method sets up the primary stage and initializes the user interface components.
     *
     * @param primaryStage The primary stage for the application.
     */
    @Override
    public void start(Stage primaryStage) {


        // Create VBox root as the main layout container
        VBox root = new VBox(20); // Spacing of 20 pixels between children

        // Set padding for the root container
        root.setPadding(new Insets(30, 100, 30, 100));

        // Set background color for window
        root.setStyle("-fx-background-color: #081c44;");

        // Get screen bounds and set application window dimensions
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        WindowDimensions.windowWidth = screenBounds.getWidth() * 0.9; // Set size to 90% of the screen bounds
        WindowDimensions.windowHeight = screenBounds.getHeight() * 0.9;

        // Create a scene with the specified root layout and dimensions
        Scene scene = new Scene(root, WindowDimensions.windowWidth, WindowDimensions.windowHeight);

        // Display screen
        primaryStage.setTitle("Patient Monitor");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Create box which contains alerts and status panels
        UpperBox upperPanelBox = new UpperBox();
        root.getChildren().addAll(upperPanelBox); // Add to scene
        VBox.setVgrow(upperPanelBox, Priority.ALWAYS);

        // Get StatusPanel and AlertPanel objects from upper panel
        StatusPanel statusPanel = upperPanelBox.getStatusPanel();
        AlertsPanel alertsPanel = upperPanelBox.getAlertsPanel();

        // Create panel containing patients and their details
        PatientsPanel patientsPanel = new PatientsPanel(statusPanel,alertsPanel);
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(patientsPanel);
        root.getChildren().addAll(vbox);
        VBox.setVgrow(patientsPanel, Priority.ALWAYS);



        liveMonitor = patientsPanel.getLiveMonitor();

        // Add close request listener
        primaryStage.setOnCloseRequest(event -> {
            if (liveMonitor != null) {
                liveMonitor.stopMonitor(); // Stop the thread
            }

        });

        primaryStage.show();
        }


}