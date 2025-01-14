package view.editMenu;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

/**
 * BasePatientForm is an abstract class for creating patient forms
 *  provides a structured layout using a GridPane within an AnchorPane
 */
public abstract class BasePatientForm extends Application implements PatientForm {

    protected GridPane gridPane;


    /**
     * Starts the application and sets up the stage and layout.
     *
     * @param primaryStage the main stage.
     */
    @Override
    public void start(Stage primaryStage) {
        // AnchorPane as root
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefSize(623, 423);

        // GridPane setup
        setupGridPane();
        anchorPane.getChildren().add(gridPane);

        // Add form-specific components
        addComponents(gridPane);

        // Set Scene
        Scene scene = new Scene(anchorPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle(getTitle());
        primaryStage.show();
    }


    /**
     * Sets up the GridPane layout with columns and rows.
     */
    private void setupGridPane() {
        gridPane = new GridPane();
        gridPane.setPrefSize(623, 423);
        AnchorPane.setTopAnchor(gridPane, 5.0);
        AnchorPane.setBottomAnchor(gridPane, 5.0);
        AnchorPane.setLeftAnchor(gridPane, 10.0);
        AnchorPane.setRightAnchor(gridPane, 10.0);

        for (int i = 0; i < 18; i++) {
            ColumnConstraints colConstraints = new ColumnConstraints();
            colConstraints.setPrefWidth(100);
            gridPane.getColumnConstraints().add(colConstraints);
        }

        for (int i = 0; i < 14; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setPrefHeight(30);
            gridPane.getRowConstraints().add(rowConstraints);
        }
    }
}
