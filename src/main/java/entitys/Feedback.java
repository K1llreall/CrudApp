package entitys;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Feedback {
    private int id;
    private String description;
    private LocalDate feedbackDate;
}
