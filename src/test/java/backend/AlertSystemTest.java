package backend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
/* Reference 1-- taken from GitHub Copilot */
public class AlertSystemTest {

    private DatabaseLookup mockDatabaseLookup;
    private AlertSystem alertSystem;

    @BeforeEach
    public void setUp() {
        mockDatabaseLookup = Mockito.mock(DatabaseLookup.class);
        alertSystem = new AlertSystem(mockDatabaseLookup);
    }

    @Test
    public void testMonitorPatientPosture_GreenToAmber() {
        List<Map<String, Object>> mockData = new ArrayList<>();
        mockData.add(createPostureRecord(1, "2023-09-01 10:00:00", "left"));
        mockData.add(createPostureRecord(1, "2023-09-01 10:05:00", "left"));

        when(mockDatabaseLookup.retrieveData("postureHistory")).thenReturn(mockData);

        List<String> alertStatuses = alertSystem.monitorPatientPosture(1);

        assertEquals(Arrays.asList("green", "amber"), alertStatuses);
    }

    @Test
    public void testMonitorPatientPosture_AmberToRed() {
        List<Map<String, Object>> mockData = new ArrayList<>();
        mockData.add(createPostureRecord(1, "2023-09-01 10:00:00", "left"));
        mockData.add(createPostureRecord(1, "2023-09-01 10:05:00", "left"));
        mockData.add(createPostureRecord(1, "2023-09-01 10:20:00", "left"));

        when(mockDatabaseLookup.retrieveData("postureHistory")).thenReturn(mockData);

        List<String> alertStatuses = alertSystem.monitorPatientPosture(1);

        assertEquals(Arrays.asList("green", "amber", "red"), alertStatuses);
    }

    @Test
    public void testMonitorPatientPosture_PostureChangeResetsToGreen() {
        List<Map<String, Object>> mockData = new ArrayList<>();
        mockData.add(createPostureRecord(1, "2023-09-01 10:00:00", "left"));
        mockData.add(createPostureRecord(1, "2023-09-01 10:05:00", "left"));
        mockData.add(createPostureRecord(1, "2023-09-01 10:10:00", "right"));

        when(mockDatabaseLookup.retrieveData("postureHistory")).thenReturn(mockData);

        List<String> alertStatuses = alertSystem.monitorPatientPosture(1);

        assertEquals(Arrays.asList("green", "amber", "green"), alertStatuses);
    }

    private Map<String, Object> createPostureRecord(int patientId, String time, String posture) {
        Map<String, Object> record = new HashMap<>();
        record.put("patient_id", patientId);
        record.put("time", time);
        record.put("posture_position", posture);
        return record;
    }


    public String convertInputStreamToString(InputStream inputStream) throws Exception {
        final int bufferSize = 1024;
        final char[] buffer = new char[bufferSize];
        final StringBuilder out = new StringBuilder();
        Reader in = new InputStreamReader(inputStream, "UTF-8");
        for (; ; ) {
            int rsz = in.read(buffer, 0, buffer.length);
            if (rsz < 0)
                break;
            out.append(buffer, 0, rsz);
        }
        return out.toString();
    }
    /* end of reference 1*/
}
