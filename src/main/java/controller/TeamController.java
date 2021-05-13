package controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import entitys.Team;
import jdbc.JDBCTeam;
import lombok.SneakyThrows;
import service.TeamService;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.stream.Collectors;

public class TeamController {
    private final TeamService teamService;

    public TeamController(HttpServer server) {
        teamService = new TeamService();
        createContext(server);
    }

    private void createContext(HttpServer server) {
        server.createContext("/api/team", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                try {
                    if (ApiConst.METHOD_GET.equals(exchange.getRequestMethod())) {
                        getTeamByName(exchange);
                    } else if (ApiConst.METHOD_POST.equals(exchange.getRequestMethod())) {
                        saveTeam(exchange, exchange.getRequestBody());
                    } else if (ApiConst.METHOD_DELETE.equals(exchange.getRequestMethod())){
                        deleteTeamByName(exchange);
                    }else{
                        exchange.sendResponseHeaders(405, -1);// 405 Method Not Allowed
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                exchange.close();
            }
        });
    }

    private void saveTeam(HttpExchange exchange, InputStream requestBody) throws SQLException, ClassNotFoundException, IOException {
        teamService.saveTeam(requestBody);
        exchange.sendResponseHeaders(200, 0);
    }

    private void getTeamByName(HttpExchange exchange) throws SQLException, ClassNotFoundException, IOException {
        String respText = teamService.getTeamByName("Ruuby");
        exchange.sendResponseHeaders(200, respText.getBytes().length);
        OutputStream output = exchange.getResponseBody();
        output.write(respText.getBytes());
        output.flush();
    }
    private void deleteTeamByName(HttpExchange exchange)throws SQLException,ClassNotFoundException,IOException{
        String uri = exchange.getRequestURI().toString();
        String teamName = uri.substring(uri.lastIndexOf("/") +1);
        if(teamName.equals("team")) {
            throw new NullPointerException("Имя не указано");
        }
        String respText= teamService.getTeamByName(teamName);
        teamService.deleteTeam(teamName);
        exchange.sendResponseHeaders(200, respText.getBytes().length);

    }
}
