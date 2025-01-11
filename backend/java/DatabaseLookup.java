import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseLookup {


    private static final String DB_URL = "jdbc:sqlite:/Users/ommahajan/Desktop/Software module/Shallowmind_working/patients.db";


    public Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL);
            System.out.println("Connection established successfully (SQLite).");
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database: " + e.getMessage());
        }
        return connection;
    }

    // ------------------------------------------------------
    // 2. Generic method to retrieve data from any table
    // ------------------------------------------------------
    public List<Map<String, Object>> retrieveData(String tableName) {
        String query = "SELECT * FROM " + tableName; // Build the query dynamically
        List<Map<String, Object>> results = new ArrayList<>();

        try (
                Connection connection = connect();
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()
        ) {
            // Get column metadata for dynamic mapping
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Process each row in the ResultSet
            while (resultSet.next()) {
                Map<String, Object> row = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object value = resultSet.getObject(i);
                    row.put(columnName, value);
                }
                results.add(row);
            }

        } catch (SQLException e) {
            System.err.println("Error while retrieving data: " + e.getMessage());
        }

        return results;
    }

    // ------------------------------------------------------
    // 3. Insert data into postureHistory
    // ------------------------------------------------------
    public void insertPostureHistory(int patientId, String time, String posturePosition) {
        // Based on columns: (patient_id, time, posture_position)
        String query = "INSERT INTO postureHistory (patient_id, time, posture_position) VALUES (?, ?, ?)";

        try (
                Connection connection = connect();
                PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setInt(1, patientId);
            statement.setString(2, time);
            statement.setString(3, posturePosition);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Inserted into postureHistory successfully!");
            }
        } catch (SQLException e) {
            System.err.println("Error while inserting into postureHistory: " + e.getMessage());
        }
    }

    // ------------------------------------------------------
    // 4. Insert data into interventionHistory
    // ------------------------------------------------------
    public void insertInterventionHistory(int patientId, String interventionTime, String handledBy) {
        // Based on columns: (patient_id, intervention_time, handled_by)
        String query = "INSERT INTO interventionHistory (patient_id, intervention_time, handled_by) VALUES (?, ?, ?)";

        try (
                Connection connection = connect();
                PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setInt(1, patientId);
            statement.setString(2, interventionTime);
            statement.setString(3, handledBy);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Inserted into interventionHistory successfully!");
            }
        } catch (SQLException e) {
            System.err.println("Error while inserting into interventionHistory: " + e.getMessage());
        }
    }

    // ------------------------------------------------------
    // 5. Insert data into patients
    // ------------------------------------------------------
    public void insertPatient(
            int id,                  // If "id" is AUTOINCREMENT, you might omit this
            String name,
            int age,
            String gender,
            int emergencyContact,
            String diagnosis,
            String diagnosisDate,
            String doctorInCharge
    ) {
        // Based on columns: (id, name, age, gender, emergencyContact, diagnosis, diagnosisDate, doctorInCharge)
        // If "id" is AUTOINCREMENT, remove it from your INSERT statement (and from parameters).
        String query = "INSERT INTO patients (id, name, age, gender, emergencyContact, diagnosis, diagnosisDate, doctorInCharge) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (
                Connection connection = connect();
                PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setInt(1, id);
            statement.setString(2, name);
            statement.setInt(3, age);
            statement.setString(4, gender);
            statement.setInt(5, emergencyContact);
            statement.setString(6, diagnosis);
            statement.setString(7, diagnosisDate);
            statement.setString(8, doctorInCharge);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Inserted new patient successfully!");
            }
        } catch (SQLException e) {
            System.err.println("Error while inserting into patients: " + e.getMessage());
        }
    }

}
