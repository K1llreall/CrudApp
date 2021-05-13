package jdbc;

import exception.ProjectNotFoundException;
import exception.TeamNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;




public class ProjectTest {
    @Before
    public void setUp() throws Exception {
        new DataBaseConnector().getStatement().execute("DELETE FROM project");
    }

    @Test
    public void createProjectAndGet() throws SQLException, ClassNotFoundException {
        /*JDBCProject jdbcProject = new JDBCProject();
        assertThrows(ProjectNotFoundException.class, () -> jdbcProject.selectProject("first project"));
        jdbcProject.saveProject("first project");
        ResultSet rs = jdbcProject.selectProject("first project");
        assertTrue(rs.next());
        assertEquals("first project", rs.getString("project_name"));*/
    }

    @Test
    public void updateProject() throws Exception {
        /*JDBCProject jdbcProject = new JDBCProject();
        jdbcProject.saveProject("Helios");
        ResultSet rs = jdbcProject.selectProject("Helios");
        rs.next();
        jdbcProject.updateProject(rs.getInt("id"), "Helios");
        rs = jdbcProject.selectProject("Helios");
        rs.next();
        assertEquals("Helios", rs.getString("project_name"));*/
    }

    @Test
    public void deleteProject() throws Exception {
        /*JDBCProject jdbcProject = new JDBCProject();
        jdbcProject.saveProject("Gunz");
        ResultSet rs = jdbcProject.selectProject("Gunz");
        rs.next();
        jdbcProject.deleteProject(rs.getInt("id"));
        assertThrows(ProjectNotFoundException.class, () -> jdbcProject.selectProject("Gunz"));*/
    }


}


