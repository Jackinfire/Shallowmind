package view.dashboard;

import backend.Patient;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import view.utils.WindowDimensions;

public class AlertsPanel extends UpperPanel {

    private VBox alertsContainer; // Container to hold alerts

    public AlertsPanel(){
        super("Posture Monitoring Alerts");

        this.setSpacing(3);


        // Initialize the VBox to hold alerts
        alertsContainer = new VBox();
        alertsContainer.setSpacing(5); // Spacing between alerts
        alertsContainer.setAlignment(Pos.CENTER);
        alertsContainer.setStyle("-fx-background-color: transparent;" );

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setMaxSize(WindowDimensions.windowWidth*0.4 - 40,WindowDimensions.windowWidth*0.1 - 40);
        scrollPane.setMinSize(WindowDimensions.windowWidth*0.4 - 40,WindowDimensions.windowWidth*0.1 - 40);
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;");


        this.getChildren().add(scrollPane);
        scrollPane.setFitToWidth(true);

        scrollPane.setContent(alertsContainer);
    }

    public void createAlert(String alertColor, Patient patient){
        VBox alert = new VBox();

        alert.setAlignment(Pos.CENTER);
        alert.setMaxSize(280,50);
        alert.setPrefSize(280,30);

        // Create label for alert containing patient name and location
        Label label = new Label(patient.getName() + " " + "Ward " + patient.getWard() + ", Room " + patient.getRoomNumber());
        label.setStyle("-fx-text-fill: black"); // Make text black
        alert.getChildren().add(label);


        switch (alertColor) {
            case "amber":
                alert.setStyle("-fx-background-color: #E67E22" + "; -fx-background-radius: 10;" +
                        "-fx-font-family: Arial;" + "-fx-font-size: 15;" + "-fx-font-weight: bold") ;
                alertsContainer.getChildren().add(alert);
                break;
            case "red":
                alert.setStyle("-fx-background-color: #C40003" + "; -fx-background-radius: 10;" +
                        "-fx-font-family: Arial;" + "-fx-font-size: 15;" + "-fx-font-weight: bold");
                alertsContainer.getChildren().add(0, alert); // Insert at index 0
                break;
            default:
                System.err.println("Unknown alert color: " + alertColor);
        }

    }

    public void clearAlerts(){
        alertsContainer.getChildren().clear();
    }
}
