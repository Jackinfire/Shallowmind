package backend;

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
    private String tableName;

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
    private <T> List<T> getDataList(String tableName, String dataName, Class<T> type) {
        List<Map<String, Object>> data = dbHelper.retrieveData(tableName);
        List<T> resultList = new ArrayList<>();
        boolean patientFound = false;
        for (Map<String, Object> row : data) {
            if (row.get("patient_id") != null && row.get("patient_id").equals(this.patientId)) {
                patientFound = true;
                Object value = row.get(dataName);
                if (value != null) {
                    // Check if the value matches the desired type
                    if (type.isInstance(value)) {
                        resultList.add(type.cast(value));
                    }
                    else if (type == String.class) {

                        resultList.add(type.cast(value.toString()));
                    }
                }
                else {
                    System.err.println("Patient ID " + this.patientId + " found, but data for '" + dataName + "' is missing.");
                }
            }
        }

        // If no matching patient ID is found
        if (!patientFound) {
            System.err.println("Patient ID " + this.patientId + " not found in table '" + tableName + "'.");
        }

        return resultList;
    }


    private <T> T getData(String tableName, String dataName, Class<T> type) {
        List<Map<String, Object>> data = dbHelper.retrieveData(tableName);
        for (Map<String, Object> row : data) {
            if (row.get("id") != null && row.get("id").equals(patientId)) {
                Object value = row.get(dataName);
                if (value != null) {
                    if (type.isInstance(value)) {
                        return type.cast(value);
                    }
                    if (type == String.class) {
                        return type.cast(value.toString());
                    }
                }
                else {
                    throw new IllegalStateException("Patient ID " + patientId + " found, but data for '" + dataName + "' is not available.");
                }
            }
        }

        throw new IllegalArgumentException("Patient ID " + patientId + " not found in table.");
    }


    /**
     * Retrieves a list of all intervention times from the intervention history.
     *
     * @return a list of intervention times
     */
    public List<String> getInterventionTime() {
        return getDataList("InterventionHistory","intervention_time",String.class);
    }

    /**
     * Retrieves a list of all nurse names who intervened from the intervention history.
     *
     * @return a list of nurse names
     */
    public List<String> getInterventionHandledBy() {
        return getDataList("interventionHistory","handled_by",String.class);
    }

    /**
     * Retrieves a list of all positions during interventions from the intervention history.
     *
     * @return a list of intervention positions
     */
    public List<String> getInterventionPosition() {
        return getDataList("interventionHistory","position",String.class);
    }

    /**
     * Retrieves a list of durations in minutes of positions before intervention from the intervention history.
     *
     * @return a list of durations in minutes
     */
    public List<Integer> getInterventionLength() {
        return getDataList("interventionHistory","timeMinutes",Integer.class);
    }

    /**
     * Retrieves a list of scheduled procedure times from the procedure history.
     *
     * @return a list of procedure times
     */
    public List<String> getProcedureTime() {
        return getDataList("procedureHistorySchedule","time",String.class);
    }

    /**
     * Retrieves a list of procedure names from the procedure history.
     *
     * @return a list of procedure names
     */
    public List<String> getProcedureName() {
        return getDataList("procedureHistorySchedule","procedure",String.class);
    }

    /**
     * Retrieves the patient's name.
     *
     * @return the patient's name, or null if not found
     */
    public String getName() {
        return getData("patientData","name",String.class);
    }

    /**
     * Retrieves the patient's image as a byte array from the SQL database.
     *
     * @return the patient's image as a byte array, or null if not found
     */
    public byte[] getImageAsBytes() {
        String query = "SELECT image FROM " + "patientData" + " WHERE id = ?";
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
        return getData("patientData","age",Integer.class);
    }

    /**
     * Retrieves the patient's gender.
     *
     * @return the patient's gender, or null if not found
     */
    public String getGender() {
        return getData("patientData","gender",String.class);
    }

    /**
     * Retrieves the patient's diagnosis.
     *
     * @return the patient's diagnosis, or null if not found
     */
    public String getDiagnosis() {
        return getData("patientData","diagnosis",String.class);
    }

    /**
     * Retrieves the patient's current posture position based on the given time.
     *
     *
     * @return a list of posture positions
     */
    public List<String> getPosture() {
        return getDataList("postureHistory","posture_position",String.class);
    }

    /**
     * Retrieves the patient's medication details.
     *
     * @return the medication details as a string, or null if not found
     */
    public List<String> getMedicationDetails() {
        tableName = "medicationData";
        List<Map<String, Object>> data = dbHelper.retrieveData(tableName);
        List<String> medicationDetailsList = new ArrayList<>();

        for (Map<String, Object> row : data) {
            if (row.get("patient_id") != null && row.get("patient_id").equals(patientId)) {
                String medicationDetail = row.get("medication") + ", "
                        + row.get("dose_mg") + " mg, "
                        + row.get("dose_frequency_daily") + "xDaily ("
                        + row.get("start_date") + ")";
                medicationDetailsList.add(medicationDetail);
            }
        }

        // Return the list, which will be empty if no medications are found
        return medicationDetailsList;
    }

    /**
     * Retrieves the patient's past and future procedure details.
     *
     * @return the procedure details as a string, or null if not found
     */
    public List<String> getProcedureDetails() {
        tableName = "procedureHistorySchedule";
        List<Map<String, Object>> data = dbHelper.retrieveData(tableName);
        List<String> procedureDetailsList = new ArrayList<>();

        for (Map<String, Object> row : data) {
            if (row.get("patient_id") != null && row.get("patient_id").equals(patientId)) {
                String procedureDetail = row.get("time") + " - "
                        + row.get("procedure");
                procedureDetailsList.add(procedureDetail);
            }
        }

        // Return the list, which will be empty if no medications are found
        return procedureDetailsList;
    }


    /**
     * Retrieves the patient's room number.
     *
     * @return the room number, or 0 if not found
     */
    public int getRoomNumber() {
        return getData("patientData","roomNum",Integer.class);
    }

    /**
     * Retrieves the patient's ward.
     *
     * @return the ward, or null if not found
     */
    public String getWard() {
        return getData("patientData","ward",String.class);
    }

    /**
     * Retrieves the patient's emergency contact number.
     *
     * @return the emergency contact number as a string, or null if not found
     */
    public String getContactNumber() {
        return getData("patientData","emergencyContact",String.class);
    }

    /**
     * Retrieves the name of the doctor in charge.
     *
     * @return the doctor's name, or null if not found
     */
    public String getDocInCharge() {
        return getData("patientData","doctorInCharge",String.class);
    }

    /**
     * Retrieves the date of the patient's diagnosis.
     *
     * @return the diagnosis date, or null if not found
     */
    public String getDiagnosisDate() {
        return getData("patientData","diagnosisDate",String.class);
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
        tableName="patientData";
        dbHelper.updateField(tableName, "name", name, "id", patientId);
    }

    /**
     * Updates the patient's age.
     *
     * @param age the new age of the patient
     */
    public void setAge(int age) {
        tableName="patientData";
        dbHelper.updateField(tableName, "age", age, "id", patientId);
    }

    /**
     * Updates the doctor in charge of the patient.
     *
     * @param docInCharge the new doctor in charge of the patient
     */

    public void setDocInCharge(String docInCharge){
        tableName="patientData";
        dbHelper.updateField(tableName, "gender", docInCharge , "id", patientId);
    }

    /**
     * Updates the patient's gender.
     *
     * @param gender the new gender of the patient
     */
    public void setGender(String gender) {
        tableName="patientData";
        dbHelper.updateField(tableName, "gender", gender, "id", patientId);
    }

    /**
     * Updates the patient's diagnosis.
     *
     * @param diagnosis the new diagnosis
     */
    public void setDiagnosis(String diagnosis) {
        tableName="patientData";
        dbHelper.updateField(tableName, "diagnosis", diagnosis, "id", patientId);
    }

    /**
     * Updates the patient's posture position.
     *
     * @param posture the new posture position
     */
    public void setDiagnosisDate(String posture) {
        tableName = "postureHistory";
        dbHelper.updateField(tableName, "posture_position", posture, "id", patientId);
    }

    /**
     * Updates the patient's room number.
     *
     * @param roomNumber the new room number
     */
    public void setRoomNumber(int roomNumber) {
        tableName="patientData";
        dbHelper.updateField(tableName, "roomNum", roomNumber, "id", patientId);
    }

    /**
     * Updates the patient's ward.
     *
     * @param wardNum the new ward
     */
    public void setWard(String wardNum) {
        tableName="patientData";
        dbHelper.updateField(tableName, "ward", wardNum, "id", patientId);
    }

    /**
     * Updates the patient's emergency contact number.
     *
     * @param contactNumber the new emergency contact number
     */
    public void setContactNumber(String contactNumber) {
        tableName="patientData";
        dbHelper.updateField(tableName, "emergencyContact", Integer.valueOf(contactNumber), "id", patientId);
    }
}