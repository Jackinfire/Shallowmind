package backend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PatientTest {

    private DatabaseLookup mockDatabaseLookup;
    private Patient patient;

    @BeforeEach
    public void setUp() {
        mockDatabaseLookup = Mockito.mock(DatabaseLookup.class);
        patient = new Patient(1);
    }

    @Test
    public void testGetPatientDetails() {
        when(mockDatabaseLookup.retrieveData("patientData")).thenReturn(Arrays.asList(
                createPatientRecord(1, "John Doe", 30, "Male", "Diagnosis", "2023-09-01", "Dr. Smith", "Ward A", 101)
        ));

        String patientDetails = patient.getPatientDetails();

        assertEquals("Age: 30\nGender: Male\nDiagnosis: Diagnosis\nDiagnosis Date: 2023-09-01\nEmergency Contact Number: null\nDoctor in charge: Dr. Smith", patientDetails);
    }

    @Test
    public void testGetInterventionHistory() {
        when(mockDatabaseLookup.retrieveData("interventionHistory")).thenReturn(Arrays.asList(
                createInterventionRecord(1, "2023-09-01 10:00:00", "Nurse A", "left", 10)
        ));

        List<String> interventionTimes = patient.getInterventionTime();
        List<String> handledByList = patient.getInterventionHandledBy();
        List<String> positionList = patient.getInterventionPosition();
        List<Integer> lengthList = patient.getInterventionLength();

        assertEquals(Arrays.asList("2023-09-01 10:00:00"), interventionTimes);
        assertEquals(Arrays.asList("Nurse A"), handledByList);
        assertEquals(Arrays.asList("left"), positionList);
        assertEquals(Arrays.asList(10), lengthList);
    }

    @Test
    public void testSetPatientDetails() {
        patient.setName("Jane Doe");
        patient.setAge(25);
        patient.setGender("Female");
        patient.setDiagnosis("New Diagnosis");
        patient.setDocInCharge("Dr. Johnson");
        patient.setWard("Ward B");
        patient.setRoomNumber(102);
        patient.setContactNumber("9876543210");

        verify(mockDatabaseLookup).updateField("patientData", "name", "Jane Doe", "id", 1);
        verify(mockDatabaseLookup).updateField("patientData", "age", 25, "id", 1);
        verify(mockDatabaseLookup).updateField("patientData", "gender", "Female", "id", 1);
        verify(mockDatabaseLookup).updateField("patientData", "diagnosis", "New Diagnosis", "id", 1);
        verify(mockDatabaseLookup).updateField("patientData", "doctorInCharge", "Dr. Johnson", "id", 1);
        verify(mockDatabaseLookup).updateField("patientData", "ward", "Ward B", "id", 1);
        verify(mockDatabaseLookup).updateField("patientData", "roomNum", 102, "id", 1);
        verify(mockDatabaseLookup).updateField("patientData", "emergencyContact", 9876543210, "id", 1);
    }

    @Test
    public void testGetProcedureDetails() {
        when(mockDatabaseLookup.retrieveData("procedureHistorySchedule")).thenReturn(Arrays.asList(
                createProcedureRecord(1, "2023-09-01 10:00:00", "Procedure A")
        ));

        List<String> procedureDetails = patient.getProcedureDetails();

        assertEquals(Arrays.asList("2023-09-01 10:00:00 - Procedure A"), procedureDetails);
    }

    private Map<String, Object> createPatientRecord(int patientId, String name, int age, String gender, String diagnosis, String diagnosisDate, String doctorInCharge, String ward, int roomNum) {
        Map<String, Object> record = new HashMap<>();
        record.put("id", patientId);
        record.put("name", name);
        record.put("age", age);
        record.put("gender", gender);
        record.put("diagnosis", diagnosis);
        record.put("diagnosisDate", diagnosisDate);
        record.put("doctorInCharge", doctorInCharge);
        record.put("ward", ward);
        record.put("roomNum", roomNum);
        return record;
    }

    private Map<String, Object> createInterventionRecord(int patientId, String time, String handledBy, String position, int length) {
        Map<String, Object> record = new HashMap<>();
        record.put("patient_id", patientId);
        record.put("intervention_time", time);
        record.put("handled_by", handledBy);
        record.put("position", position);
        record.put("timeMinutes", length);
        return record;
    }

    private Map<String, Object> createProcedureRecord(int patientId, String time, String procedure) {
        Map<String, Object> record = new HashMap<>();
        record.put("patient_id", patientId);
        record.put("time", time);
        record.put("procedure", procedure);
        return record;
    }

    /* Reference taken from GitHub Copilot */
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
    /* end of reference */
}
