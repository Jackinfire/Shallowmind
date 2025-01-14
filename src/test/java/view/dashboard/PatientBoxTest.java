package view.dashboard;

import backend.Patient;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/* Reference --8 taken from GitHub Copilot */
public class PatientBoxTest {

    private PatientBox patientBox;
    private Patient mockPatient;

    @BeforeEach
    public void setUp() {
        mockPatient = Mockito.mock(Patient.class);
        when(mockPatient.getName()).thenReturn("John Doe");
        when(mockPatient.getWard()).thenReturn("A");
        when(mockPatient.getRoomNumber()).thenReturn(101);
        patientBox = new PatientBox(mockPatient);
    }

    @Test
    public void testPatientBoxUI() {
        VBox detailsBox = (VBox) patientBox.getChildren().get(1);
        Label nameLabel = (Label) detailsBox.getChildren().get(0);
        Label locationLabel = (Label) detailsBox.getChildren().get(1);

        assertEquals("John Doe", nameLabel.getText());
        assertEquals("Ward A, Room 101", locationLabel.getText());
    }

    @Test
    public void testSetAlertColor() {
        patientBox.setAlertColor("#E67E22");
        assertEquals("-fx-background-color: #E67E22;-fx-background-radius: 15;", patientBox.getStyle());
    }

    @Test
    public void testSetPostureImage() {
        patientBox.setPostureImage("left");
        ImageView postureImageView = (ImageView) patientBox.getChildren().get(2);
        assertEquals("left_posture.png", postureImageView.getImage().getUrl());
    }
}
/* end of reference --8 */
