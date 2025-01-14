/*package view.dashboard;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UpperPanelTest {

    private UpperPanel upperPanel;
    private StatusPanel mockStatusPanel;
    private AlertsPanel mockAlertsPanel;

    @BeforeEach
    public void setUp() {
        mockStatusPanel = Mockito.mock(StatusPanel.class);
        mockAlertsPanel = Mockito.mock(AlertsPanel.class);
        upperPanel = new UpperPanel("Test Panel");
    }

    @Test
    public void testUpperPanelLayout() {
        assertEquals(Pos.TOP_CENTER, upperPanel.getAlignment());
        assertEquals(20, upperPanel.getSpacing());
    }

    @Test
    public void testUpperPanelTitle() {
        Label titleLabel = (Label) upperPanel.getChildren().get(0);
        assertEquals("Test Panel", titleLabel.getText());
    }

    @Test
    public void testUpperPanelStatusPanel() {
        upperPanel.getChildren().add(mockStatusPanel);
        assertEquals(mockStatusPanel, upperPanel.getChildren().get(1));
    }

    @Test
    public void testUpperPanelAlertsPanel() {
        upperPanel.getChildren().add(mockAlertsPanel);
        assertEquals(mockAlertsPanel, upperPanel.getChildren().get(2));
    }
}
*/