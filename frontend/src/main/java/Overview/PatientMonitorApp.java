package Overview;

import javafx.application.*;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.geometry.*;


public class PatientMonitorApp extends Application {
    @Override
    public void start(Stage primaryStage) {

        // Create VBox root
        VBox root = new VBox(20);
        root.setPadding(new Insets(30,100,30,100));

        // Create HBox which will contain upper panels
        HBox upperPanels = new HBox(20);
        upperPanels.setAlignment(Pos.TOP_CENTER);
        root.getChildren().addAll(upperPanels); // Add to root


        // Set background color
        root.setStyle("-fx-background-color: #081c44;");

        // Get screen bounds
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        // Set screen size
        double windowWidth = screenBounds.getWidth() * 0.8; // 80% of screen width
        double windowHeight = screenBounds.getHeight() * 0.8; // 80% of screen height
        Scene scene = new Scene(root,windowWidth,windowHeight);

        // Display screen
        primaryStage.setTitle("Patient Monitor");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Add Status panel to top region
        StatusPanel statusPanel = new StatusPanel();
        statusPanel.setAlignment(Pos.TOP_CENTER);
        statusPanel.setMaxSize(600,110);
        root.setMargin(statusPanel, new Insets(20, 0, 0, 100)); // Margin: top, right, bottom, left

        // Add Alerts panel to top region
        AlertsPanel alertsPanel = new AlertsPanel();
        alertsPanel.setAlignment(Pos.TOP_CENTER);
        alertsPanel.setMaxSize(600,110);
        root.setMargin(alertsPanel, new Insets(20, 0, 0, 100)); // Margin: top, right, bottom, left



        // Add status panel and alerts panel to upper HBox
        upperPanels.getChildren().addAll(statusPanel, alertsPanel);

        PatientsPanel patientspanel = new PatientsPanel();
        root.getChildren().addAll(patientspanel);



    }
    public static void main(String[] args) {
        launch(args);
    }
}