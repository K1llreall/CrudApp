package jdbc;

import exception.EmployeeNotFoundException;
import exception.FeedbackNotFoundException;
import org.junit.Before;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class FeedbackTest {
    @Before
    public void setUp() throws Exception {
        new DataBaseConnector().getStatement().execute("DELETE FROM feedback");
    }

    @Test
    public void createFeedbackAndGet() throws SQLException, ClassNotFoundException {
        JDBCFeedback jdbcFeedback = new JDBCFeedback();
        assertThrows(FeedbackNotFoundException.class, () -> jdbcFeedback.selectFeedback("moving on"));
        jdbcFeedback.saveFeedback("moving on");
        ResultSet rs = jdbcFeedback.selectFeedback("moving on");
        assertTrue(rs.next());
        assertEquals("moving on", rs.getString("description"));
    }

    @Test
    public void updateFeedback() throws Exception {
        JDBCFeedback jdbcFeedback= new JDBCFeedback();
      jdbcFeedback.saveFeedback("moving on");
        ResultSet rs = jdbcFeedback.selectFeedback("moving on");
        rs.next();
        jdbcFeedback.updateFeedback(rs.getInt("id"), "Forward");
        rs = jdbcFeedback.selectFeedback("Forward");
        rs.next();
        assertEquals("Forward", rs.getString("description"));
    }

    @Test
    public void deleteFeedback() throws Exception {
        JDBCFeedback jdbcFeedback= new JDBCFeedback();
        jdbcFeedback.saveFeedback("moving on");
        ResultSet rs = jdbcFeedback.selectFeedback("moving on");
        rs.next();
        jdbcFeedback.deleteFeedback(rs.getInt("id"));
        assertThrows(FeedbackNotFoundException.class, () -> jdbcFeedback.selectFeedback("moving on"));
    }
}
