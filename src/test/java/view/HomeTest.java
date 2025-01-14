package view;

import javafx.application.Platform;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import view.dashboard.PatientMonitorApp;
import view.editMenu.PatientEditor;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

/* Reference --14 taken from GitHub Copilot */
public class HomeTest {

    private Home home;
    private Stage mockStage;
    private PatientMonitorApp mockPatientMonitorApp;
    private PatientEditor mockPatientEditor;

    @BeforeEach
    public void setUp() {
        home = new Home();
        mockStage = Mockito.mock(Stage.class);
        mockPatientMonitorApp = Mockito.mock(PatientMonitorApp.class);
        mockPatientEditor = Mockito.mock(PatientEditor.class);
    }

    @Test
    public void testStart() {
        Platform.runLater(() -> {
            home.start(mockStage);
            assertNotNull(home);
        });
    }


}
/* end of reference --14 */
