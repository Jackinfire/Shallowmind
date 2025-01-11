package Overview;

import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import backend.*;



public class PatientsPanel extends VBox {

    public PatientsPanel(){
        this.setStyle("-fx-background-color: #33516d;" +
                "-fx-background-radius: 15;");


        this.setPrefSize(1200,430);

        Patient john = new Patient(00, "John Doe","/johndoe.jfif",30,"Male","n/a","n/a","","3","01234","Dr. Adams","Pepsin","n/a","n/a","n/a",false) ;

        PatientBox patientBox1 = new PatientBox(john);
        this.getChildren().addAll(patientBox1);
        this.setPadding(new Insets(20,20,20,20));

    }

    // Each hbox should contain two patient boxes

//    private HBox createPatientHBox(){


}
