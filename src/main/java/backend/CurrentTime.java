package backend;
import javafx.application.Platform;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;
import javafx.scene.control.Label;

public class CurrentTime {

    private static final String DEFAULT_TIME_FORMAT = "dd-MM-yyyy HH:mm";
    private static final String DEFAULT_TIME_ZONE = "GMT";
    private static Timer timer;

    /**
     * Get the current time in a custom format and specific time zone.
     * <p>
     *
     * @return Formatted current date and time as a String.
     */
    public static String getCurrentTime() {
        // Get the current date-time in the specified time zone
        LocalDateTime now = LocalDateTime.now(ZoneId.of(DEFAULT_TIME_ZONE));
        // Format the date-time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT);
        return now.format(formatter);
    }

    public static void startUpdatingLabel(Label label) {
        if (timer == null) {
            timer = new Timer(true); // Create a shared daemon timer
        }

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // Update the label text on the JavaFX Application Thread
                Platform.runLater(() -> label.setText("Last updated: " + getCurrentTime()));
            }
        }, 0, 1000); // Update every second (1000 milliseconds)
    }

    /**
     * Stop updating all labels (stops the shared timer).
     */
    public static void stopUpdatingLabels() {
        if (timer != null) {
            timer.cancel();
            timer.purge();
            timer = null;
        }
    }
}



