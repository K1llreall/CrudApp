package jdbc;

import exception.EmployeeNotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCEmployee {
    public void saveEmployee(String name) throws SQLException, ClassNotFoundException {
        new DataBaseConnector().getStatement().execute(String.format("INSERT INTO employee (name)VALUES ('%s');", name));

    }

    public ResultSet selectEmployee(String name) throws SQLException, ClassNotFoundException {
        ResultSet resultSet= new DataBaseConnector().getStatement().executeQuery(String.format("SELECT * FROM employee WHERE name = '%s'", name));
        if (!resultSet.isBeforeFirst()){
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }else {
            return resultSet;
        }
    }

    public void updateEmployee(int id,String name) throws SQLException, ClassNotFoundException {
        new DataBaseConnector().getStatement().execute(String.format("UPDATE employee SET name ='%s' WHERE id= '%s'" , name, id));
    }

    public void deleteEmployee(int id) throws SQLException, ClassNotFoundException {
        new DataBaseConnector().getStatement().execute(String.format("DELETE FROM employee WHERE id = '%s';", id));
    }
}
