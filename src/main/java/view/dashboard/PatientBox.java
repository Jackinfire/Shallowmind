package view.dashboard;

import backend.CurrentTime;
import backend.Patient;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import view.dashboard.details.PatientProfile;
import view.utils.WindowDimensions;
import java.io.ByteArrayInputStream;

/**
 * The PatientBox class represents a UI component that displays a patient's
 * information, including name, location, last updated time, image, and posture image.
 * It extends HBox to align its components horizontally.
 */
public class PatientBox extends HBox {

    private String alertColor = "#328D40"; // Default alert color is green
    ImageView postureImageView; // ImageView to display the patient's posture

    /**
     * Constructor for PatientBox.
     *
     * @param patient The patient object containing the patient's information.
     */
    public PatientBox(Patient patient) {

        // Create a VBox for patient details
        VBox detailsBox = new VBox();

        // Set the style and layout properties of the PatientBox
        this.setStyle("-fx-background-color:" + alertColor + "; -fx-background-radius: 15;");
        this.setMinSize(WindowDimensions.windowWidth * 0.4 - 20, WindowDimensions.windowHeight * 0.125);
        this.setMaxSize(WindowDimensions.windowWidth * 0.4 - 20, WindowDimensions.windowHeight * 0.125);
        this.setPadding(new Insets(10, 20, 10, 20));
        this.setSpacing(20);

        // Add label to display the patient's name
        Label nameLabel = new Label(patient.getName());
        nameLabel.setStyle("-fx-font-family: Arial; -fx-font-size: 18; -fx-text-fill: black; -fx-font-weight: bold");

        // Add label to display the patient's location
        Label locationLabel = new Label("Ward " + patient.getWard() + ", Room " + patient.getRoomNumber());
        locationLabel.setStyle("-fx-font-family: Arial; -fx-font-size: 18; -fx-text-fill: black;");

        // Add label to display the last updated time
        Label lastUpdatedLabel = new Label("Last updated:" + CurrentTime.getCurrentTime());
        lastUpdatedLabel.setStyle("-fx-font-family: Arial; -fx-font-size: 14; -fx-text-fill: black;");

        // Update the label dynamically with the current time
        CurrentTime.updateLabel(lastUpdatedLabel);

        // Add all labels to the details box and set padding for spacing
        detailsBox.getChildren().addAll(nameLabel, locationLabel, lastUpdatedLabel);
        detailsBox.setPadding(new Insets(0, 60, 0, 0)); // Add padding on the right

        // Add the patient's profile image
        ImageView patientImage = addPatientImage(patient);

        // Initialize the posture image with the default posture
        this.postureImageView = new ImageView("straight_posture.png");
        postureImageView.setFitWidth(65);  // Set the desired width
        postureImageView.setFitHeight(65); // Set the desired height
        postureImageView.setPreserveRatio(true); // Preserve the aspect ratio of the image

        // Add all components to the PatientBox (HBox)
        this.getChildren().addAll(patientImage, detailsBox, postureImageView);

        // Add a click listener to the PatientBox to open a detailed PatientProfile
        this.setOnMouseClicked(event -> {
            PatientProfile patientPopup = new PatientProfile(patient.getPatientId());
            patientPopup.show();
        });
    }

    /**
     * Fetches the patient's profile image from the database and returns an ImageView.
     *
     * @param patient The patient object containing the profile image data.
     * @return An ImageView containing the patient's image.
     */
    private ImageView addPatientImage(Patient patient) {
        ImageView imageView = new ImageView();
        try {
            byte[] imageBytes = patient.getImageAsBytes(); // Get the image as bytes from the database
            if (imageBytes != null) {
                // Convert byte array to an Image
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(imageBytes);
                Image image = new Image(byteArrayInputStream);
                imageView.setImage(image);
            } else {
                // Handle case where no image is available
            }
        } catch (Exception e) {
            System.out.println("Failed to load patient image: " + e.getMessage());
            // Handle error loading the image
        }

        // Set dimensions and aspect ratio for the image view
        imageView.setFitWidth(65);
        imageView.setFitHeight(65);
        imageView.setPreserveRatio(true);

        return imageView;
    }

    /**
     * Updates the alert color of the PatientBox and applies the new style.
     *
     * @param alertColor The new alert color to be applied (e.g., green, amber, red).
     */
    public void setAlertColor(String alertColor) {
        this.alertColor = alertColor;
        this.setStyle("-fx-background-color: " + alertColor + ";-fx-background-radius: 15;");
    }

    /**
     * Updates the posture image of the patient based on the given posture.
     *
     * @param posture The posture to be displayed ("left", "right", "straight").
     */
    public void setPostureImage(String posture) {
        String imagePath; // Define the image path based on the posture

        switch (posture) {
            case "left":
                imagePath = "left_posture.png";
                break;
            case "right":
                imagePath = "right_posture.png";
                break;
            case "straight":
                imagePath = "straight_posture.png";
                break;
            default:
                System.err.println("Unknown posture: " + posture);
                return; // Exit the method if posture is unknown
        }

        // Update the existing ImageView with the new image
        this.postureImageView.setImage(new Image(imagePath));
    }
}
