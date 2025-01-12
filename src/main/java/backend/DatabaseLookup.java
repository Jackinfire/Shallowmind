package backend;
import java.sql.*;
import java.util.*;
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

    private static final String DB_URL = "jdbc:sqlite:/Users/divijvhanda/Desktop/patients.db";

    // Establish connection to the database
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

    // Retrieve data from any table
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

    // Update a specific field in a table
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
}
