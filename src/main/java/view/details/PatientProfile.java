package view.details;

import backend.Patient;
import javafx.application.Application;
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
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;


public class PatientProfile extends Stage {
    private  Patient patient;


    public PatientProfile(int patientID){
        this.patient = new Patient (patientID);

        // Root AnchorPane
        AnchorPane root = new AnchorPane();
        root.setPrefSize(600, 500);

        Scene scene = new Scene(root, 800,600);
        this.setScene(scene);

        this.setWidth(750);
        this.setHeight(550);

        this.setTitle("Patient's Profile");
        this.show();

        // GridPane
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10, 10, 10, 10));

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
        ImageView imageView = addPatientImage(this.patient);

        profilePicture.getChildren().add(imageView);
        GridPane.setColumnIndex(profilePicture, 0);
        GridPane.setRowSpan(profilePicture, 4);
        gridPane.getChildren().add(profilePicture);

        // Patient Details Section
        VBox patientDetails = new VBox();
        patientDetails.setSpacing(5);

        Label nameLabel = new Label(this.patient.getName());
        nameLabel.setFont(new javafx.scene.text.Font(30));
        Label idLabel = new Label("Patient ID: " + this.patient.getPatientId().toString());
        Label wardLabel = new Label("Ward " + this.patient.getWard() + ", Room " + this.patient.getRoomNumber());
        Label updateLabel = new Label("Last Updated: 13:26 GMT");
        Label dateLabel = new Label("28/11/2024");

        patientDetails.getChildren().addAll(nameLabel, idLabel, wardLabel, updateLabel, dateLabel);
        GridPane.setColumnIndex(patientDetails, 1);
        GridPane.setColumnSpan(patientDetails, 2);
        GridPane.setRowSpan(patientDetails, 4);
        gridPane.getChildren().add(patientDetails);

        // Good Posture Section
        StackPane goodPosturePane = new StackPane();
        Rectangle postureRectangle = new Rectangle(150, 75, Color.web("#4cbb17"));
        postureRectangle.setArcHeight(20);
        postureRectangle.setArcWidth(20);
        postureRectangle.setStroke(Color.WHITE);
        Label postureLabel = new Label("Good Posture");
        postureLabel.setTextFill(Color.WHITE);
        postureLabel.setFont(new javafx.scene.text.Font(20));
        goodPosturePane.getChildren().addAll(postureRectangle, postureLabel);
        GridPane.setColumnIndex(goodPosturePane, 3);
        GridPane.setRowIndex(goodPosturePane, 1);
        GridPane.setRowSpan(goodPosturePane, 1);
        gridPane.getChildren().add(goodPosturePane);

        // Details Section
        VBox detailsSection = new VBox();
        Label detailsLabel = new Label("Details");
        detailsLabel.setFont(new javafx.scene.text.Font(17));

        StackPane detailsPane = new StackPane();
        Rectangle detailsRectangle = new Rectangle(300, 150, Color.web("#e5e4e2"));
        detailsRectangle.setArcHeight(20);
        detailsRectangle.setArcWidth(20);

        VBox detailsContent = new VBox();
        detailsContent.setSpacing(10);
        detailsContent.setPadding(new Insets(10,0,0,35));
        detailsContent.getChildren().addAll(
                new Label(this.patient.getPatientDetails())
                );
        detailsPane.getChildren().addAll(detailsRectangle, detailsContent);
        detailsSection.getChildren().addAll(detailsLabel, detailsPane);
        GridPane.setRowIndex(detailsSection, 6);
        GridPane.setColumnSpan(detailsSection, 2);
        gridPane.getChildren().add(detailsSection);

        // Posture Intervention History Section
// Posture Intervention History Section
        VBox postureHistory = new VBox();
        Label postureHistoryLabel = new Label("Posture Intervention History");
        postureHistoryLabel.setFont(new javafx.scene.text.Font(17));

// Create a central button
        Button viewHistoryButton = new Button("View History");
        viewHistoryButton.setFont(new javafx.scene.text.Font(14));
        viewHistoryButton.setOnAction(e -> {
            // Action to perform when the button is clicked
            System.out.println("Posture intervention history button clicked!");
        });

// Center the button horizontally
        StackPane buttonContainer = new StackPane(viewHistoryButton);
        buttonContainer.setPadding(new Insets(10)); // Optional padding for aesthetics

// Add the label and button container to the VBox
        postureHistory.getChildren().addAll(postureHistoryLabel, buttonContainer);
        postureHistory.setSpacing(10);

// Add the VBox to the GridPane
        GridPane.setRowIndex(postureHistory, 10);
        GridPane.setColumnSpan(postureHistory, 2);
        gridPane.getChildren().add(postureHistory);


        // Procedures Section
        VBox proceduresSection = new VBox();
        Label proceduresLabel = new Label("Procedures");
        proceduresLabel.setFont(new javafx.scene.text.Font(17));

        VBox proceduresContent = new VBox();
        proceduresContent.setSpacing(5);
        proceduresContent.getChildren().addAll(
                createRoundedLabel(this.patient.getProcedureName()),
                createRoundedLabel(this.patient.getProcedureName()),
                createRoundedLabel(this.patient.getProcedureName())
        );

        proceduresSection.getChildren().addAll(proceduresLabel, proceduresContent);
        GridPane.setRowIndex(proceduresSection, 10);
        GridPane.setColumnIndex(proceduresSection, 2);
        gridPane.getChildren().add(proceduresSection);

        //Medication Section
        VBox medicationSection = new VBox();
        Label MedicationLabel = new Label("Medication");
        MedicationLabel.setFont(new javafx.scene.text.Font(17));

        VBox medicationContent = new VBox();
        medicationContent.setSpacing(5);

        medicationContent.getChildren().addAll(
                createRoundedLabel(this.patient.getMedicationDetails()),
                createRoundedLabel(this.patient.getMedicationDetails(),
        );

        medicationSection.getChildren().addAll(MedicationLabel, medicationContent);
        GridPane.setRowIndex(medicationSection, 5);
        GridPane.setColumnIndex(medicationSection, 2);
        GridPane.setColumnSpan(medicationSection, 2);
        gridPane.getChildren().add(medicationSection);
        // Adding GridPane to AnchorPane
        AnchorPane.setTopAnchor(gridPane, 5.0);
        AnchorPane.setBottomAnchor(gridPane, 5.0);
        AnchorPane.setLeftAnchor(gridPane, 10.0);
        AnchorPane.setRightAnchor(gridPane, 10.0);
        root.getChildren().add(gridPane);


}
    private StackPane createRoundedLabel(String text) {
        StackPane stackPane = new StackPane();
        Rectangle background = new Rectangle(300, 30, Color.WHITE);
        background.setArcHeight(15);
        background.setArcWidth(15);
        background.setStroke(Color.BLACK);

        Label label = new Label(text);
        stackPane.getChildren().addAll(background, label);
        return stackPane;
    }

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


        imageView.setFitWidth(150);  // Set the desired width
        imageView.setFitHeight(175); // Set the desired height
        imageView.setPreserveRatio(true); // Preserve the aspect ratio of the image

        return imageView;
    }
}


