package service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entitys.Project;

import jdbc.JDBCProject;


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

public class ProjectService {
    private final JDBCProject jdbcProject;
    private final ObjectMapper objectMapper;

    public ProjectService() {
        this.jdbcProject = new JDBCProject();
        this.objectMapper = new ObjectMapper();

        objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
    }

    public String getProjectByName(String projectName) throws SQLException, ClassNotFoundException, JsonProcessingException {
        ResultSet rs = jdbcProject.selectProject(projectName);
        List<Project> projectList = new ArrayList<>();
        while(rs.next()) {
            Project project = new Project();
            project.setId(rs.getInt("id"));
            project.setProjectName(rs.getString("project_name"));
            project.setCustomer(rs.getString("customer"));
            project.setDuration(rs.getString("duration"));
            project.setMethodology(rs.getString("methodology"));
            project.setProjectManager(rs.getString("project_manager"));
            project.setTeamId(rs.getInt("team_id"));
            projectList.add(project);
        }

        return objectMapper.writeValueAsString(projectList);
    }

    public void saveProject(InputStream requestBody) throws SQLException, ClassNotFoundException, IOException {
        String text = new BufferedReader(
                new InputStreamReader(requestBody, StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));
        Project project = objectMapper.readValue(text, Project.class);
        jdbcProject.save(project);
    }

    public void deleteProject(String projectName) throws SQLException, ClassNotFoundException {
        jdbcProject.deleteProject(projectName);
    }
}
