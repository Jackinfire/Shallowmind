package view.dashboard;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import view.utils.WindowDimensions;


public class UpperPanel extends VBox {
    private Label title;
    public UpperPanel(String panelTitle){


        // Set color of panel
        this.setStyle("-fx-background-color: linear-gradient(to right, #707070, #9a9a9a);" +
                "-fx-background-radius: 15;");

        // Set fixed panel size
        this.setMaxSize(WindowDimensions.windowWidth*0.4,WindowDimensions.windowWidth*0.1);
        this.setMinSize(WindowDimensions.windowWidth*0.4,WindowDimensions.windowWidth*0.1);

        // Customise title
        title = new Label(panelTitle);
        title.setStyle("-fx-font-family: Arial;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 20;" +
                "-fx-text-fill: black;");

        // Set positioning of title
        title.setAlignment(Pos.CENTER);
        title.setPadding(new Insets(10,0,0,0));

        // Add title to panel
        this.getChildren().add(title);

    }
}
