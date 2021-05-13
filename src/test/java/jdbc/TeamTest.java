package jdbc;


import exception.TeamNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import java.sql.ResultSet;
import java.sql.SQLException;
import static org.junit.Assert.*;



public class TeamTest {
    @Before
    public void setUp() throws Exception {
        new DataBaseConnector().getStatement().execute("DELETE FROM TEAM");
    }

    @Test
    public void createTeamAndGet() throws SQLException, ClassNotFoundException {
        /*JDBCTeam jdbcTeam = new JDBCTeam();
        assertThrows(TeamNotFoundException.class, () -> jdbcTeam.selectTeam("KOMANDA"));
        jdbcTeam.saveTeam("KOMANDA");
        ResultSet rs = jdbcTeam.selectTeam("KOMANDA");
        assertTrue(rs.next());
        assertEquals("KOMANDA", rs.getString("name"));*/
    }

    @Test
    public void updateTeam() throws Exception {
        /*JDBCTeam jdbcTeam = new JDBCTeam();
        jdbcTeam.saveTeam("Team");
        ResultSet rs = jdbcTeam.selectTeam("Team");
        rs.next();
        jdbcTeam.updateTeam(rs.getInt("id"), "Team1");
        rs = jdbcTeam.selectTeam("Team1");
        rs.next();
        assertEquals("Team1",rs.getString("name"));*/
    }

    @Test
    public void deleteTeam() throws Exception {
        /*JDBCTeam jdbcTeam = new JDBCTeam();
        jdbcTeam.saveTeam("Team1");
        ResultSet rs = jdbcTeam.selectTeam("Team1");
        rs.next();
        jdbcTeam.deleteTeam(rs.getInt("id"));
        assertThrows(TeamNotFoundException.class ,()->  jdbcTeam.selectTeam("Team1"));*/
    }


}



