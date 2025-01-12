package view.dashboard;

import backend.Patient;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import view.utils.WindowDimensions;
import java.io.ByteArrayInputStream;


public class PatientBox extends HBox {

    private String alertColor = "#328D40"; // Class-level variable to store the color, default is green

    public PatientBox(Patient patient){

        // Create a VBox for details
        VBox detailsBox = new VBox();

        this.setStyle("-fx-background-color:" + alertColor +
                "; -fx-background-radius: 15;");

        this.setMinSize(WindowDimensions.windowWidth* 0.4 -10,WindowDimensions.windowHeight * 0.125);
        this.setMaxSize(WindowDimensions.windowWidth* 0.4 - 10,WindowDimensions.windowHeight * 0.125);
        this.setPadding(new Insets(10,20,10,20));
        this.setSpacing(20);

        HBox imageAndDetailsBox = new HBox(20);
        // Add the patient image to the PatientBox
        ImageView patientImage = addPatientImage(patient);

        // Add label to display patient's name
        Label nameLabel = new Label(patient.getName());
        nameLabel.setStyle("-fx-font-family: Arial; -fx-font-size: 18; -fx-text-fill: black; -fx-font-weight: bold");

        // Add label to display patient's Location
        Label locationLabel = new Label("Ward Null, "+ "Room " + patient.getRoomNumber());
        locationLabel.setStyle("-fx-font-family: Arial; -fx-font-size: 18; -fx-text-fill: black;");

        // Add label to display time since last update
        Label lastUpdatedLabel = new Label("Last updated: null");
        lastUpdatedLabel.setStyle("-fx-font-family: Arial; -fx-font-size: 14; -fx-text-fill: black;");

        // Add the image and the labels to the details box
        detailsBox.getChildren().addAll(patientImage, nameLabel, locationLabel, lastUpdatedLabel);

        imageAndDetailsBox.getChildren().addAll(patientImage, detailsBox);
        // Add the details box to the PatientBox
        this.getChildren().addAll(imageAndDetailsBox);
    }

    // Fetches patient image and returns
    private ImageView addPatientImage(Patient patient){
        ImageView imageView = new ImageView();
        try {
            byte[] imageBytes = patient.getImageAsBytes(); // Get the image as bytes from database from Patients class
            if (imageBytes != null) {
                // Convert byte[] to Image
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(imageBytes);
                Image image = new Image(byteArrayInputStream);
                imageView.setImage(image);
            } else {
                // No image if you can't get
            }
        } catch (Exception e) {
            System.out.println("Failed to load patient image: " + e.getMessage());
            // Load no image if any error occurs
        }


        imageView.setFitWidth(65);  // Set the desired width
        imageView.setFitHeight(65); // Set the desired height
        imageView.setPreserveRatio(true); // Preserve the aspect ratio of the image

        return imageView;
    }
}
