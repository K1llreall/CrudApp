package jdbc;

import exception.TeamNotFoundException;
import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.NoSuchElementException;

public class JDBCTeam {
    public void saveTeam(String name) throws SQLException, ClassNotFoundException {
        new DataBaseConnector().getStatement().execute(String.format("INSERT INTO team (name)VALUES ('%s');", name));

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

    public void deleteTeam(int id) throws SQLException, ClassNotFoundException {
        new DataBaseConnector().getStatement().execute(String.format("DELETE FROM team WHERE id = '%s';", id));
    }
}
