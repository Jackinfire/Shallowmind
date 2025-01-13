package view.dashboard;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class UpperBox extends HBox {

    private StatusPanel statusPanel;
    private AlertsPanel alertsPanel;

    public UpperBox(){

        this.setAlignment(Pos.TOP_CENTER);
        this.setSpacing(20);

        // Add Status panel to top region
        this.statusPanel = new StatusPanel();
        statusPanel.setAlignment(Pos.TOP_CENTER);
        this.setMargin(statusPanel, new Insets(20, 0, 0, 0)); // Margin: top, right, bottom, left

        // Add Alerts panel to top region
        alertsPanel = new AlertsPanel();
        alertsPanel.setAlignment(Pos.TOP_CENTER);
        this.setMargin(alertsPanel, new Insets(20, 0, 0, 0)); // Margin: top, right, bottom, left

        this.getChildren().addAll(statusPanel, alertsPanel);

    }

    // Getter for statusPanel, used by LiveMonitor
    public StatusPanel getStatusPanel(){
        return this.statusPanel;
    }

    public AlertsPanel getAlertsPanel(){
        return this.alertsPanel;
    }

}
