package backend;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.Label;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CurrentTimeTest {

    @BeforeAll
    public static void initJFX() {
        // Initializes JavaFX environment
        new JFXPanel();
    }

    @Test
    public void testGetCurrentTimeFormat() {
        String currentTime = CurrentTime.getCurrentTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime parsedTime = LocalDateTime.parse(currentTime, formatter);

        assertEquals(currentTime, parsedTime.format(formatter));
    }

    @Test
    public void testGetCurrentTimeZone() {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("GMT"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String expectedTime = now.format(formatter);

        String currentTime = CurrentTime.getCurrentTime();

        assertEquals(expectedTime, currentTime);
    }

    @Test
    public void testUpdateLabel() {
        Label label = new Label();
        Platform.runLater(() -> CurrentTime.updateLabel(label));

        // Wait for the label to be updated
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String labelText = label.getText();
        assertEquals("Last updated: " + CurrentTime.getCurrentTime(), labelText);
    }

    @Test
    public void testStopUpdatingLabels() {
        Label label = new Label();
        Platform.runLater(() -> CurrentTime.updateLabel(label));

        // Wait for the label to be updated
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Platform.runLater(CurrentTime::stopUpdatingLabels);

        String labelTextBeforeStop = label.getText();

        // Wait to ensure no further updates
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String labelTextAfterStop = label.getText();
        assertEquals(labelTextBeforeStop, labelTextAfterStop);
    }
}
