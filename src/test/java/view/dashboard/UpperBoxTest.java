package view.dashboard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

public class UpperBoxTest {

    private UpperBox upperBox;
    private StatusPanel mockStatusPanel;
    private AlertsPanel mockAlertsPanel;

    @BeforeEach
    public void setUp() {
        mockStatusPanel = mock(StatusPanel.class);
        mockAlertsPanel = mock(AlertsPanel.class);
        upperBox = new UpperBox();
    }

    @Test
    public void testUpperBoxLayout() {
        assertNotNull(upperBox.getStatusPanel());
        assertNotNull(upperBox.getAlertsPanel());
    }

    @Test
    public void testStatusPanelInteraction() {
        upperBox.getStatusPanel().updateGreenCount(5);
        Mockito.verify(mockStatusPanel, Mockito.times(1)).updateGreenCount(5);
    }

    @Test
    public void testAlertsPanelInteraction() {
        upperBox.getAlertsPanel().clearAlerts();
        Mockito.verify(mockAlertsPanel, Mockito.times(1)).clearAlerts();
    }
}
