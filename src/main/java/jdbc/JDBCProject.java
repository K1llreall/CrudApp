package jdbc;


import exception.ProjectNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCProject {
    public void saveProject(String projectName) throws SQLException, ClassNotFoundException {
        new DataBaseConnector().getStatement().execute(String.format("INSERT INTO project (project_name)VALUES ('%s');", projectName));

    }

    public ResultSet selectProject(String projectName) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = new DataBaseConnector().getStatement().executeQuery(String.format("SELECT * FROM project WHERE project_name = '%s'", projectName));
        if(!resultSet.isBeforeFirst()) {
            throw new ProjectNotFoundException("Проект не найден");
        } else {
            return resultSet;
        }
    }

    public void updateProject(int id,String projectName) throws SQLException, ClassNotFoundException {
        new DataBaseConnector().getStatement().execute(String.format("UPDATE project SET project_name ='%s' WHERE id= '%s'" , projectName, id));
    }

    public void deleteProject(int id) throws SQLException, ClassNotFoundException {
        new DataBaseConnector().getStatement().execute(String.format("DELETE FROM project WHERE id = '%s';", id));
    }
}