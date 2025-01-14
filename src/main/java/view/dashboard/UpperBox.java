package view.dashboard;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

/**
 * The UpperBox class is a horizontal container (HBox) that combines the StatusPanel
 * and AlertsPanel into a single layout for the top region of the application.
 */
public class UpperBox extends HBox {

    private StatusPanel statusPanel; // Panel to display status counts (green, amber, red).
    private AlertsPanel alertsPanel; // Panel to display alerts for patients.

    /**
     * Constructor for the UpperBox class.
     * Initializes and aligns the StatusPanel and AlertsPanel.
     */
    public UpperBox() {

        // Set alignment and spacing for the HBox.
        this.setAlignment(Pos.TOP_CENTER);
        this.setSpacing(20);

        // Initialize the StatusPanel and align it to the top center.
        this.statusPanel = new StatusPanel();
        statusPanel.setAlignment(Pos.TOP_CENTER);

        // Set a margin for the StatusPanel: top, right, bottom, left.
        this.setMargin(statusPanel, new Insets(20, 0, 0, 0));

        // Initialize the AlertsPanel and align it to the top center.
        alertsPanel = new AlertsPanel();
        alertsPanel.setAlignment(Pos.TOP_CENTER);

        // Set a margin for the AlertsPanel: top, right, bottom, left.
        this.setMargin(alertsPanel, new Insets(20, 0, 0, 0));

        // Add both panels to the HBox.
        this.getChildren().addAll(statusPanel, alertsPanel);
    }

    /**
     * Getter method for the StatusPanel.
     * Used by LiveMonitor to update status counts.
     *
     * @return The StatusPanel instance.
     */
    public StatusPanel getStatusPanel() {
        return this.statusPanel;
    }

    /**
     * Getter method for the AlertsPanel.
     * Used by LiveMonitor to display alerts.
     *
     * @return The AlertsPanel instance.
     */
    public AlertsPanel getAlertsPanel() {
        return this.alertsPanel;
    }
}
