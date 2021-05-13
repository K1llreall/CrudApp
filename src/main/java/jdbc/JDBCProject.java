package jdbc;


import entitys.Project;
import exception.ProjectNotFoundException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCProject {
    public void save(Project project) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = new DataBaseConnector().getPreparedStatement(String.format("INSERT INTO project (project_name,\n" +
                "customer,duration,methodology,project_manager,team_id) VALUES (?,?,?,?,?,?);"));
        preparedStatement.setString(1, project.getProjectName());
        preparedStatement.setString(2,project.getCustomer());
        preparedStatement.setString(3,project.getDuration());
        preparedStatement.setString(4,project.getMethodology());
        preparedStatement.setString(5,project.getProjectManager());
        preparedStatement.setObject(6,project.getTeamId());
        preparedStatement.executeUpdate();
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

    public void deleteProject(String name) throws SQLException, ClassNotFoundException {
        new DataBaseConnector().getStatement().execute(String.format("DELETE FROM project WHERE project_name = '%s';",name ));
    }
}