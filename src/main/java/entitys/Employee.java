package entitys;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private int id;
    private String surname;
    private String name;
    private String patronymic;
    private String fio;
    private String email;
    private String phoneNumber;
    private LocalDate birthday;
    private float experience;
    private LocalDate hiringDay;
    private int projectId;
    private DeveloperLevel developerLevel;
    private String englishLevel;
    private String skype;
    private int teamId;
    private int feedbackId ;

    public enum DeveloperLevel {
        j1, j2, m1, m2, m3, s1, s2
    }
}
