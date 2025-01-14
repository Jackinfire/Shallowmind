package backend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DatabaseLookupTest {

    private DatabaseLookup databaseLookup;
    private Connection mockConnection;
    private PreparedStatement mockStatement;
    private ResultSet mockResultSet;

    @BeforeEach
    public void setUp() {
        databaseLookup = new DatabaseLookup();
        mockConnection = Mockito.mock(Connection.class);
        mockStatement = Mockito.mock(PreparedStatement.class);
        mockResultSet = Mockito.mock(ResultSet.class);
    }

    @Test
    public void testConnect() throws SQLException {
        DatabaseLookup spyDatabaseLookup = Mockito.spy(databaseLookup);
        doReturn(mockConnection).when(spyDatabaseLookup).connect();

        Connection connection = spyDatabaseLookup.connect();
        assertNotNull(connection);
    }

    @Test
    public void testRetrieveData() throws SQLException {
        DatabaseLookup spyDatabaseLookup = Mockito.spy(databaseLookup);
        doReturn(mockConnection).when(spyDatabaseLookup).connect();
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenReturn(mockResultSet);

        List<Map<String, Object>> mockData = new ArrayList<>();
        Map<String, Object> row = new HashMap<>();
        row.put("column1", "value1");
        row.put("column2", "value2");
        mockData.add(row);

        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getObject("column1")).thenReturn("value1");
        when(mockResultSet.getObject("column2")).thenReturn("value2");

        List<Map<String, Object>> result = spyDatabaseLookup.retrieveData("testTable");
        assertEquals(mockData, result);
    }

    @Test
    public void testUpdateField() throws SQLException {
        DatabaseLookup spyDatabaseLookup = Mockito.spy(databaseLookup);
        doReturn(mockConnection).when(spyDatabaseLookup).connect();
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeUpdate()).thenReturn(1);

        spyDatabaseLookup.updateField("testTable", "column", "newValue", "conditionColumn", "conditionValue");

        verify(mockStatement, times(1)).executeUpdate();
    }

    @Test
    public void testGetNumberOfPatients() throws SQLException {
        DatabaseLookup spyDatabaseLookup = Mockito.spy(databaseLookup);
        doReturn(mockConnection).when(spyDatabaseLookup).connect();
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("total")).thenReturn(5);

        int totalPatients = spyDatabaseLookup.getNumberOfPatients();
        assertEquals(5, totalPatients);
    }

    @Test
    public void testAddNewPatient() throws SQLException {
        DatabaseLookup spyDatabaseLookup = Mockito.spy(databaseLookup);
        doReturn(mockConnection).when(spyDatabaseLookup).connect();
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeUpdate()).thenReturn(1);

        boolean result = spyDatabaseLookup.addNewPatient("John Doe", 30, "Male", "1234567890", "Diagnosis", "2023-09-01", "Dr. Smith", "Ward A", 101);
        assertTrue(result);
    }

    @Test
    public void testDeletePatientData() throws SQLException {
        DatabaseLookup spyDatabaseLookup = Mockito.spy(databaseLookup);
        doReturn(mockConnection).when(spyDatabaseLookup).connect();
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);

        boolean result = spyDatabaseLookup.deletePatientData(1);
        assertTrue(result);
    }
}
