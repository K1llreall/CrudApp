package jdbc;



import entitys.Feedback;
import exception.FeedbackNotFoundException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCFeedback {
    public void saveFeedback(Feedback feedback) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = new DataBaseConnector().getPreparedStatement(String.format("INSERT INTO feedback (description,feedback_date)VALUES (?,?);"));
        preparedStatement.setString(1,feedback.getDescription());
        preparedStatement.setDate(2,feedback.getFeedbackDate());
        preparedStatement.executeUpdate();
    }

    public ResultSet selectFeedback(int fb) throws SQLException, ClassNotFoundException {
        ResultSet resultSet= new DataBaseConnector().getStatement().executeQuery(String.format("SELECT * FROM feedback WHERE id = '%s'",fb));
        if(!resultSet.isBeforeFirst()){
            throw new FeedbackNotFoundException("фидбэк не найден");
        }else {
            return resultSet;
        }
    }

    public void updateFeedback(int id,String fb) throws SQLException, ClassNotFoundException {
        new DataBaseConnector().getStatement().execute(String.format("UPDATE feedback SET description ='%s' WHERE id= '%s'" , fb, id));
    }

    public void deleteFeedback(int id) throws SQLException, ClassNotFoundException {
        new DataBaseConnector().getStatement().execute(String.format("DELETE FROM feedback WHERE id = '%s';", id));
    }
}
