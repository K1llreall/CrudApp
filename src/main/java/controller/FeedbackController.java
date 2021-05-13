package controller;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import service.FeedbackService;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.sql.SQLException;


public class FeedbackController {
    private final FeedbackService feedbackService;


    public FeedbackController(HttpServer server) {
        feedbackService = new FeedbackService();
        createContext(server);
    }

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    private void createContext(HttpServer server) {
        server.createContext("/api/feedback", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                try {
                    if (ApiConst.METHOD_GET.equals(exchange.getRequestMethod())) {
                        getFeedbackById(exchange);
                    } else if (ApiConst.METHOD_POST.equals(exchange.getRequestMethod())) {
                        saveFeedback(exchange, exchange.getRequestBody());
                    } else if (ApiConst.METHOD_DELETE.equals(exchange.getRequestMethod())) {
                        deleteFeedbackById(exchange);
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

    private void saveFeedback(HttpExchange exchange, InputStream requestBody) throws SQLException, ClassNotFoundException, IOException {
        feedbackService.saveFeedback(requestBody);
        exchange.sendResponseHeaders(200, 0);
    }

    private void getFeedbackById(HttpExchange exchange) throws SQLException, ClassNotFoundException, IOException {
        String uri = exchange.getRequestURI().toString();
        String feedback = uri.substring(uri.lastIndexOf("/") + 1);
        if (feedback.equals("feedback")) {
            throw new NullPointerException("фидбек отсутсвует");
        }
        String respText = feedbackService.getFeedbackById(Integer.parseInt(feedback));
        exchange.sendResponseHeaders(200, respText.getBytes().length);
        OutputStream output = exchange.getResponseBody();
        output.write(respText.getBytes());
        output.flush();
    }

    private void deleteFeedbackById(HttpExchange exchange) throws SQLException, ClassNotFoundException, IOException {
        String uri = exchange.getRequestURI().toString();
        String feedback = uri.substring(uri.lastIndexOf("/") + 1);
        if (feedback.equals("feedback")) {
            throw new NullPointerException("фидбек отсутсвует");
        }
        String respText = feedbackService.getFeedbackById(Integer.parseInt(feedback));
        feedbackService.deleteFeedback(Integer.parseInt(feedback));
        exchange.sendResponseHeaders(200, respText.getBytes().length);

    }
}


