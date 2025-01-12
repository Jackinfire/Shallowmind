package backend;

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

    public Integer getPatientId() {
        return patientId;
    }

    public List<String> getInterventionTime() {
        List<Map<String, Object>> data = dbHelper.retrieveData("interventionHistory");
        List<String> interventionTimes = new ArrayList<>();

        for (Map<String, Object> row : data) {
            if (row.get("patient_id") != null && row.get("patient_id").equals(patientId)) {
                interventionTimes.add((String) row.get("intervention_time"));
            }
        }
        return interventionTimes;
    }


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


    public List<String> getInterventionPosition() {
        List<Map<String, Object>> data = dbHelper.retrieveData("interventionHistory");
        List<String> interventionPosition = new ArrayList<>();

        for (Map<String, Object> row : data) {
            if (row.get("patient_id") != null && row.get("patient_id").equals(patientId)) {
                interventionPosition.add((String) row.get("position"));
            }
        }
        return interventionPosition;
    }


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

    public String getPosture(String time) {
        tableName="postureHistory";
        List<Map<String, Object>> data = dbHelper.retrieveData(tableName);
        for (Map<String, Object> row : data) {
            if (row.get("id") != null && row.get("id").equals(patientId) && row.get("time").equals(time)) {
                return (String) row.get("posture_position");
            }
        }
        return null;
    }

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

    public int getRoomNumber() {
        List<Map<String, Object>> data = dbHelper.retrieveData(tableName);
        for (Map<String, Object> row : data) {
            if (row.get("id") != null && row.get("id").equals(patientId)) {
                return (int) row.get("roomNum");
            }
        }
        return 0;
    }

    public String getWard() {
        List<Map<String, Object>> data = dbHelper.retrieveData(tableName);
        for (Map<String, Object> row : data) {
            if (row.get("id") != null && row.get("id").equals(patientId)) {
                return (String) row.get("ward");
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
        tableName="postureHistory";
        dbHelper.updateField(tableName, "posture_position", posture, "id", patientId);
    }

    public void setRoomNumber(int roomNumber) {
        dbHelper.updateField(tableName, "roomNum", roomNumber, "id", patientId);
    }

    public void setWard(String roomNumber) {
        dbHelper.updateField(tableName, "ward", roomNumber, "id", patientId);
    }
    public void setContactNumber(String contactNumber) {
        dbHelper.updateField(tableName, "emergencyContact", contactNumber, "id", patientId);
    }

}

