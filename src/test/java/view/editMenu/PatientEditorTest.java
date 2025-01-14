package view.editMenu;

import backend.DatabaseLookup;
import backend.Patient;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
/* Reference --13 taken from GitHub Copilot */
public class PatientEditorTest {

    private PatientEditor patientEditor;
    private DatabaseLookup mockDatabaseLookup;
    private Patient mockPatient;

    @BeforeEach
    public void setUp() {
        mockDatabaseLookup = Mockito.mock(DatabaseLookup.class);
        mockPatient = Mockito.mock(Patient.class);
        patientEditor = new PatientEditor();
    }

    @Test
    public void testFetchPatientData() {
        when(mockDatabaseLookup.getNumberOfPatients()).thenReturn(1);
        when(mockPatient.getName()).thenReturn("John Doe");
        when(mockPatient.getAge()).thenReturn(30);
        when(mockPatient.getRoomNumber()).thenReturn(101);
        when(mockPatient.getDocInCharge()).thenReturn("Dr. Smith");
        when(mockPatient.getDiagnosis()).thenReturn("Diagnosis");
        when(mockPatient.getContactNumber()).thenReturn("1234567890");
        when(mockPatient.getWard()).thenReturn("Ward A");

        GridPane gridPane = new GridPane();
        patientEditor.addComponents(gridPane);

        TextField idField = (TextField) gridPane.getChildren().get(3);
        idField.setText("1");

        Button fetchButton = (Button) gridPane.getChildren().get(4);
        fetchButton.fire();

        TextField nameField = (TextField) gridPane.getChildren().get(6);
        assertEquals("John Doe", nameField.getText());

        TextField ageField = (TextField) gridPane.getChildren().get(12);
        assertEquals("30", ageField.getText());

        TextField roomField = (TextField) gridPane.getChildren().get(10);
        assertEquals("101", roomField.getText());

        TextField doctorField = (TextField) gridPane.getChildren().get(18);
        assertEquals("Dr. Smith", doctorField.getText());

        TextField diagnosisField = (TextField) gridPane.getChildren().get(16);
        assertEquals("Diagnosis", diagnosisField.getText());

        TextField contactField = (TextField) gridPane.getChildren().get(14);
        assertEquals("1234567890", contactField.getText());

        TextField wardField = (TextField) gridPane.getChildren().get(8);
        assertEquals("Ward A", wardField.getText());
    }

    @Test
    public void testSavePatientData() {
        when(mockDatabaseLookup.getNumberOfPatients()).thenReturn(1);
        when(mockPatient.getName()).thenReturn("John Doe");
        when(mockPatient.getAge()).thenReturn(30);
        when(mockPatient.getRoomNumber()).thenReturn(101);
        when(mockPatient.getDocInCharge()).thenReturn("Dr. Smith");
        when(mockPatient.getDiagnosis()).thenReturn("Diagnosis");
        when(mockPatient.getContactNumber()).thenReturn("1234567890");
        when(mockPatient.getWard()).thenReturn("Ward A");

        GridPane gridPane = new GridPane();
        patientEditor.addComponents(gridPane);

        TextField idField = (TextField) gridPane.getChildren().get(3);
        idField.setText("1");

        Button fetchButton = (Button) gridPane.getChildren().get(4);
        fetchButton.fire();

        TextField nameField = (TextField) gridPane.getChildren().get(6);
        nameField.setText("Jane Doe");

        TextField ageField = (TextField) gridPane.getChildren().get(12);
        ageField.setText("25");

        TextField roomField = (TextField) gridPane.getChildren().get(10);
        roomField.setText("102");

        TextField doctorField = (TextField) gridPane.getChildren().get(18);
        doctorField.setText("Dr. Johnson");

        TextField diagnosisField = (TextField) gridPane.getChildren().get(16);
        diagnosisField.setText("New Diagnosis");

        TextField contactField = (TextField) gridPane.getChildren().get(14);
        contactField.setText("9876543210");

        TextField wardField = (TextField) gridPane.getChildren().get(8);
        wardField.setText("Ward B");

        Button submitButton = (Button) gridPane.getChildren().get(20);
        submitButton.fire();

        verify(mockPatient).setName("Jane Doe");
        verify(mockPatient).setAge(25);
        verify(mockPatient).setRoomNumber(102);
        verify(mockPatient).setDocInCharge("Dr. Johnson");
        verify(mockPatient).setDiagnosis("New Diagnosis");
        verify(mockPatient).setContactNumber("9876543210");
        verify(mockPatient).setWard("Ward B");
    }
}
/* end of reference --13 */
