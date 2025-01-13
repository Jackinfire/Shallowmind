package backend;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class CurrentTime {

    private static final String DEFAULT_TIME_FORMAT = "dd-MM-yyyy HH:mm";
    private static final String DEFAULT_TIME_ZONE = "GMT";

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
}


