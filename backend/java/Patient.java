import java.util.ArrayList;
import java.util.HashMap;
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

    public Integer getPatientId() {
        return patientId;
    }

    public String getInterventionTime() {
        List<Map<String, Object>> data = dbHelper.retrieveData(tableName);
        for (Map<String, Object> row : data) {
            if (row.get("id") != null && row.get("id").equals(patientId)) {
                return (String) row.get("interventionTime");
            }
        }
        return null;
    }

    public String getInterventionHandledBy() {
        List<Map<String, Object>> data = dbHelper.retrieveData(tableName);
        for (Map<String, Object> row : data) {
            if (row.get("id") != null && row.get("id").equals(patientId)) {
                return (String) row.get("interventionHandledBy");
            }
        }
        return null;
    }

    public String getSchedule() {
        List<Map<String, Object>> data = dbHelper.retrieveData(tableName);
        for (Map<String, Object> row : data) {
            if (row.get("id") != null && row.get("id").equals(patientId)) {
                return (String) row.get("schedule");
            }
        }
        return null;
    }

    public String getName() {
        List<Map<String, Object>> data = dbHelper.retrieveData(tableName);
        for (Map<String, Object> row : data) {
            if (row.get("id") != null && row.get("id").equals(patientId)) {
                return (String) row.get("name");
            }
        }
        return null;
    }

    public String getImagePath() {
        List<Map<String, Object>> data = dbHelper.retrieveData(tableName);
        for (Map<String, Object> row : data) {
            if (row.get("id") != null && row.get("id").equals(patientId)) {
                return (String) row.get("imagePath");
            }
        }
        return null;
    }

    public Integer getAge() {
        List<Map<String, Object>> data = dbHelper.retrieveData(tableName);
        for (Map<String, Object> row : data) {
            if (row.get("id") != null && row.get("id").equals(patientId)) {
                return (Integer) row.get("age");
            }
        }
        return null;
    }

    public String getGender() {
        List<Map<String, Object>> data = dbHelper.retrieveData(tableName);
        for (Map<String, Object> row : data) {
            if (row.get("id") != null && row.get("id").equals(patientId)) {
                return (String) row.get("gender");
            }
        }
        return null;
    }

    public String getDiagnosis() {
        List<Map<String, Object>> data = dbHelper.retrieveData(tableName);
        for (Map<String, Object> row : data) {
            if (row.get("id") != null && row.get("id").equals(patientId)) {
                return (String) row.get("diagnosis");
            }
        }
        return null;
    }

    public String getPosture() {
        List<Map<String, Object>> data = dbHelper.retrieveData(tableName);
        for (Map<String, Object> row : data) {
            if (row.get("id") != null && row.get("id").equals(patientId)) {
                return (String) row.get("posture");
            }
        }
        return null;
    }

    public String getMedication() {
        List<Map<String, Object>> data = dbHelper.retrieveData(tableName);
        for (Map<String, Object> row : data) {
            if (row.get("id") != null && row.get("id").equals(patientId)) {
                return (String) row.get("medication");
            }
        }
        return null;
    }

    public String getRoomNumber() {
        List<Map<String, Object>> data = dbHelper.retrieveData(tableName);
        for (Map<String, Object> row : data) {
            if (row.get("id") != null && row.get("id").equals(patientId)) {
                return (String) row.get("roomNumber");
            }
        }
        return null;
    }

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


    public String getDocInCharge() {
        List<Map<String, Object>> data = dbHelper.retrieveData(tableName);
        for (Map<String, Object> row : data) {
            if (row.get("id") != null && row.get("id").equals(patientId)) {
                return (String) row.get("doctorInCharge");
            }
        }
        return null;
    }

    public String getDiagnosisDate() {
        List<Map<String, Object>> data = dbHelper.retrieveData(tableName);
        for (Map<String, Object> row : data) {
            if (row.get("id") != null && row.get("id").equals(patientId)) {
                return (String) row.get("diagnosisDate");
            }
        }
        return null;
    }

    public String getPatientDetails() {
        return "Age: " + getAge() + "\n" +
                "Gender: " + getGender() + "\n" +
                "Diagnosis: " + getDiagnosis() + "\n" +
                "Diagnosis Date: " + getDiagnosisDate() + "\n" +
                "Posture: " + getPosture() + "\n" +
                "Emergency Contact Number: " + getContactNumber() + "\n" +
                "Doctor in charge: " + getDocInCharge();
    }

    /*** Set methods ***/

    public void setName(String name) {
        dbHelper.updateField(tableName, "name", name, "id", patientId);
    }

    public void setImagePath(String imagePath) {
        dbHelper.updateField(tableName, "imagePath", imagePath, "id", patientId);
    }

    public void setAge(int age) {
        dbHelper.updateField(tableName, "age", age, "id", patientId);
    }

    public void setGender(String gender) {
        dbHelper.updateField(tableName, "gender", gender, "id", patientId);
    }

    public void setDiagnosis(String diagnosis) {
        dbHelper.updateField(tableName, "diagnosis", diagnosis, "id", patientId);
    }

    public void setPosture(String posture) {
        dbHelper.updateField(tableName, "posture", posture, "id", patientId);
    }

    public void setRoomNumber(String roomNumber) {
        dbHelper.updateField(tableName, "roomNumber", roomNumber, "id", patientId);
    }

    public void setContactNumber(String contactNumber) {
        dbHelper.updateField(tableName, "emergencyContact", contactNumber, "id", patientId);
    }

    /** Other methods **/

    public void updatePosture(String newPosture) {
        setPosture(newPosture); // Reuse setter method
    }


}
