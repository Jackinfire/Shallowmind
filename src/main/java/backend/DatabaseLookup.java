package backend;

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

/**
 * The {@code DatabaseLookup} class provides methods to interact with a SQLite database that will be from the device
 * It allows connecting to the database, retrieving data from tables, updating fields,
 * and retrieving the number of patients.
 */
public class DatabaseLookup {

    /**
     * The location for the SQLite database on the specific device it is running on.
     */
    private static final String DB_URL = "jdbc:sqlite:src/main/patients_long_1hr.db";

    /**
     * Establishes a connection to the SQLite database.
     *
     * @return A {@link Connection} object representing the database connection,
     *         or {@code null} if the connection fails.
     */
    public Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL);

        } catch (SQLException e) {
            System.err.println("Failed to connect to the database: " + e.getMessage());
        }
        return connection;
    }

    /**
     * Retrieves all rows from the specified table in the database.
     *
     * @param tableName The name of the table to retrieve data from.
     * @return A list of maps, where each map represents a row in the table. Each map contains
     *         column names as keys and their corresponding values as map values.
     */
    public List<Map<String, Object>> retrieveData(String tableName) {
        String query = "SELECT * FROM " + tableName; // Build the query dynamically
        List<Map<String, Object>> results = new ArrayList<>();

        try (
                Connection connection = connect();
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()
        ) {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

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

    /**
     * Updates a specific field in a table based on a condition.
     *
     * @param tableName       The name of the table to update.
     * @param columnName      The name of the column to update.
     * @param newValue        The new value to set.
     * @param conditionColumn The column to match for the condition.
     * @param conditionValue  The value to match for the condition.
     */
    public void updateField(String tableName, String columnName, Object newValue, String conditionColumn, Object conditionValue) {
        String query = "UPDATE " + tableName + " SET " + columnName + " = ? WHERE " + conditionColumn + " = ?";

        try (
                Connection connection = connect();
                PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setObject(1, newValue);
            statement.setObject(2, conditionValue);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Field updated successfully in table: " + tableName);
            }
        } catch (SQLException e) {
            System.err.println("Error while updating field: " + e.getMessage());
        }
    }

    /**
     * Retrieves the total number of patients in the {@code patientData} table.
     *
     * @return The total number of rows in the {@code patientData} table.
     */
    public int getNumberOfPatients() {
        String query = "SELECT COUNT(*) AS total FROM patientData";
        int totalPatients = 0;

        try (
                Connection connection = connect();
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()
        ) {
            if (resultSet.next()) {
                totalPatients = resultSet.getInt("total");
            }
        } catch (SQLException e) {
            System.err.println("Error while retrieving the number of patients: " + e.getMessage());
        }

        return totalPatients;
    }

    /**
     * Adds a new patient to the patientData table.
     *
     * @param name              Patient's name
     * @param age               Patient's age
     * @param gender            Patient's gender
     * @param emergencyContact  Patient's emergency contact number
     * @param diagnosis         Patient's diagnosis
     * @param diagnosisDate     Date of diagnosis
     * @param doctorInCharge    Doctor in charge
     * @param ward              Patient's ward
     * @param roomNum           Patient's room number
     * @return True if the patient was added successfully; false otherwise
     */
    public boolean addNewPatient(String name, int age, String gender, String emergencyContact, String diagnosis,
                                 String diagnosisDate, String doctorInCharge, String ward, int roomNum) {
        String query = "INSERT INTO patientData (name, age, gender, emergencyContact, diagnosis, diagnosisDate, " +
                "doctorInCharge, ward, roomNum, image) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = connect();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, name);
            statement.setInt(2, age);
            statement.setString(3, gender);
            statement.setString(4, emergencyContact);
            statement.setString(5, diagnosis);
            statement.setString(6, diagnosisDate);
            statement.setString(7, doctorInCharge);
            statement.setString(8, ward);
            statement.setInt(9, roomNum);


            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("New patient added successfully.");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error adding new patient: " + e.getMessage());
        }

        return false;
    }

    public boolean deletePatientData(int patientId) {
        String deletePatientQuery = "DELETE FROM patientData WHERE id = ?";
        String deleteProcedureHistoryQuery = "DELETE FROM procedureHistorySchedule WHERE patient_id = ?";
        String deletePostureHistoryQuery = "DELETE FROM postureHistory WHERE patient_id = ?";
        String deleteMedicationDataQuery = "DELETE FROM medicationData WHERE patient_id = ?";
        String deleteInterventionHistoryQuery = "DELETE FROM interventionHistory WHERE patient_id = ?";

        try (Connection connection = connect()) {
            // Begin transaction
            connection.setAutoCommit(false); // so that  can group multiple SQL statements into a single transaction,

            try {
                // Delete from patientData
                PreparedStatement patientStatement = connection.prepareStatement(deletePatientQuery);
                patientStatement.setInt(1, patientId);

                // Delete from procedureHistorySchedule
                PreparedStatement procedureStatement = connection.prepareStatement(deleteProcedureHistoryQuery);
                procedureStatement.setInt(1, patientId);
                procedureStatement.executeUpdate();

                // Delete from postureHistory
                PreparedStatement postureStatement = connection.prepareStatement(deletePostureHistoryQuery);
                postureStatement.setInt(1, patientId);
                postureStatement.executeUpdate();

                // Delete from medicationData
                PreparedStatement medicationStatement = connection.prepareStatement(deleteMedicationDataQuery);
                medicationStatement.setInt(1, patientId);
                medicationStatement.executeUpdate();

                // Delete from interventionHistory
                PreparedStatement interventionStatement = connection.prepareStatement(deleteInterventionHistoryQuery);
                interventionStatement.setInt(1, patientId);
                interventionStatement.executeUpdate();

                // Commit transaction
                connection.commit();

            } catch (SQLException e) {
                connection.rollback(); // Rollback transaction in case of error
                System.err.println("Error deleting patient and related data: " + e.getMessage());
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
        }
        return false;
    }

}
