package controller;

import com.sun.net.httpserver.HttpServer;
import service.EmployeeService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(HttpServer server) {
        employeeService = new EmployeeService();
        createContext(server);
    }

    private void createContext(HttpServer server) {
        server.createContext("/api/employee", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                try {
                    if (ApiConst.METHOD_GET.equals(exchange.getRequestMethod())) {
                        getEmployeeById(exchange);
                    } else if (ApiConst.METHOD_POST.equals(exchange.getRequestMethod())) {
                        saveEmployee(exchange, exchange.getRequestBody());
                    } else if (ApiConst.METHOD_DELETE.equals(exchange.getRequestMethod())) {
                        deleteEmployeeById(exchange);
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

    private void saveEmployee(HttpExchange exchange, InputStream requestBody) throws SQLException, IOException, ClassNotFoundException {
        employeeService.saveEmployee(requestBody);
        exchange.sendResponseHeaders(200, 0);
    }

    private void getEmployeeById(HttpExchange exchange) throws IOException, SQLException, ClassNotFoundException {
        String uri = exchange.getRequestURI().toString();
        String employeeId = uri.substring(uri.lastIndexOf("/") + 1);
        if (employeeId == "employee") {
            throw new NullPointerException("сорудник не найден");
        }
        String respText = employeeService.getEmployeeById(Integer.parseInt(employeeId));
        exchange.sendResponseHeaders(200, respText.getBytes().length);
        OutputStream output = exchange.getResponseBody();
        output.write(respText.getBytes());
        output.flush();
    }

    private void deleteEmployeeById(HttpExchange exchange) throws SQLException, ClassNotFoundException, IOException {
        String uri = exchange.getRequestURI().toString();
        String employeeId = uri.substring(uri.lastIndexOf("/") + 1);
        if (employeeId=="employee") {
            throw new NullPointerException("сотрудник не найден");
        }
        String respText = employeeService.getEmployeeById(Integer.parseInt(employeeId));
        employeeService.deleteEmployee(Integer.parseInt(employeeId));
        exchange.sendResponseHeaders(200, respText.getBytes().length);

    }
}
