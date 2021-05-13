package jdbc;

import entitys.Employee;
import exception.EmployeeNotFoundException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCEmployee {
    public void save(Employee employee) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = new DataBaseConnector().getPreparedStatement(String.format("INSERT INTO employee (name,surname,patronymic,email,phone_number,\n" +
                "    birthday ,experience,hiringday,developer_level,english_level,skype)VALUES (?,?,?,?,?,?,?,?,?,?,?);"));
        preparedStatement.setString(1,employee.getName());
        preparedStatement.setString(2, employee.getSurname());
        preparedStatement.setString(3, employee.getPatronymic());
        preparedStatement.setString(4, employee.getEmail());
        preparedStatement.setString(5,employee.getPhoneNumber());
        preparedStatement.setDate(6,employee.getBirthday());
        preparedStatement.setFloat(7,employee.getExperience());
        preparedStatement.setDate(8,employee.getHiringDay());
        preparedStatement.setString(9,employee.getDeveloperLevel());
        preparedStatement.setString(10,employee.getEnglishLevel());
        preparedStatement.setString(11, employee.getSkype());



        preparedStatement.executeUpdate();

    }

    public ResultSet select(int id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet= new DataBaseConnector().getStatement().executeQuery(String.format("SELECT * FROM employee WHERE id = '%s'", id));
        if (!resultSet.isBeforeFirst()){
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }else {
            return resultSet;
        }
    }

    public void update(int id,String name) throws SQLException, ClassNotFoundException {
        new DataBaseConnector().getStatement().execute(String.format("UPDATE employee SET name ='%s' WHERE id= '%s'" , name, id));
    }

    public void delete(int id) throws SQLException, ClassNotFoundException {
        new DataBaseConnector().getStatement().execute(String.format("DELETE FROM employee WHERE id = '%s';", id));
    }
}
