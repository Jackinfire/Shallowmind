package view.dashboard;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class AlertsPanel extends UpperPanel {
    public AlertsPanel(){
        super("Posture Monitoring Alerts");

        this.setSpacing(3);
        VBox redalert = createAlert("red","John Doe", "Ward A", "2 hours ago");
        VBox amberalert = createAlert("amber","John Doe", "Ward A", "2 hours ago");


        this.getChildren().addAll(redalert, amberalert);

    }

    private VBox createAlert(String severity, String name, String location, String time){
        VBox alert = new VBox();

        alert.setAlignment(Pos.CENTER);
        alert.setMaxSize(280,50);
        alert.setPrefSize(280,30);


        if (severity == "red")
            alert.setStyle("-fx-background-color: #C40003" + "; -fx-background-radius: 10");
        else
            alert.setStyle("-fx-background-color: #FFBF00" + "; -fx-background-radius: 10");
        return alert;


    }
}
