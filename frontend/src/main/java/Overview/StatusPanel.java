package Overview;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;

public class StatusPanel extends UpperPanel{
    public StatusPanel(){
        super("You currently have 0 patients");

        // Create horizontal box to put traffic light system in
        HBox trafficLights = new HBox();
        trafficLights.setAlignment(Pos.CENTER);
        trafficLights.setSpacing(30);
        trafficLights.setPadding(new Insets(10,0,0,0));

        // Create traffic light system boxes
        VBox greenBox = createStatusBox(0,"Good Condition","#328D40");
        VBox amberBox = createStatusBox(0,"Good Condition","#FFBF00");
        VBox redBox = createStatusBox(0,"Good Condition","#C40003");

        trafficLights.getChildren().addAll(greenBox,amberBox,redBox);
        this.getChildren().addAll(trafficLights);

    }

    // Method to create a status box
    private VBox createStatusBox(Integer number, String description, String color) {
        VBox box = new VBox();
        box.setStyle("-fx-background-color: " + color + "; -fx-background-radius: 10");
        box.setMaxSize(50,50);
        box.setPrefSize(50,50);
        box.setAlignment(Pos.CENTER);
        return box;

    }
}
