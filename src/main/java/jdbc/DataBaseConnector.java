package jdbc;

import lombok.extern.java.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Log
public class DataBaseConnector {
    Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        return statement;
    }

    Statement statement;

    public DataBaseConnector() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/HT2", "postgres", "postgres");
        statement = connection.createStatement();
    log.info("совершено подключение к БД");}


}
