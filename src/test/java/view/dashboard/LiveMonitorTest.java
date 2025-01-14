/*package view.dashboard;

import backend.AlertSystem;
import backend.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LiveMonitorTest {

    private StatusPanel mockStatusPanel;
    private AlertsPanel mockAlertsPanel;
    private List<PatientBox> mockPatientBoxes;
    private List<Patient> mockPatients;
    private LiveMonitor liveMonitor;

    @BeforeEach
    public void setUp() {
        mockStatusPanel = Mockito.mock(StatusPanel.class);
        mockAlertsPanel = Mockito.mock(AlertsPanel.class);
        mockPatientBoxes = new ArrayList<>();
        mockPatients = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            PatientBox mockPatientBox = Mockito.mock(PatientBox.class);
            Patient mockPatient = Mockito.mock(Patient.class);
            when(mockPatient.getPatientId()).thenReturn(i + 1);
            mockPatientBoxes.add(mockPatientBox);
            mockPatients.add(mockPatient);
        }

        liveMonitor = new LiveMonitor(mockStatusPanel, mockAlertsPanel, mockPatientBoxes, mockPatients);
    }

    @Test
    public void testMonitorPatientPostureUpdates() {
        AlertSystem mockAlertSystem = Mockito.mock(AlertSystem.class);

        when(mockAlertSystem.monitorPatientPosture(anyInt())).thenReturn(Arrays.asList("green", "amber", "red"));

        liveMonitor.run();

        verify(mockStatusPanel, times(1)).updateGreenCount(anyInt());
        verify(mockStatusPanel, times(1)).updateAmberCount(anyInt());
        verify(mockStatusPanel, times(1)).updateRedCount(anyInt());

        verify(mockAlertsPanel, times(1)).clearAlerts();
        verify(mockAlertsPanel, times(1)).createAlert(eq("amber"), any(Patient.class));
        verify(mockAlertsPanel, times(1)).createAlert(eq("red"), any(Patient.class));

        for (PatientBox mockPatientBox : mockPatientBoxes) {
            verify(mockPatientBox, times(1)).setAlertColor(anyString());
            verify(mockPatientBox, times(1)).setPostureImage(anyString());
        }
    }
}
*/