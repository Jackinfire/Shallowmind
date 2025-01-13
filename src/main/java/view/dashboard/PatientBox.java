package view.dashboard;

import backend.Patient;
import backend.exportToPdf;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import view.details.PatientProfile;
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



        detailsBox.getChildren().addAll(nameLabel,locationLabel,lastUpdatedLabel);
        this.getChildren().addAll(patientImage,detailsBox);
//        this.getChildren().addAll(detailsBox);

        // Add a click listener to the HBox
        this.setOnMouseClicked(event -> {
            System.out.println("HBox clicked!");
            PatientProfile patientPopup = new PatientProfile(patient.getPatientId());
            patientPopup.show();
            // Perform any action, such as opening a pop-up or navigating
        });


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
