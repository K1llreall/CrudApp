package jdbc;



import exception.FeedbackNotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCFeedback {
    public void saveFeedback(String description) throws SQLException, ClassNotFoundException {
        new DataBaseConnector().getStatement().execute(String.format("INSERT INTO feedback (description)VALUES ('%s');", description));

    }

    public ResultSet selectFeedback(String description) throws SQLException, ClassNotFoundException {
        ResultSet resultSet= new DataBaseConnector().getStatement().executeQuery(String.format("SELECT * FROM feedback WHERE description = '%s'", description));
        if(!resultSet.isBeforeFirst()){
            throw new FeedbackNotFoundException("фидбэк не найден");
        }else {
            return resultSet;
        }
    }

    public void updateFeedback(int id,String description) throws SQLException, ClassNotFoundException {
        new DataBaseConnector().getStatement().execute(String.format("UPDATE feedback SET description ='%s' WHERE id= '%s'" , description, id));
    }

    public void deleteFeedback(int id) throws SQLException, ClassNotFoundException {
        new DataBaseConnector().getStatement().execute(String.format("DELETE FROM feedback WHERE id = '%s';", id));
    }
}
