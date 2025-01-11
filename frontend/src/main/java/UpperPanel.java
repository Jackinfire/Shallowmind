import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.control.Label;

public class UpperPanel extends VBox {
    private Label title;
    public UpperPanel(String panelTitle){


        // Set color of panel
        this.setStyle("-fx-background-color: linear-gradient(to right, #707070, #9a9a9a);" +
                "-fx-background-radius: 15;");

        // Set panel size
        this.setPrefSize(400,200);

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
