package view.dashboard;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import view.utils.*;


public class PatientMonitorApp extends Application {
    @Override
    public void start(Stage primaryStage) {


        // Create VBox root
        VBox root = new VBox(20);

        // Set Padding
        root.setPadding(new Insets(30,100,30,100));

        // Set background color
        root.setStyle("-fx-background-color: #081c44;");


        // Get screen bounds and set screen size
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        WindowDimensions.windowWidth = screenBounds.getWidth() * 0.9;
        WindowDimensions.windowHeight = screenBounds.getHeight() * 0.9;
        Scene scene = new Scene(root,WindowDimensions.windowWidth,WindowDimensions.windowHeight); // Creates scene

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


    }
    public static void main(String[] args) {
        launch(args);
    }
}