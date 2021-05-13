package entitys;

import lombok.*;

import java.sql.Date;
import java.time.LocalDate;

@Data
public class Employee {
    public Employee() {

    }

    private int id;
    private String surname;
    private String name;
    private String patronymic;
    private String fio;
    private String email;
    private String phoneNumber;
    private Date birthday;
    private float experience;
    private Date hiringDay;
    private int projectId;
    private String developerLevel;
    private String englishLevel;
    private String skype;
    private int teamId;
    private int feedbackId;

    public enum developerLevel {
        j1, j2, m1, m2, m3, s1, s2
    }

    public enum EnglishLevel {
        A0, A1, A2, B1, B2, C1, C2
    }
}
