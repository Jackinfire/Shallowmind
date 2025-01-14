package view.dashboard;

import backend.Patient;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import view.utils.WindowDimensions;

/**
 * The AlertsPanel class is responsible for displaying posture monitoring alerts in the application.
 * Alerts are displayed in a scrollable panel and are categorized by their severity (amber or red).
 */
public class AlertsPanel extends UpperPanel {

    private VBox alertsContainer; // Container to hold all the alerts

    /**
     * Constructor for AlertsPanel.
     * Initializes the layout, styling, and scrollable container for displaying alerts.
     */
    public AlertsPanel() {
        super("Posture Monitoring Alerts"); // Call the parent constructor with the panel title

        this.setSpacing(3); // Set spacing between the components of the panel

        // Initialize the VBox container for alerts
        alertsContainer = new VBox();
        alertsContainer.setSpacing(5); // Set spacing between individual alerts
        alertsContainer.setAlignment(Pos.CENTER); // Center align the alerts
        alertsContainer.setStyle("-fx-background-color: transparent;"); // Set transparent background

        // Initialize the scroll pane to hold the alerts container
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setMaxSize(WindowDimensions.windowWidth * 0.4 - 40, WindowDimensions.windowWidth * 0.1 - 40); // Set maximum size
        scrollPane.setMinSize(WindowDimensions.windowWidth * 0.4 - 40, WindowDimensions.windowWidth * 0.1 - 40); // Set minimum size
        scrollPane.setStyle("-fx-background: transparent; -fx-background-color: transparent;"); // Transparent background and border

        this.getChildren().add(scrollPane); // Add the scroll pane to the main panel
        scrollPane.setFitToWidth(true); // Ensure the content fits the width of the scroll pane

        scrollPane.setContent(alertsContainer); // Set the VBox as the content of the scroll pane
    }

    /**
     * Creates and adds an alert to the alerts container.
     * Alerts are displayed with details about the patient and are styled according to their severity.
     *
     * @param alertColor The severity of the alert ("amber" or "red").
     * @param patient    The patient associated with the alert.
     */
    public void createAlert(String alertColor, Patient patient) {
        VBox alert = new VBox(); // Create a new VBox to represent an individual alert

        alert.setAlignment(Pos.CENTER); // Center align the alert content
        alert.setMaxSize(280, 50); // Set maximum size of the alert box
        alert.setPrefSize(280, 30); // Set preferred size of the alert box

        // Create a label with the patient's name and location details
        Label label = new Label(patient.getName() + " " + "Ward " + patient.getWard() + ", Room " + patient.getRoomNumber());
        label.setStyle("-fx-text-fill: black"); // Set the label text color to black
        alert.getChildren().add(label); // Add the label to the alert box

        // Style the alert box and add it to the container based on the alert severity
        switch (alertColor) {
            case "amber":
                // Style for amber alerts
                alert.setStyle("-fx-background-color: #E67E22" + "; -fx-background-radius: 10;" +
                        "-fx-font-family: Arial;" + "-fx-font-size: 15;" + "-fx-font-weight: bold");
                alertsContainer.getChildren().add(alert); // Add the alert to the container
                break;
            case "red":
                // Style for red alerts
                alert.setStyle("-fx-background-color: #C40003" + "; -fx-background-radius: 10;" +
                        "-fx-font-family: Arial;" + "-fx-font-size: 15;" + "-fx-font-weight: bold");
                alertsContainer.getChildren().add(0, alert); // Add the alert at the top of the container
                break;
            default:
                // Handle unknown alert colors
                System.err.println("Unknown alert color: " + alertColor);
        }
    }

    /**
     * Clears all alerts from the alerts container.
     */
    public void clearAlerts() {
        alertsContainer.getChildren().clear(); // Remove all child elements from the VBox
    }
}
