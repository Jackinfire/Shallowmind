package view.dashboard;

import backend.Patient;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import view.utils.WindowDimensions;
import view.details.*;


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

//        ImageView patientImage = addPatientImage(patient);

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
//        this.getChildren().addAll(patientImage,detailsBox);
        this.getChildren().addAll(detailsBox);

        // Add a click listener to the HBox
        this.setOnMouseClicked(event -> {
            System.out.println("HBox clicked!");
            PatientProfile patientPopup = new PatientProfile(patient.getPatientId());
            patientPopup.show();
            // Perform any action, such as opening a pop-up or navigating
        });


    }

    // Fetches patient image and returns
//    private ImageView addPatientImage(Patient patient){
//        ImageView imageView = new ImageView();
//        try {
//            System.out.println("Attempting to load patient image: " + patient.getImagePath());
//            // Load image from classpath
//            Image image = new Image(getClass().getResourceAsStream(patient.getImagePath()));
//            imageView.setImage(image); // Set patient image
//        } catch (Exception e) {
//            System.out.println("Failed to load image: " + patient.getImagePath() + ". Loading default image.");
//            try {
//                // Load default image from classpath
//                Image defaultImage = new Image(getClass().getResourceAsStream("/default_pfp.jpg"));
//                imageView.setImage(defaultImage); // Set default image
//            } catch (Exception ex) {
//                System.out.println("Failed to load default image: " + ex.getMessage());
//            }
//        }

//        imageView.setFitWidth(65);  // Set the desired width
//        imageView.setFitHeight(65); // Set the desired height
//        imageView.setPreserveRatio(true); // Preserve the aspect ratio of the image
//
//
//
//        return imageView;

//    }

}



