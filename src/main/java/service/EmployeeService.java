package service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entitys.Employee;
import jdbc.JDBCEmployee;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeService {
    private final JDBCEmployee jdbcEmployee;
    private final ObjectMapper objectMapper;

    public EmployeeService() {
        this.jdbcEmployee = new JDBCEmployee();
        this.objectMapper = new ObjectMapper();

        objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
    }

    public String getEmployeeById(int id) throws SQLException, ClassNotFoundException, JsonProcessingException {
        ResultSet rs = jdbcEmployee.select(id);
        List<Employee> employeeList = new ArrayList<>();
        while (rs.next()) {
            Employee employee = new Employee();
            employee.setId(rs.getInt("id"));
            employee.setSurname(rs.getString("surname"));
            employee.setName(rs.getString("name"));
            employee.setPatronymic(rs.getString("patronymic"));
            employee.setEmail(rs.getString("email"));
            employee.setPhoneNumber(rs.getString("phone_number"));
            employee.setBirthday(rs.getDate("birthday"));
            employee.setExperience(rs.getFloat("experience"));
            employee.setHiringDay(rs.getDate("hiringday"));
            employee.setDeveloperLevel(rs.getString("developer_level"));
            employee.setEnglishLevel(rs.getString("english_level"));
            employee.setSkype(rs.getString("skype"));
            employee.setFeedbackId(rs.getInt("feedback_id"));
            employee.setProjectId(rs.getInt("project_id"));
            employee.setTeamId(rs.getInt("team_id"));
            employeeList.add(employee);
        }

        return objectMapper.writeValueAsString(employeeList);
    }

    public void saveEmployee(InputStream requestBody) throws SQLException, ClassNotFoundException, IOException {
        String text = new BufferedReader(
                new InputStreamReader(requestBody, StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));
        Employee employee = objectMapper.readValue(text, Employee.class);
        jdbcEmployee.save(employee);
    }

    public void deleteEmployee(int id) throws SQLException, ClassNotFoundException {
        jdbcEmployee.delete(id);
    }
}
