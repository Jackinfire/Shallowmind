package Shallowmind.frontend.src.main.java;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;

public class UpperPanel extends HBox {
    private Label title;
    public UpperPanel(String panelTitle){


        // Set color of panel
        this.setStyle("-fx-background-color: #b0b4bd;" +
                "-fx-background-radius: 15;");

        // Set panel size
        this.setPrefSize(350,150);

        // Customise title
        title = new Label(panelTitle);
        title.setStyle("-fx-font-family: Arial;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 20;" +
                "-fx-text-fill: black;");

        title.setAlignment(Pos.CENTER);

        // Add title to panel
        this.getChildren().add(title);

    }
}
