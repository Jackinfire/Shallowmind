package Shallowmind.frontend.src.main.java;

import javafx.application.*;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.geometry.*;


public class PatientMonitorApp extends Application {
    @Override
    public void start(Stage primaryStage) {

        // Create borderpane root
        HBox root = new HBox();

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

        // Add Navigation Panel to the left region of the BorderPane
        NavigationPane navigationPane = new NavigationPane(); // Create instance of NavigationPane
        navigationPane.setAlignment(Pos.CENTER_LEFT); // Set NavigationPane as the left region

        // Add Status panel to top region
        StatusPanel statusPanel = new StatusPanel();
        statusPanel.setAlignment(Pos.TOP_CENTER);
        statusPanel.setMaxSize(350,100);



        // Add components
        root.getChildren().addAll(navigationPane, statusPanel);



    }}