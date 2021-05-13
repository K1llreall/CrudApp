package entitys;

import lombok.Data;

import java.sql.Date;


@Data
public class Feedback {
    public Feedback(){

    }

    private int id;
    private String description;
    private Date feedbackDate;

}
