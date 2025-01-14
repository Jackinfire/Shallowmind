package view.dashboard;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import view.utils.*;

/**
 * The PatientMonitorApp class serves as the main entry point for the patient monitoring dashboard.
 * It initializes the JavaFX application window, sets up the layout, and creates panels for monitoring
 * patients' posture, displaying alerts, and tracking patient statuses.
 */
public class PatientMonitorApp extends Application {

    private LiveMonitor liveMonitor; // Reference to the LiveMonitor thread for managing patient updates.

    /**
     * The start method sets up the primary stage and initializes the user interface components.
     *
     * @param primaryStage The primary stage for the application.
     */
    @Override
    public void start(Stage primaryStage) {
        // Create VBox root as the main layout container.
        VBox root = new VBox(20); // Spacing of 20 pixels between children.

        // Set padding for the root container.
        root.setPadding(new Insets(30, 100, 30, 100));

        // Set background color of the application window.
        root.setStyle("-fx-background-color: linear-gradient(to right, #081c44, #1b294b);;");

        // Get dimensions of the device screen.
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        // Set the application window dimensions to 90% of the screen size.
        WindowDimensions.windowWidth = screenBounds.getWidth() * 0.9;
        WindowDimensions.windowHeight = screenBounds.getHeight() * 0.9;

        // Create the main scene with the specified root layout and dimensions.
        Scene scene = new Scene(root, WindowDimensions.windowWidth, WindowDimensions.windowHeight);

        // Set the title of the application window and display the scene.
        primaryStage.setTitle("Patient Monitor");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Create the upper box layout containing the status panel and alerts panel.
        UpperBox upperPanelBox = new UpperBox();
        root.getChildren().addAll(upperPanelBox); // Add the upper box to the root layout.
        VBox.setVgrow(upperPanelBox, Priority.ALWAYS); // Allow the upper box to grow with the window.

        // Extract the StatusPanel and AlertsPanel objects from the upper panel.
        StatusPanel statusPanel = upperPanelBox.getStatusPanel();
        AlertsPanel alertsPanel = upperPanelBox.getAlertsPanel();

        // Create the patient panel layout for displaying individual patient information and statuses.
        PatientsPanel patientsPanel = new PatientsPanel(statusPanel,alertsPanel);

        // Create a VBox container for the patients panel and align it to the center.
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);

        // Add the patients panel to the root layout and allow it to grow with the window.
        vbox.getChildren().addAll(patientsPanel);
        root.getChildren().addAll(vbox);
        VBox.setVgrow(patientsPanel, Priority.ALWAYS);

        // Retrieve the LiveMonitor instance from the PatientsPanel.
        liveMonitor = patientsPanel.getLiveMonitor();

        // Add a close request listener to terminate LiveMonitor thread when the application is closed.
        primaryStage.setOnCloseRequest(event -> {
            if (liveMonitor != null) {
                liveMonitor.stopMonitor(); // Stop the LiveMonitor thread.
            }
        });

        // Display the application window.
        primaryStage.show();
    }
}