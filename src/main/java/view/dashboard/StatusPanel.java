package view.dashboard;

import backend.DatabaseLookup;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * The StatusPanel class is responsible for displaying the status of patients
 * using a traffic light system. It shows the number of patients in green, amber, and red states.
 * This class extends the UpperPanel.
 */
public class StatusPanel extends UpperPanel {

    // Counters for patients in green, amber, and red states
    int greenPatients = 0;
    int amberPatients = 0;
    int redPatients = 0;

    // Boxes for the traffic light system
    VBox greenBox;
    VBox amberBox;
    VBox redBox;

    /**
     * Constructor for the StatusPanel class.
     * Initializes the traffic light system and sets up the panel layout.
     */
    public StatusPanel(){

        // Call the UpperPanel constructor with the appropriate string
        super("You currently have " + new DatabaseLookup().getNumberOfPatients() + " patients");

        // Create a horizontal box to hold the traffic light system
        HBox trafficLights = new HBox();
        trafficLights.setAlignment(Pos.CENTER);
        trafficLights.setSpacing(30); // Set spacing between the boxes
        trafficLights.setPadding(new Insets(10, 0, 0, 0)); // Set padding around the box

        // Create traffic light system boxes for green, amber, and red states
        this.greenBox = createStatusBox(greenPatients, "#328D40"); // Green box
        this.amberBox = createStatusBox(amberPatients, "#E67E22"); // Amber box
        this.redBox = createStatusBox(redPatients, "#C40003"); // Red box

        // Add the traffic light boxes to the horizontal box
        trafficLights.getChildren().addAll(greenBox, amberBox, redBox);

        // Add the traffic light system to the panel
        this.getChildren().addAll(trafficLights);

    }

    /**
     * Creates a status box with a given number and background color.
     *
     * @param number The number to display in the status box.
     * @param color  The background color of the status box.
     * @return A VBox representing the status box.
     */
    private VBox createStatusBox(Integer number, String color) {
        VBox box = new VBox();
        box.setStyle("-fx-background-color: " + color + "; -fx-background-radius: 10"); // Set background color and rounded corners

        // Create a label to display the number
        Label label = new Label(Integer.toString(number));
        label.setStyle("-fx-font-family: Arial; -fx-font-size: 30; -fx-text-fill: black; -fx-font-weight: bold");

        // Add the label to the box
        box.getChildren().addAll(label);

        // Set dimensions and alignment of the box
        box.setMaxSize(65, 65);
        box.setPrefSize(65, 65);
        box.setAlignment(Pos.CENTER);

        return box;
    }

    /**
     * Updates the count of green patients and refreshes the display.
     *
     * @param greenPatients The new count of green patients.
     */
    public void updateGreenCount(int greenPatients) {
        this.greenPatients = greenPatients;

        // Update the label in the green box
        Label label = (Label) this.greenBox.getChildren().get(0); // Get the first (and only) child
        label.setText(Integer.toString(greenPatients));
    }

    /**
     * Updates the count of amber patients and refreshes the display.
     *
     * @param amberPatients The new count of amber patients.
     */
    public void updateAmberCount(int amberPatients) {
        this.amberPatients = amberPatients;

        // Update the label in the amber box
        Label label = (Label) this.amberBox.getChildren().get(0); // Get the first (and only) child
        label.setText(Integer.toString(amberPatients));
    }

    /**
     * Updates the count of red patients and refreshes the display.
     *
     * @param redPatients The new count of red patients.
     */
    public void updateRedCount(int redPatients) {
        this.redPatients = redPatients;

        // Update the label in the red box
        Label label = (Label) this.redBox.getChildren().get(0); // Get the first (and only) child
        label.setText(Integer.toString(redPatients));
    }
}
