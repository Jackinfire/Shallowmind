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

public class Patient {
    private int patientId;
    private DatabaseLookup dbHelper;
    private String tableName = "patientData";

    public Patient(int patientId) {
        this.patientId = patientId;
        this.dbHelper = new DatabaseLookup();
    }

    /*** Get methods **/

    // function to get patient id
    public Integer getPatientId() {
        return patientId;
    }

    //Function that creates a list of type string containing all the intervention times from the intervention history
    public List<String> getInterventionTime() {
        tableName="interventionHistory";
        List<Map<String, Object>> data = dbHelper.retrieveData(tableName);
        List<String> interventionTimes = new ArrayList<>();

        for (Map<String, Object> row : data) {
            if (row.get("patient_id") != null && row.get("patient_id").equals(patientId)) {
                interventionTimes.add((String) row.get("intervention_time"));
            }
        }
        return interventionTimes;
    }

    //Function that creates a list of type string containing all nurse's names who intervened from the intervention history
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

    //Function that creates a list of type string containing all positions when intervened from the intervention history
    public List<String> getInterventionPosition() {
        tableName="interventionHistory";
        List<Map<String, Object>> data = dbHelper.retrieveData(tableName);
        List<String> interventionPosition = new ArrayList<>();

        for (Map<String, Object> row : data) {
            if (row.get("patient_id") != null && row.get("patient_id").equals(patientId)) {
                interventionPosition.add((String) row.get("position"));
            }
        }
        return interventionPosition;
    }

    //Function that creates a list of type Integer containing duration of position before intervention from the intervention history
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

    //Function that creates a list of type string containing schedule of procedure from the procedure history
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

    //Function that creates a list of type string containing all names of procedure from the procedure history
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

    //Function to get the name
    public String getName() {
        List<Map<String, Object>> data = dbHelper.retrieveData(tableName);
        for (Map<String, Object> row : data) {
            if (row.get("id") != null && row.get("id").equals(patientId)) {
                return (String) row.get("name");
            }
        }
        return null;
    }

    //Function to get the image of the patient as a byte file from sql
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

    //function to get patient age as an integer
    public Integer getAge() {
        List<Map<String, Object>> data = dbHelper.retrieveData(tableName);
        for (Map<String, Object> row : data) {
            if (row.get("id") != null && row.get("id").equals(patientId)) {
                return (Integer) row.get("age");
            }
        }
        return null;
    }

    //Function to get gender as a String
    public String getGender() {
        List<Map<String, Object>> data = dbHelper.retrieveData(tableName);
        for (Map<String, Object> row : data) {
            if (row.get("id") != null && row.get("id").equals(patientId)) {
                return (String) row.get("gender");
            }
        }
        return null;
    }

    //Function to get diagnosis name as String
    public String getDiagnosis() {
        List<Map<String, Object>> data = dbHelper.retrieveData(tableName);
        for (Map<String, Object> row : data) {
            if (row.get("id") != null && row.get("id").equals(patientId)) {
                return (String) row.get("diagnosis");
            }
        }
        return null;
    }

    //Function to get current posture position as String, after entering the current time
    public List <String> getPosture(String time) {
        tableName="postureHistory";
        List<Map<String, Object>> data = dbHelper.retrieveData(tableName);
        List <String> posturePositions=new ArrayList<>();

        for (Map<String, Object> row : data) {
            if (row.get("patient_id") != null && row.get("patient_id").equals(patientId)) {
                 posturePositions.add((String) row.get("posture_position"));
            }
        }
        return posturePositions;
    }

    // function to return medication details as a string
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

    //function to give you room number of the patient
    public int getRoomNumber() {
        List<Map<String, Object>> data = dbHelper.retrieveData(tableName);
        for (Map<String, Object> row : data) {
            if (row.get("id") != null && row.get("id").equals(patientId)) {
                return (int) row.get("roomNum");
            }
        }
        return 0;
    }

    //function to give you the ward of the patient
    public String getWard() {
        List<Map<String, Object>> data = dbHelper.retrieveData(tableName);
        for (Map<String, Object> row : data) {
            if (row.get("id") != null && row.get("id").equals(patientId)) {
                return (String) row.get("ward");
            }
        }
        return null;
    }

    // Function to give emergency contact number
    public String getContactNumber() {
        List<Map<String, Object>> data = dbHelper.retrieveData(tableName);
        for (Map<String, Object> row : data) {
            if (row.get("id") != null && row.get("id").equals(patientId)) {
                // Retrieve the contact number and convert it to a String
                Object contactNumber = row.get("emergencyContact");
                if (contactNumber != null) {
                    return contactNumber.toString(); // Convert to String
                }
            }
        }
        return null; // Return null if patient not found or contact is null
    }


    //Function to give doctor in charge's name
    public String getDocInCharge() {
        List<Map<String, Object>> data = dbHelper.retrieveData(tableName);
        for (Map<String, Object> row : data) {
            if (row.get("id") != null && row.get("id").equals(patientId)) {
                return (String) row.get("doctorInCharge");
            }
        }
        return null;
    }

    //Function to give the date of diagnosis
    public String getDiagnosisDate() {
        List<Map<String, Object>> data = dbHelper.retrieveData(tableName);
        for (Map<String, Object> row : data) {
            if (row.get("id") != null && row.get("id").equals(patientId)) {
                return (String) row.get("diagnosisDate");
            }
        }
        return null;
    }

    //function to give all patient details
    public String getPatientDetails() {
        return "Age: " + getAge() + "\n" +
                "Gender: " + getGender() + "\n" +
                "Diagnosis: " + getDiagnosis() + "\n" +
                "Diagnosis Date: " + getDiagnosisDate() + "\n" +
                "Emergency Contact Number: " + getContactNumber() + "\n" +
                "Doctor in charge: " + getDocInCharge();
    }

    /*** Set methods ***/

    //Function to update name of patient
    public void setName(String name) {
        dbHelper.updateField(tableName, "name", name, "id", patientId);
    }

    //Function to update image path of patient
    public void setImagePath(String imagePath) {
        dbHelper.updateField(tableName, "imagePath", imagePath, "id", patientId);
    }

    //function to update patient age
    public void setAge(int age) {
        dbHelper.updateField(tableName, "age", age, "id", patientId);
    }

    //function to update gender of patient
    public void setGender(String gender) {
        dbHelper.updateField(tableName, "gender", gender, "id", patientId);
    }

    //function to update diagnosis of patient
    public void setDiagnosis(String diagnosis) {
        dbHelper.updateField(tableName, "diagnosis", diagnosis, "id", patientId);
    }

    //function to update posture position of patient
    public void setPosture(String posture) {
        tableName="postureHistory";
        dbHelper.updateField(tableName, "posture_position", posture, "id", patientId);
    }

    //function to update room number of the patient
    public void setRoomNumber(int roomNumber) {
        dbHelper.updateField(tableName, "roomNum", roomNumber, "id", patientId);
    }

    //function to update ward of patient
    public void setWard(String roomNumber) {
        dbHelper.updateField(tableName, "ward", roomNumber, "id", patientId);
    }

    //function to update emergency contact number of patient
    public void setContactNumber(String contactNumber) {
        dbHelper.updateField(tableName, "emergencyContact", contactNumber, "id", patientId);
    }

}

