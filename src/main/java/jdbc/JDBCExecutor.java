package jdbc;

import lombok.extern.java.Log;

import java.io.*;
import java.sql.SQLException;

@Log
public class JDBCExecutor {
    public void initDataBase()
            throws IOException, SQLException, ClassNotFoundException {
        log.info("инициализируем базу данных");
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("sql/initDataBase.sql");
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }

        new DataBaseConnector().getStatement().execute(resultStringBuilder.toString());
        log.info("закончили ининициализацию");
    }


}
