package view.dashboard.details;

import backend.Patient;
import backend.exportToPdf;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.ByteArrayInputStream;

/**
 * Represents the patient profile UI that displays detailed information about a patient.
 */
public class PatientProfile extends Stage {
    private Patient patient;

    /**
     * Constructs the PatientProfile UI for the given patient ID.
     *
     * @param patientID The ID of the patient whose profile is displayed.
     */
    public PatientProfile(int patientID) {
        this.patient = new Patient(patientID);

        // Root AnchorPane
        AnchorPane root = new AnchorPane();
        root.setPrefSize(600, 500);

        // Create the scene with the root layout
        Scene scene = new Scene(root, 800, 600);
        this.setScene(scene);

        // Set stage dimensions
        this.setWidth(750);
        this.setHeight(550);

        // Set the title of the stage
        this.setTitle("Patient's Profile");
        this.show();

        // GridPane layout
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10); // Set horizontal gap between grid cells
        gridPane.setVgap(10); // Set vertical gap between grid cells
        gridPane.setPadding(new Insets(10, 10, 10, 10)); // Set padding around the grid

        // Column Constraints
        ColumnConstraints col1 = new ColumnConstraints(200);
        ColumnConstraints col2 = new ColumnConstraints(150);
        ColumnConstraints col3 = new ColumnConstraints(150);
        gridPane.getColumnConstraints().addAll(col1, col2, col3);

        // Row Constraints
        for (int i = 0; i < 14; i++) {
            gridPane.getRowConstraints().add(new RowConstraints(30));
        }

        // Profile Picture Section
        VBox profilePicture = new VBox();
        profilePicture.setPadding(new Insets(10));
        ImageView imageView = addPatientImage(this.patient); // Add patient image

        profilePicture.getChildren().add(imageView);
        GridPane.setColumnIndex(profilePicture, 0);
        GridPane.setRowSpan(profilePicture, 4); // Span 4 rows in the grid
        gridPane.getChildren().add(profilePicture);

        // Patient Details Section
        VBox patientDetails = new VBox();
        patientDetails.setSpacing(5); // Spacing between details

        Label nameLabel = new Label(this.patient.getName());
        nameLabel.setFont(new javafx.scene.text.Font(30)); // Set font size for patient name
        Label idLabel = new Label("Patient ID: " + this.patient.getPatientId().toString());
        Label wardLabel = new Label("Ward " + this.patient.getWard() + ", Room " + this.patient.getRoomNumber());
        Label updateLabel = new Label("Last Updated: 13:26 GMT"); // Static timestamp
        Label dateLabel = new Label("28/11/2024"); // Static date

        patientDetails.getChildren().addAll(nameLabel, idLabel, wardLabel, updateLabel, dateLabel);
        GridPane.setColumnIndex(patientDetails, 1);
        GridPane.setColumnSpan(patientDetails, 2); // Span 2 columns in the grid
        GridPane.setRowSpan(patientDetails, 4); // Span 4 rows in the grid
        gridPane.getChildren().add(patientDetails);

        // Details Section
        VBox detailsSection = new VBox();
        Label detailsLabel = new Label("Details");
        detailsLabel.setFont(new javafx.scene.text.Font(17));

        StackPane detailsPane = new StackPane();
        Rectangle detailsRectangle = new Rectangle(300, 150, Color.web("#e5e4e2")); // Background for details
        detailsRectangle.setArcHeight(20); // Rounded corners
        detailsRectangle.setArcWidth(20);

        VBox detailsContent = new VBox();
        detailsContent.setSpacing(10); // Spacing between content
        detailsContent.setPadding(new Insets(10, 0, 0, 35)); // Padding inside the content area
        detailsContent.getChildren().addAll(
                new Label(this.patient.getPatientDetails()) // Add patient details
        );
        detailsPane.getChildren().addAll(detailsRectangle, detailsContent);
        detailsSection.getChildren().addAll(detailsLabel, detailsPane);
        GridPane.setRowIndex(detailsSection, 6);
        GridPane.setColumnSpan(detailsSection, 2); // Span 2 columns
        gridPane.getChildren().add(detailsSection);

        // Posture Intervention History Section
        VBox postureHistory = new VBox();
        Label postureHistoryLabel = new Label("Posture Intervention History");
        postureHistoryLabel.setFont(new javafx.scene.text.Font(17));

        // Create a central button for viewing history
        Button viewHistoryButton = new Button("View History");
        viewHistoryButton.setFont(new javafx.scene.text.Font(14));
        Label noNotification = new Label(""); // Placeholder for notifications
        viewHistoryButton.setOnAction(e -> {
            // Action to generate and export patient history to PDF
            exportToPdf PdfExporter = new exportToPdf(patient);
            PdfExporter.generatePatientDetailsPDF("patient_" + patient.getPatientId() + "_details.pdf");
            Label downloadedNotification = new Label("patient_" + patient.getPatientId() + "_details.pdf exported to Downloads Folder");
            postureHistory.getChildren().remove(noNotification);
            postureHistory.getChildren().add(downloadedNotification);
            Timeline timeline = new Timeline(new KeyFrame(
                    Duration.seconds(3),
                    event -> {
                        postureHistory.getChildren().remove(downloadedNotification);
                        postureHistory.getChildren().add(noNotification);
                    } // Revert to placeholder after notification
            ));
            timeline.setCycleCount(1);
            timeline.play();
        });

        // Center the button horizontally
        StackPane buttonContainer = new StackPane(viewHistoryButton);
        buttonContainer.setPadding(new Insets(10)); // Optional padding

        // Add the label and button container to the VBox
        postureHistory.getChildren().addAll(postureHistoryLabel, buttonContainer, noNotification);
        postureHistory.setSpacing(10);

        // Add the VBox to the GridPane
        GridPane.setRowIndex(postureHistory, 10);
        GridPane.setColumnSpan(postureHistory, 2); // Span 2 columns
        gridPane.getChildren().add(postureHistory);

        // Procedures Section
        VBox proceduresSection = new VBox();
        Label proceduresLabel = new Label("Procedures");
        proceduresLabel.setFont(new javafx.scene.text.Font(17));

        VBox proceduresContent = new VBox();
        proceduresContent.setSpacing(5);
        int lenProc = this.patient.getProcedureDetails().size(); // Get procedures
        for (int i = 0; i < lenProc; i++) {
            proceduresContent.getChildren().add(createRoundedLabel(this.patient.getProcedureDetails().get(i)));
        }

        proceduresSection.getChildren().addAll(proceduresLabel, proceduresContent);
        GridPane.setRowIndex(proceduresSection, 10);
        GridPane.setColumnIndex(proceduresSection, 2);
        gridPane.getChildren().add(proceduresSection);

        // Medication Section
        VBox medicationSection = new VBox();
        Label MedicationLabel = new Label("Medication");
        MedicationLabel.setFont(new javafx.scene.text.Font(17));

        VBox medicationContent = new VBox();
        medicationContent.setSpacing(5);
        int lenMed = this.patient.getMedicationDetails().size(); // Get medications

        for (int i = 0; i < lenMed; i++) {
            medicationContent.getChildren().add(createRoundedLabel(this.patient.getMedicationDetails().get(i)));
        }

        medicationSection.getChildren().addAll(MedicationLabel, medicationContent);
        GridPane.setRowIndex(medicationSection, 5);
        GridPane.setColumnIndex(medicationSection, 2);
        GridPane.setColumnSpan(medicationSection, 2); // Span 2 columns
        gridPane.getChildren().add(medicationSection);

        // Adding GridPane to AnchorPane
        AnchorPane.setTopAnchor(gridPane, 5.0);
        AnchorPane.setBottomAnchor(gridPane, 5.0);
        AnchorPane.setLeftAnchor(gridPane, 10.0);
        AnchorPane.setRightAnchor(gridPane, 10.0);
        root.getChildren().add(gridPane); // Add GridPane to root
    }

    /**
     * Creates a rounded label with the specified text.
     *
     * @param text The text to display inside the label.
     * @return A StackPane containing the label.
     */
    private StackPane createRoundedLabel(String text) {
        StackPane stackPane = new StackPane();
        Rectangle background = new Rectangle(300, 30, Color.WHITE); // Background of the label
        background.setArcHeight(15); // Rounded corners
        background.setArcWidth(15);
        background.setStroke(Color.BLACK); // Outline color

        Label label = new Label(text);
        stackPane.getChildren().addAll(background, label);
        return stackPane;
    }

    /**
     * Adds the patient's profile image to the profile section.
     *
     * @param patient The patient object containing image data.
     * @return An ImageView containing the patient's profile picture.
     */
    private ImageView addPatientImage(Patient patient) {
        ImageView imageView = new ImageView();
        try {
            byte[] imageBytes = patient.getImageAsBytes(); // Get the image as bytes from the database
            if (imageBytes != null) {
                // Convert byte[] to Image
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(imageBytes);
                Image image = new Image(byteArrayInputStream);
                imageView.setImage(image);
            } else {
                // No image found for the patient
            }
        } catch (Exception e) {
            System.out.println("Failed to load patient image: " + e.getMessage());
            // Handle exceptions while loading the image
        }

        imageView.setFitWidth(150); // Set the desired width
        imageView.setFitHeight(175); // Set the desired height
        imageView.setPreserveRatio(true); // Preserve the aspect ratio of the image

        return imageView;
    }
}
