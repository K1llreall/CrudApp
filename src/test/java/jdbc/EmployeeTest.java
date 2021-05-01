package jdbc;

import exception.EmployeeNotFoundException;
import exception.ProjectNotFoundException;
import org.junit.Before;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class EmployeeTest {
    @Before
    public void setUp() throws Exception {
        new DataBaseConnector().getStatement().execute("DELETE FROM employee");
    }

    @Test
    public void createEmployeeAndGet() throws SQLException, ClassNotFoundException {
        JDBCEmployee jdbcEmployee = new JDBCEmployee();
        assertThrows(EmployeeNotFoundException.class, () -> jdbcEmployee.selectEmployee("Billy"));
        jdbcEmployee.saveEmployee("Billy");
        ResultSet rs = jdbcEmployee.selectEmployee("Billy");
        assertTrue(rs.next());
        assertEquals("Billy", rs.getString("name"));
    }

    @Test
    public void updateEmployee() throws Exception {
        JDBCEmployee jdbcEmployee= new JDBCEmployee();
        jdbcEmployee.saveEmployee("Jorge");
        ResultSet rs = jdbcEmployee.selectEmployee("Jorge");
        rs.next();
        jdbcEmployee.updateEmployee(rs.getInt("id"), "George");
        rs = jdbcEmployee.selectEmployee("George");
        rs.next();
        assertEquals("George", rs.getString("name"));
    }

    @Test
    public void deleteEmployee() throws Exception {
        JDBCEmployee jdbcEmployee = new JDBCEmployee();
        jdbcEmployee.saveEmployee("Goga");
        ResultSet rs = jdbcEmployee.selectEmployee("Goga");
        rs.next();
        jdbcEmployee.deleteEmployee(rs.getInt("id"));
        assertThrows(EmployeeNotFoundException.class, () -> jdbcEmployee.selectEmployee("Goga"));
    }

}
