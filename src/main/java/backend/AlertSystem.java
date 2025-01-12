package backend;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AlertSystem {

    private final DatabaseLookup databaseLookup;

    /**
     * Constructor requires a backend.DatabaseLookup instance,
     * which knows how to retrieve posture data from your database.
     */
    public AlertSystem(DatabaseLookup databaseLookup) {
        this.databaseLookup = databaseLookup;
    }

    /**
     * Monitors the patient's posture history (only from postureHistory table)
     * and returns a final alert status:
     * - GREEN initially or when posture changes,
     * - AMBER after 5 consecutive minutes of the same posture,
     * - RED after 20 consecutive minutes of the same posture (while AMBER).
     *
     * @param patientId The ID of the patient to monitor
     * @return The final alert status ("green", "amber", or "red"),
     *         or a message if no data for that patient.
     */
    public List<String> monitorPatientPosture(int patientId) {

        // 1. Retrieve ALL rows from the postureHistory table
        List<Map<String, Object>> allRows = databaseLookup.retrieveData("postureHistory");

        // If the table is empty or doesn't exist
        if (allRows.isEmpty()) {
            return List.of("No posture history table found or table is empty.");
        }

        // 2. Filter rows for the SPECIFIC patient
        List<Map<String, Object>> patientRows = allRows.stream()
                .filter(row -> {
                    // Make sure 'patient_id' matches the patient's ID
                    Object pidVal = row.get("patient_id");
                    if (pidVal instanceof Integer) {
                        return (Integer) pidVal == patientId;
                    }
                    return false;
                })
                .collect(Collectors.toList());

        if (patientRows.isEmpty()) {
            return List.of("No posture history found for Patient " + patientId);
        }

        // 3. Convert 'time' (String) to LocalDateTime; sort in ascending order of time
        DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        patientRows.sort((rowA, rowB) -> {
            String timeA = (String) rowA.get("time");
            String timeB = (String) rowB.get("time");

            LocalDateTime dateTimeA = LocalDateTime.parse(timeA, dtFormatter);
            LocalDateTime dateTimeB = LocalDateTime.parse(timeB, dtFormatter);

            return dateTimeA.compareTo(dateTimeB);
        });

        // 4. Initialize posture tracking
        String currentPosture = null;
        LocalDateTime currentPostureStartTime = null;
        String alertStatus = "green";

        List<String> alertStatuses = new ArrayList<>(); // List to store alert statuses

        // 5. Iterate over rows in chronological order
        for (Map<String, Object> row : patientRows) {
            // Extract posture_position
            String posture = (String) row.get("posture_position");

            // Parse 'time' to LocalDateTime
            String timeStr = (String) row.get("time");
            LocalDateTime timestamp = LocalDateTime.parse(timeStr, dtFormatter);

            // If posture changed or first record
            if (currentPosture == null || !currentPosture.equals(posture)) {
                currentPosture = posture;
                currentPostureStartTime = timestamp;
                alertStatus = "green";
                alertStatuses.add(alertStatus); // Append the status for this timestamp

            } else {
                // Same posture, calculate how long we've been in it
                Duration duration = Duration.between(currentPostureStartTime, timestamp);
                long minutes = duration.toMinutes();

                // If GREEN and we've hit >= 5 minutes, go AMBER
                if ("green".equals(alertStatus) && minutes >= 5) {
                    alertStatus = "amber";

                }
                // If AMBER and we've hit >= 20 minutes, go RED
                else if ("amber".equals(alertStatus) && minutes >= 20) {
                    alertStatus = "red";

                }

                alertStatuses.add(alertStatus); // Append the status for this timestamp
            }
        }

        // Return the list of alert statuses after processing all posture records
        return alertStatuses;
    }

}
