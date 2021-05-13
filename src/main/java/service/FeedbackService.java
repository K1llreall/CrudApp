package service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entitys.Feedback;
import jdbc.JDBCFeedback;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FeedbackService {
    private final JDBCFeedback jdbcFeedback;
    private final ObjectMapper objectMapper;


    public FeedbackService() {
        this.jdbcFeedback = new JDBCFeedback();
        this.objectMapper = new ObjectMapper();

        objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
    }

    public String getFeedbackById(int fb) throws SQLException, ClassNotFoundException, JsonProcessingException {
        ResultSet rs = jdbcFeedback.selectFeedback(fb);
        List<Feedback> FeedbackList = new ArrayList<>();
        while (rs.next()) {
            Feedback feedback = new Feedback();
            feedback.setId(rs.getInt("id"));
            feedback.setDescription(rs.getString("description"));
            feedback.setFeedbackDate(rs.getDate("feedback_date"));
            FeedbackList.add(feedback)  ;
        }

        return objectMapper.writeValueAsString(FeedbackList);
    }

    public void saveFeedback(InputStream requestBody) throws SQLException, ClassNotFoundException, IOException {
        String text = new BufferedReader(
                new InputStreamReader(requestBody, StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));
        Feedback feedback = objectMapper.readValue(text, Feedback.class);
        jdbcFeedback.saveFeedback(feedback);
    }
    public void deleteFeedback(int id) throws SQLException, ClassNotFoundException {
        jdbcFeedback.deleteFeedback(id);
    }
}
