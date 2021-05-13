import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import controller.EmployeeController;
import controller.FeedbackController;
import controller.ProjectController;
import controller.TeamController;


import java.io.IOException;

import java.net.InetSocketAddress;


public class Main {


    public static void main(String[] args) throws IOException {
        int serverPort = 8080;
        HttpServer server = HttpServer.create(new InetSocketAddress(serverPort), 0);
        TeamController teamController = new TeamController(server);
        ProjectController projectController= new ProjectController(server);
        FeedbackController feedbackController= new FeedbackController(server);
        EmployeeController employeeController= new EmployeeController(server);

        server.start();
    }
}
