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

    @Test
    public void testOpenHealthcareProfessionalView() {
        Platform.runLater(() -> {
            home.start(mockStage);
            home.openHealthcareProfessionalView();
            verify(mockPatientMonitorApp, times(1)).start(any(Stage.class));
        });
    }

    @Test
    public void testOpenAdminView() {
        Platform.runLater(() -> {
            home.start(mockStage);
            home.openAdminView();
            verify(mockPatientEditor, times(1)).start(any(Stage.class));
        });
    }
}
