package jdbc;

import entitys.Team;
import exception.TeamNotFoundException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class JDBCTeam {
    public void saveTeam(Team team) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = new DataBaseConnector().getPreparedStatement("INSERT INTO team(name) VALUES (?);");
        preparedStatement.setString(1, team.getName());
        preparedStatement.executeUpdate();
    }

    public ResultSet selectTeam(String name) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = new DataBaseConnector().getStatement().executeQuery(String.format("SELECT * FROM team where name = '%s'", name));
        if(!resultSet.isBeforeFirst()) {
            throw new TeamNotFoundException("Команда не найдена");
        } else {
            return resultSet;
        }
    }

    public void updateTeam(int id ,String name) throws SQLException, ClassNotFoundException {
        new DataBaseConnector().getStatement().execute(String.format("UPDATE team SET name = '%s' WHERE id = '%s';", name,id));
    }

    public void deleteTeam(String name) throws SQLException, ClassNotFoundException {
        new DataBaseConnector().getStatement().execute(String.format("DELETE FROM team WHERE name = '%s';",name));
    }
}
