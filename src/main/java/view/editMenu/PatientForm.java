package view.editMenu;

import javafx.scene.layout.GridPane;

/**
 * The PatientForm interface defines the structure for creating patient-related forms.
 * Classes implementing this interface should provide implementations for adding components
 * to the form and specifying the form's title.
 */
public interface PatientForm {

    /**
     * Adds the necessary components to the provided GridPane.
     *
     * @param gridPane the GridPane to which components will be added.
     */
    void addComponents(GridPane gridPane);
    String getTitle();
}
