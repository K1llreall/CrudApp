package service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entitys.Team;
import jdbc.JDBCTeam;

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

public class TeamService {
    private final JDBCTeam jdbcTeam;
    private final ObjectMapper objectMapper;

    public TeamService() {
        this.jdbcTeam = new JDBCTeam();
        this.objectMapper = new ObjectMapper();

        objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
    }

    public String getTeamByName(String name) throws SQLException, ClassNotFoundException, JsonProcessingException {
        ResultSet rs = jdbcTeam.selectTeam(name);
        List<Team> teamList = new ArrayList<>();
        while(rs.next()) {
            Team team = new Team();
            team.setId(rs.getInt("id"));
            team.setName(rs.getString("name"));
            teamList.add(team);
        }

        return objectMapper.writeValueAsString(teamList);
    }

    public void saveTeam(InputStream requestBody) throws SQLException, ClassNotFoundException, IOException {
        String text = new BufferedReader(
                new InputStreamReader(requestBody, StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));
        Team team = objectMapper.readValue(text, Team.class);
        jdbcTeam.saveTeam(team);
    }
    public void deleteTeam(String teamName) throws SQLException, ClassNotFoundException {
        jdbcTeam.deleteTeam(teamName);
    }

}
