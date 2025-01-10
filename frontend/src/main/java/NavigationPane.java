package Shallowmind.frontend.src.main.java;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class NavigationPane extends VBox {
    public NavigationPane() {

        // Create buttons
        Button overviewBtn = new Button("Overview");
        Button detailsBtn = new Button("Details");
        Button optionsBtn = new Button("Options");
        Button settingsBtn = new Button("Settings");

        // Set button sizes
        overviewBtn.setPrefSize(150,150);
        detailsBtn.setPrefSize(150,150);
        optionsBtn.setPrefSize(150,150);
        settingsBtn.setPrefSize(150,150);

        // Set button styles
        overviewBtn.setStyle("-fx-background-color: #FFFFFF;" +
                "-fx-font-family: Arial;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 16");
        detailsBtn.setStyle("-fx-background-color: #FFFFFF;" +
                "-fx-font-family: Arial;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 16");
        optionsBtn.setStyle("-fx-background-color: #FFFFFF;" +
                "-fx-font-family: Arial;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 16");
        settingsBtn.setStyle("-fx-background-color: #FFFFFF;" +
                "-fx-font-family: Arial;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 16");

        // Add buttons
        this.getChildren().addAll(overviewBtn, detailsBtn, optionsBtn, settingsBtn);

    }}
