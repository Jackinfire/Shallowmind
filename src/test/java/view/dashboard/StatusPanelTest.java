package view.dashboard;

import backend.DatabaseLookup;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class StatusPanelTest {

    private StatusPanel statusPanel;
    private DatabaseLookup mockDatabaseLookup;

    @BeforeEach
    public void setUp() {
        mockDatabaseLookup = Mockito.mock(DatabaseLookup.class);
        when(mockDatabaseLookup.getNumberOfPatients()).thenReturn(10);
        statusPanel = new StatusPanel();
    }

    @Test
    public void testInitialCounts() {
        assertEquals(0, statusPanel.greenPatients);
        assertEquals(0, statusPanel.amberPatients);
        assertEquals(0, statusPanel.redPatients);
    }

    @Test
    public void testUpdateGreenCount() {
        statusPanel.updateGreenCount(5);
        assertEquals(5, statusPanel.greenPatients);
        Label label = (Label) statusPanel.greenBox.getChildren().get(0);
        assertEquals("5", label.getText());
    }

    @Test
    public void testUpdateAmberCount() {
        statusPanel.updateAmberCount(3);
        assertEquals(3, statusPanel.amberPatients);
        Label label = (Label) statusPanel.amberBox.getChildren().get(0);
        assertEquals("3", label.getText());
    }

    @Test
    public void testUpdateRedCount() {
        statusPanel.updateRedCount(2);
        assertEquals(2, statusPanel.redPatients);
        Label label = (Label) statusPanel.redBox.getChildren().get(0);
        assertEquals("2", label.getText());
    }
}
