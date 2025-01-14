package view.dashboard;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import view.utils.WindowDimensions;

/**
 * The UpperPanel class represents a vertically oriented panel (VBox)
 * with a title, used as a container for displaying information with a specific style.
 */
public class UpperPanel extends VBox {

    private Label title; // Label to display the panel's title.

    /**
     * Constructor for the UpperPanel class.
     *
     * @param panelTitle The title to be displayed at the top of the panel.
     */
    public UpperPanel(String panelTitle) {

        // Set the background color and rounded corners for the panel.
        this.setStyle("-fx-background-color: linear-gradient(to right, #707070, #9a9a9a);" +
                "-fx-background-radius: 15;");

        // Set the fixed size of the panel.
        this.setMaxSize(WindowDimensions.windowWidth * 0.4, WindowDimensions.windowWidth * 0.1);
        this.setMinSize(WindowDimensions.windowWidth * 0.4, WindowDimensions.windowWidth * 0.1);

        // Create and customize the title label.
        title = new Label(panelTitle);
        title.setStyle("-fx-font-family: Arial;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 20;" +
                "-fx-text-fill: black;");

        // Set the alignment and padding for the title label.
        title.setAlignment(Pos.CENTER);
        title.setPadding(new Insets(10, 0, 0, 0));

        // Add the title label to the panel's children.
        this.getChildren().add(title);
    }
}
