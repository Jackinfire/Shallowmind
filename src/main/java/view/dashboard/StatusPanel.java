package view.dashboard;

import backend.DatabaseLookup;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class StatusPanel extends UpperPanel {

    int greenPatients = 10;
    int amberPatients = 0;
    int redPatients = 0;
    VBox greenBox;
    VBox amberBox;
    VBox redBox;

    public StatusPanel(){

        // Call the UpperPanel constructor with the appropriate string
        super("You currently have " + new DatabaseLookup().getNumberOfPatients() + " patients");
        // Create horizontal box to put traffic light system in
        HBox trafficLights = new HBox();
        trafficLights.setAlignment(Pos.CENTER);
        trafficLights.setSpacing(30);
        trafficLights.setPadding(new Insets(10,0,0,0));

        // Create traffic light system boxes
        this.greenBox = createStatusBox(greenPatients,"#328D40");
        this.amberBox = createStatusBox(amberPatients,"#E67E22");
        this.redBox = createStatusBox(redPatients,"#C40003");

        trafficLights.getChildren().addAll(greenBox,amberBox,redBox);
        this.getChildren().addAll(trafficLights);

    }

    // Method to create a status box
    private VBox createStatusBox(Integer number, String color) {
        VBox box = new VBox();
        box.setStyle("-fx-background-color: " + color + "; -fx-background-radius: 10");
        Label label = new Label(Integer.toString(number));
        label.setStyle("-fx-font-family: Arial; -fx-font-size: 20; -fx-text-fill: black; -fx-font-weight: bold");
        box.getChildren().addAll(label);
        box.setMaxSize(50,50);
        box.setPrefSize(50,50);
        box.setAlignment(Pos.CENTER);
        return box;
    }

    public void updateGreenCount(int greenPatients){
        this.greenPatients = greenPatients;
        Label label = (Label) this.greenBox.getChildren().get(0); // Get the first (and only) child
        label.setText(Integer.toString(greenPatients));
        System.out.println("green updated");
    }

    public void updateAmberCount(int amberPatients){
        this.amberPatients = amberPatients;
        Label label = (Label) this.amberBox.getChildren().get(0); // Get the first (and only) child
        label.setText(Integer.toString(amberPatients));
    }

    public void updateRedCount(int redPatients){
        this.redPatients = redPatients;
        Label label = (Label) this.redBox.getChildren().get(0); // Get the first (and only) child
        label.setText(Integer.toString(redPatients));
    }
}
