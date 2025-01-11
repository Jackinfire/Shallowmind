package Overview;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import backend.*;


public class PatientBox extends HBox {

    private String alertColor = "#328D40"; // Class-level variable to store the color, default is green

    public PatientBox(Patient patient){


        this.setStyle("-fx-background-color:" + alertColor +
                "; -fx-background-radius: 15;");

        this.setPrefSize(380,78);
        this.setMaxSize(380,78);
        this.setPadding(new Insets(20,20,20,20));

        ImageView patientImage = addPatientImage(patient);

        this.getChildren().addAll(patientImage);

    }

    // Fetches patient image and returns
    private ImageView addPatientImage(Patient patient){
        ImageView imageView = new ImageView();
        try {
            System.out.println("Attempting to load patient image: " + patient.getImagePath());
            // Load image from classpath
            Image image = new Image(getClass().getResourceAsStream(patient.getImagePath()));
            imageView.setImage(image); // Set patient image
        } catch (Exception e) {
            System.out.println("Failed to load image: " + patient.getImagePath() + ". Loading default image.");
            try {
                // Load default image from classpath
                Image defaultImage = new Image(getClass().getResourceAsStream("/default_pfp.jpg"));
                imageView.setImage(defaultImage); // Set default image
            } catch (Exception ex) {
                System.out.println("Failed to load default image: " + ex.getMessage());
            }
        }

        imageView.setFitWidth(65);  // Set the desired width
        imageView.setFitHeight(65); // Set the desired height
        imageView.setPreserveRatio(true); // Preserve the aspect ratio of the image



        return imageView;

    }

}



