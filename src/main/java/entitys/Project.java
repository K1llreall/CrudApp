package entitys;

import lombok.Data;

@Data
public class Project {
    public Project(){

    }
    private int id;
    private String projectName;
    private String customer;
    private String duration;
    private String methodology;
    private String projectManager;
    private Integer teamId;

}
