import jdbc.DataBaseConnector;
import jdbc.JDBCExecutor;
import jdbc.JDBCTeam;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        JDBCExecutor jdbcExecutor = new JDBCExecutor();
        jdbcExecutor.initDataBase();
        JDBCTeam jdbcTeam= new JDBCTeam();
        jdbcTeam.updateTeam(2,"Yohoho");
        jdbcTeam.deleteTeam(3);
    }
}
