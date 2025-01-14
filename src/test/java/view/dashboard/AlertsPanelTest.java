package view.dashboard;

import backend.Patient;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/* Reference --6 taken from GitHub Copilot */
public class AlertsPanelTest {

    private AlertsPanel alertsPanel;
    private Patient mockPatient;

    @BeforeEach
    public void setUp() {
        alertsPanel = new AlertsPanel();
        mockPatient = Mockito.mock(Patient.class);
    }

    @Test
    public void testCreateAlertAmber() {
        when(mockPatient.getName()).thenReturn("John Doe");
        when(mockPatient.getWard()).thenReturn("A");
        when(mockPatient.getRoomNumber()).thenReturn(101);

        alertsPanel.createAlert("amber", mockPatient);

        VBox alertsContainer = (VBox) alertsPanel.getChildren().get(1).lookup(".scroll-pane .content");
        assertEquals(1, alertsContainer.getChildren().size());

        Label alertLabel = (Label) ((VBox) alertsContainer.getChildren().get(0)).getChildren().get(0);
        assertEquals("John Doe Ward A, Room 101", alertLabel.getText());
    }

    @Test
    public void testCreateAlertRed() {
        when(mockPatient.getName()).thenReturn("Jane Doe");
        when(mockPatient.getWard()).thenReturn("B");
        when(mockPatient.getRoomNumber()).thenReturn(102);

        alertsPanel.createAlert("red", mockPatient);

        VBox alertsContainer = (VBox) alertsPanel.getChildren().get(1).lookup(".scroll-pane .content");
        assertEquals(1, alertsContainer.getChildren().size());

        Label alertLabel = (Label) ((VBox) alertsContainer.getChildren().get(0)).getChildren().get(0);
        assertEquals("Jane Doe Ward B, Room 102", alertLabel.getText());
    }

    @Test
    public void testClearAlerts() {
        when(mockPatient.getName()).thenReturn("John Doe");
        when(mockPatient.getWard()).thenReturn("A");
        when(mockPatient.getRoomNumber()).thenReturn(101);

        alertsPanel.createAlert("amber", mockPatient);
        alertsPanel.clearAlerts();

        VBox alertsContainer = (VBox) alertsPanel.getChildren().get(1).lookup(".scroll-pane .content");
        assertEquals(0, alertsContainer.getChildren().size());
    }
}
/* end of reference --6 */
