package controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import service.ProjectService;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(HttpServer server) {
        projectService = new ProjectService();
        createContext(server);
    }

    private void createContext(HttpServer server) {
        server.createContext("/api/project", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                try {
                    if (ApiConst.METHOD_GET.equals(exchange.getRequestMethod())) {
                        getProjectByName(exchange);
                    } else if (ApiConst.METHOD_POST.equals(exchange.getRequestMethod())) {
                        saveProject(exchange, exchange.getRequestBody());
                    } else if (ApiConst.METHOD_DELETE.equals(exchange.getRequestMethod())) {
                        deleteProjectByName(exchange);
                    } else {
                        exchange.sendResponseHeaders(405, -1);// 405 Method Not Allowed
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                exchange.close();
            }
        });
    }

    private void saveProject(HttpExchange exchange, InputStream requestBody) throws SQLException, ClassNotFoundException, IOException {
        projectService.saveProject(requestBody);
        exchange.sendResponseHeaders(200, 0);
    }

    private void getProjectByName(HttpExchange exchange) throws SQLException, ClassNotFoundException, IOException {
        String uri = exchange.getRequestURI().toString();
        String projectName = uri.substring(uri.lastIndexOf("/") + 1);
        if (projectName.equals("project")) {
            throw new NullPointerException("Имя не указано");
        }

        String respText = projectService.getProjectByName(projectName);
        exchange.sendResponseHeaders(200, respText.getBytes().length);
        OutputStream output = exchange.getResponseBody();
        output.write(respText.getBytes());
        output.flush();
    }

    private void deleteProjectByName(HttpExchange exchange) throws SQLException, ClassNotFoundException, IOException {
        String uri = exchange.getRequestURI().toString();
        String projectName = uri.substring(uri.lastIndexOf("/") + 1);
        if (projectName.equals("project")) {
            throw new NullPointerException("Имя не указано");
        }
        String respText = projectService.getProjectByName(projectName);
        projectService.deleteProject(projectName);
        exchange.sendResponseHeaders(200, respText.getBytes().length);

    }
}
