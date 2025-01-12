package backend;

import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents a Patient with various attributes and functionalities to retrieve and update their data.
 */
public class Patient {
    private int patientId;
    private DatabaseLookup dbHelper;
    private String tableName = "patientData";

    /**
     * Constructs a Patient instance with the given patient ID.
     *
     * @param patientId the ID of the patient
     */
    public Patient(int patientId) {
        this.patientId = patientId;
        this.dbHelper = new DatabaseLookup();
    }

    /**
     * Gets the patient ID.
     *
     * @return the patient ID
     */
    public Integer getPatientId() {
        return patientId;
    }

    /**
     * Retrieves a list of all intervention times from the intervention history.
     *
     * @return a list of intervention times
     */
    public List<String> getInterventionTime() {
        tableName = "interventionHistory";
        List<Map<String, Object>> data = dbHelper.retrieveData(tableName);
        List<String> interventionTimes = new ArrayList<>();

        for (Map<String, Object> row : data) {
            if (row.get("patient_id") != null && row.get("patient_id").equals(patientId)) {
                interventionTimes.add((String) row.get("intervention_time"));
            }
        }
        return interventionTimes;
    }

    /**
     * Retrieves a list of all nurse names who intervened from the intervention history.
     *
     * @return a list of nurse names
     */
    public List<String> getInterventionHandledBy() {
        tableName = "interventionHistory";
        List<Map<String, Object>> data = dbHelper.retrieveData(tableName);
        List<String> handledByList = new ArrayList<>();

        for (Map<String, Object> row : data) {
            if (row.get("patient_id") != null && row.get("patient_id").equals(patientId)) {
                handledByList.add((String) row.get("handled_by"));
            }
        }
        return handledByList;
    }

    /**
     * Retrieves a list of all positions during interventions from the intervention history.
     *
     * @return a list of intervention positions
     */
    public List<String> getInterventionPosition() {
        tableName = "interventionHistory";
        List<Map<String, Object>> data = dbHelper.retrieveData(tableName);
        List<String> interventionPosition = new ArrayList<>();

        for (Map<String, Object> row : data) {
            if (row.get("patient_id") != null && row.get("patient_id").equals(patientId)) {
                interventionPosition.add((String) row.get("position"));
            }
        }
        return interventionPosition;
    }

    /**
     * Retrieves a list of durations in minutes of positions before intervention from the intervention history.
     *
     * @return a list of durations in minutes
     */
    public List<Integer> getInterventionLength() {
        List<Map<String, Object>> data = dbHelper.retrieveData("interventionHistory");
        List<Integer> interventionLength = new ArrayList<>();

        for (Map<String, Object> row : data) {
            if (row.get("patient_id") != null && row.get("patient_id").equals(patientId)) {
                interventionLength.add((Integer) row.get("timeMinutes"));
            }
        }
        return interventionLength;
    }

    /**
     * Retrieves a list of scheduled procedure times from the procedure history.
     *
     * @return a list of procedure times
     */
    public List<String> getProcedureTime() {
        tableName = "procedureHistory";
        List<Map<String, Object>> data = dbHelper.retrieveData(tableName);
        List<String> procedureTime = new ArrayList<>();

        for (Map<String, Object> row : data) {
            if (row.get("patient_id") != null && row.get("patient_id").equals(patientId)) {
                procedureTime.add((String) row.get("time"));
            }
        }
        return procedureTime;
    }

    /**
     * Retrieves a list of procedure names from the procedure history.
     *
     * @return a list of procedure names
     */
    public List<String> getProcedureName() {
        tableName = "procedureHistory";
        List<Map<String, Object>> data = dbHelper.retrieveData(tableName);
        List<String> procedureName = new ArrayList<>();

        for (Map<String, Object> row : data) {
            if (row.get("patient_id") != null && row.get("patient_id").equals(patientId)) {
                procedureName.add((String) row.get("procedure"));
            }
        }
        return procedureName;
    }

    /**
     * Retrieves the patient's name.
     *
     * @return the patient's name, or null if not found
     */
    public String getName() {
        List<Map<String, Object>> data = dbHelper.retrieveData(tableName);
        for (Map<String, Object> row : data) {
            if (row.get("id") != null && row.get("id").equals(patientId)) {
                return (String) row.get("name");
            }
        }
        return null;
    }

    /**
     * Retrieves the patient's image as a byte array from the SQL database.
     *
     * @return the patient's image as a byte array, or null if not found
     */
    public byte[] getImageAsBytes() {
        String query = "SELECT image FROM " + tableName + " WHERE id = ?";
        try (
                Connection connection = dbHelper.connect();
                PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setInt(1, patientId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getBytes("image");
            }
        } catch (Exception e) {
            System.err.println("Error retrieving image: " + e.getMessage());
        }
        return null;
    }

    /**
     * Retrieves the patient's age.
     *
     * @return the patient's age, or null if not found
     */
    public Integer getAge() {
        List<Map<String, Object>> data = dbHelper.retrieveData(tableName);
        for (Map<String, Object> row : data) {
            if (row.get("id") != null && row.get("id").equals(patientId)) {
                return (Integer) row.get("age");
            }
        }
        return null;
    }

    /**
     * Retrieves the patient's gender.
     *
     * @return the patient's gender, or null if not found
     */
    public String getGender() {
        List<Map<String, Object>> data = dbHelper.retrieveData(tableName);
        for (Map<String, Object> row : data) {
            if (row.get("id") != null && row.get("id").equals(patientId)) {
                return (String) row.get("gender");
            }
        }
        return null;
    }

    /**
     * Retrieves the patient's diagnosis.
     *
     * @return the patient's diagnosis, or null if not found
     */
    public String getDiagnosis() {
        List<Map<String, Object>> data = dbHelper.retrieveData(tableName);
        for (Map<String, Object> row : data) {
            if (row.get("id") != null && row.get("id").equals(patientId)) {
                return (String) row.get("diagnosis");
            }
        }
        return null;
    }

    /**
     * Retrieves the patient's current posture position based on the given time.
     *
     *
     * @return a list of posture positions
     */
    public List<String> getPosture() {
        tableName = "postureHistory";
        List<Map<String, Object>> data = dbHelper.retrieveData(tableName);
        List<String> posturePositions = new ArrayList<>();

        for (Map<String, Object> row : data) {
            if (row.get("patient_id") != null && row.get("patient_id").equals(patientId)) {
                posturePositions.add((String) row.get("posture_position"));
            }
        }
        return posturePositions;
    }

    /**
     * Retrieves the patient's medication details.
     *
     * @return the medication details as a string, or null if not found
     */
    public String getMedicationDetails() {
        tableName = "medicationData";
        List<Map<String, Object>> data = dbHelper.retrieveData(tableName);
        StringBuilder medicationDetails = new StringBuilder();
        boolean medicationFound = false;
        for (Map<String, Object> row : data) {
            if (row.get("patient_id") != null && row.get("patient_id").equals(patientId)) {
                medicationFound = true;
                medicationDetails.append(row.get("medication"))
                        .append(", ")
                        .append(row.get("dose_mg"))
                        .append(" mg, ")
                        .append(row.get("dose_frequency_daily"))
                        .append("xDaily (")
                        .append(row.get("start_date"))
                        .append(")\n");
                return medicationDetails.toString();
            }
        }
        return null;
    }

    /**
     * Retrieves the patient's room number.
     *
     * @return the room number, or 0 if not found
     */
    public int getRoomNumber() {
        List<Map<String, Object>> data = dbHelper.retrieveData(tableName);
        for (Map<String, Object> row : data) {
            if (row.get("id") != null && row.get("id").equals(patientId)) {
                return (int) row.get("roomNum");
            }
        }
        return 0;
    }

    /**
     * Retrieves the patient's ward.
     *
     * @return the ward, or null if not found
     */
    public String getWard() {
        List<Map<String, Object>> data = dbHelper.retrieveData(tableName);
        for (Map<String, Object> row : data) {
            if (row.get("id") != null && row.get("id").equals(patientId)) {
                return (String) row.get("ward");
            }
        }
        return null;
    }

    /**
     * Retrieves the patient's emergency contact number.
     *
     * @return the emergency contact number as a string, or null if not found
     */
    public String getContactNumber() {
        List<Map<String, Object>> data = dbHelper.retrieveData(tableName);
        for (Map<String, Object> row : data) {
            if (row.get("id") != null && row.get("id").equals(patientId)) {
                Object contactNumber = row.get("emergencyContact");
                if (contactNumber != null) {
                    return contactNumber.toString();
                }
            }
        }
        return null;
    }

    /**
     * Retrieves the name of the doctor in charge.
     *
     * @return the doctor's name, or null if not found
     */
    public String getDocInCharge() {
        List<Map<String, Object>> data = dbHelper.retrieveData(tableName);
        for (Map<String, Object> row : data) {
            if (row.get("id") != null && row.get("id").equals(patientId)) {
                return (String) row.get("doctorInCharge");
            }
        }
        return null;
    }

    /**
     * Retrieves the date of the patient's diagnosis.
     *
     * @return the diagnosis date, or null if not found
     */
    public String getDiagnosisDate() {
        List<Map<String, Object>> data = dbHelper.retrieveData(tableName);
        for (Map<String, Object> row : data) {
            if (row.get("id") != null && row.get("id").equals(patientId)) {
                return (String) row.get("diagnosisDate");
            }
        }
        return null;
    }

    /**
     * Retrieves all patient details.
     *
     * @return a formatted string with patient details
     */
    public String getPatientDetails() {
        return "Age: " + getAge() + "\n" +
                "Gender: " + getGender() + "\n" +
                "Diagnosis: " + getDiagnosis() + "\n" +
                "Diagnosis Date: " + getDiagnosisDate() + "\n" +
                "Emergency Contact Number: " + getContactNumber() + "\n" +
                "Doctor in charge: " + getDocInCharge();
    }

    /*** Set methods ***/

    /**
     * Updates the patient's name.
     *
     * @param name the new name of the patient
     */
    public void setName(String name) {
        dbHelper.updateField(tableName, "name", name, "id", patientId);
    }

    /**
     * Updates the patient's image path.
     *
     * @param imagePath the new image path
     */
    public void setImagePath(String imagePath) {
        dbHelper.updateField(tableName, "imagePath", imagePath, "id", patientId);
    }

    /**
     * Updates the patient's age.
     *
     * @param age the new age of the patient
     */
    public void setAge(int age) {
        dbHelper.updateField(tableName, "age", age, "id", patientId);
    }

    /**
     * Updates the patient's gender.
     *
     * @param gender the new gender of the patient
     */
    public void setGender(String gender) {
        dbHelper.updateField(tableName, "gender", gender, "id", patientId);
    }

    /**
     * Updates the patient's diagnosis.
     *
     * @param diagnosis the new diagnosis
     */
    public void setDiagnosis(String diagnosis) {
        dbHelper.updateField(tableName, "diagnosis", diagnosis, "id", patientId);
    }

    /**
     * Updates the patient's posture position.
     *
     * @param posture the new posture position
     */
    public void setPosture(String posture) {
        tableName = "postureHistory";
        dbHelper.updateField(tableName, "posture_position", posture, "id", patientId);
    }

    /**
     * Updates the patient's room number.
     *
     * @param roomNumber the new room number
     */
    public void setRoomNumber(int roomNumber) {
        dbHelper.updateField(tableName, "roomNum", roomNumber, "id", patientId);
    }

    /**
     * Updates the patient's ward.
     *
     * @param roomNumber the new ward
     */
    public void setWard(String roomNumber) {
        dbHelper.updateField(tableName, "ward", roomNumber, "id", patientId);
    }

    /**
     * Updates the patient's emergency contact number.
     *
     * @param contactNumber the new emergency contact number
     */
    public void setContactNumber(String contactNumber) {
        dbHelper.updateField(tableName, "emergencyContact", contactNumber, "id", patientId);
    }
}
