package jdbc;

import lombok.extern.java.Log;

import java.sql.*;

@Log
public class DataBaseConnector {
    Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        return statement;
    }

    public PreparedStatement getPreparedStatement(String query) throws SQLException {
        return connection.prepareStatement(query);
    }

    Statement statement;

    public DataBaseConnector() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/HT2", "postgres", "postgres");
        statement = connection.createStatement();
        log.info("совершено подключение к БД");}


}
