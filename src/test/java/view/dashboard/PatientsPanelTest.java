package view.dashboard;

import backend.DatabaseLookup;
import backend.Patient;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import view.utils.WindowDimensions;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PatientsPanelTest {

    private PatientsPanel patientsPanel;
    private StatusPanel mockStatusPanel;
    private AlertsPanel mockAlertsPanel;
    private DatabaseLookup mockDatabaseLookup;
    private List<Patient> mockPatients;
    private List<PatientBox> mockPatientBoxes;

    @BeforeEach
    public void setUp() {
        mockStatusPanel = Mockito.mock(StatusPanel.class);
        mockAlertsPanel = Mockito.mock(AlertsPanel.class);
        mockDatabaseLookup = Mockito.mock(DatabaseLookup.class);
        when(mockDatabaseLookup.getNumberOfPatients()).thenReturn(4);

        mockPatients = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            Patient mockPatient = Mockito.mock(Patient.class);
            when(mockPatient.getPatientId()).thenReturn(i);
            mockPatients.add(mockPatient);
        }

        mockPatientBoxes = new ArrayList<>();
        for (Patient mockPatient : mockPatients) {
            PatientBox mockPatientBox = Mockito.mock(PatientBox.class);
            mockPatientBoxes.add(mockPatientBox);
        }

        patientsPanel = new PatientsPanel(mockStatusPanel, mockAlertsPanel);
    }

    @Test
    public void testPatientsPanelLayout() {
        VBox panel = (VBox) patientsPanel.getContent();
        assertEquals(4, panel.getChildren().size());

        for (int i = 0; i < 4; i += 2) {
            HBox hBox = (HBox) panel.getChildren().get(i / 2);
            assertEquals(2, hBox.getChildren().size());
        }
    }

    @Test
    public void testLiveMonitorInitialization() {
        LiveMonitor liveMonitor = patientsPanel.getLiveMonitor();
        assertEquals(mockStatusPanel, liveMonitor.getStatusPanel());
        assertEquals(mockAlertsPanel, liveMonitor.getAlertsPanel());
        assertEquals(mockPatientBoxes, liveMonitor.getPatientBoxes());
        assertEquals(mockPatients, liveMonitor.getPatients());
    }
}
