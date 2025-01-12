package view.dashboard;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class UpperBox extends HBox {

    public UpperBox(){

        this.setAlignment(Pos.TOP_CENTER);
        this.setSpacing(20);

        // Add Status panel to top region
        StatusPanel statusPanel = new StatusPanel();
        statusPanel.setAlignment(Pos.TOP_CENTER);
        this.setMargin(statusPanel, new Insets(20, 0, 0, 0)); // Margin: top, right, bottom, left

        // Add Alerts panel to top region
        AlertsPanel alertsPanel = new AlertsPanel();
        alertsPanel.setAlignment(Pos.TOP_CENTER);
        this.setMargin(alertsPanel, new Insets(20, 0, 0, 0)); // Margin: top, right, bottom, left

        this.getChildren().addAll(statusPanel, alertsPanel);

    }
}
