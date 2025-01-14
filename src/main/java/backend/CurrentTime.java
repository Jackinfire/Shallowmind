package backend;
import javafx.application.Platform;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;
import javafx.scene.control.Label;

public class CurrentTime {

    private static final String TIME_FORMAT = "dd-MM-yyyy HH:mm";
    private static final String TIME_ZONE = "GMT";
    private static Timer timer;

    /**
     * get time in set format and zone
     *
     * @return Formatted current date and time as a String.
     */
    public static String getCurrentTime() {
        LocalDateTime now = LocalDateTime.now(ZoneId.of(TIME_ZONE));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_FORMAT);
        return now.format(formatter);
    }

    public static void updateLabel(Label label) {
        if (timer == null) {
            timer = new Timer(true);
        }

        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                Platform.runLater(() -> label.setText("Last updated: " + getCurrentTime()));
            }
        }, 0, 1000); // Updates every second
    }

    /**
     * Stops updating all labels when window is closed
     */
    public static void stopUpdatingLabels() {
        if (timer != null) {
            timer.cancel();
            timer.purge();
            timer = null;
        }
    }
}



