package view.details;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class PatientProfile extends Application {
    @Override
    public void start(Stage stage) {
        // Root AnchorPane
        AnchorPane root = new AnchorPane();
        root.setPrefSize(600, 500);

        Scene scene = new Scene(root, 800,600);
        stage.setScene(scene);

        stage.setWidth(750);
        stage.setHeight(550);

        stage.setTitle("Patient's Profile");

        stage.show();

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

        Image image = new Image(getClass().getResource("/Patient_Profile_Samplejpeg.jpeg").toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(150);
        imageView.setFitWidth(175);
        imageView.setPreserveRatio(true);

        profilePicture.getChildren().add(imageView);
        GridPane.setColumnIndex(profilePicture, 0);
        GridPane.setRowSpan(profilePicture, 4);
        gridPane.getChildren().add(profilePicture);

        // Patient Details Section
        VBox patientDetails = new VBox();
        patientDetails.setSpacing(5);

        Label nameLabel = new Label("John Doe");
        nameLabel.setFont(new javafx.scene.text.Font(30));
        Label idLabel = new Label("Patient ID: 21411231");
        Label wardLabel = new Label("Ward D, Room 204");
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
                new Label("Age: 67"),
                new Label("Gender: Male"),
                new Label("Emergency Contact: 07481751739"),
                new Label("Diagnosis: Type 2 Diabetes"),
                new Label("Doctor in Charge: Dr. Charlie James")
        );

        detailsPane.getChildren().addAll(detailsRectangle, detailsContent);
        detailsSection.getChildren().addAll(detailsLabel, detailsPane);
        GridPane.setRowIndex(detailsSection, 6);
        GridPane.setColumnSpan(detailsSection, 2);
        gridPane.getChildren().add(detailsSection);

        // Posture Intervention History Section
        VBox postureHistory = new VBox();
        Label postureHistoryLabel = new Label("Posture Intervention History");
        postureHistoryLabel.setFont(new javafx.scene.text.Font(17));

        VBox postureContent = new VBox();
        postureContent.setSpacing(5);
        postureContent.getChildren().addAll(
                createRoundedLabel("12/11: Poor Posture Left: 22 Minutes"),
                createRoundedLabel("8/11: Poor Posture Left: 45 Minutes"),
                createRoundedLabel("6/11: Poor Posture Left: 23 Minutes")
        );

        postureHistory.getChildren().addAll(postureHistoryLabel, postureContent);
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
                createRoundedLabel("20/11 Foot Exam"),
                createRoundedLabel("29/11 Meet Dietician"),
                createRoundedLabel("13/21 Pancreas Transplant")
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
                createRoundedLabel("Metformin, Tablet 500mg 2x Daily (3.3.2022)"),
                createRoundedLabel("Insulin Glargine, 10 units 1x Daily (23.3.2024)")
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

    public static void main(String[] args) {
        launch(args);
    }
}
