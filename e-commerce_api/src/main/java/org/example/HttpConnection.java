package org.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;


public class HttpConnection {
    private UsersHandler usersHandler;

    public HttpConnection(){
        Database database = new Database();
        usersHandler = new UsersHandler(database);
    }

    public void startServer() throws IOException {
        HttpServer httpServer = HttpServer.create(new InetSocketAddress("localhost", 8060), 0);
        httpServer.createContext("/", new Handler());
        httpServer.setExecutor(Executors.newSingleThreadExecutor());
        httpServer.start();
    }

    private class Handler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String method = exchange.getRequestMethod();
            String path = exchange.getRequestURI().getPath();
            String response = "";

            if(path.contains("/users") && method.equals("GET")){
                response = usersHandler.getMethod(path);
            }else if(path.contains("/users") && method.equals("DELETE")){
                response = usersHandler.deleteMethod(path);
            }else if(path.contains("/users") && method.equals("POST")){
                JSONObject requestBodyJson = parseRequestBody(exchange.getRequestBody());
                response = usersHandler.postMethod(requestBodyJson);
            }else if(path.contains("/users") && method.equals("PUT")){
                JSONObject requestBodyJson = parseRequestBody(exchange.getRequestBody());
                response = usersHandler.putMethod(path, requestBodyJson);
            }

            OutputStream outputStream = exchange.getResponseBody();
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, response.length());
            outputStream.write(response.getBytes());
            outputStream.flush();
            outputStream.close();
        }

        private JSONObject parseRequestBody(InputStream requestBody) throws IOException {
            byte[] requestBodyBytes = requestBody.readAllBytes();
            String requestBodyString = new String(requestBodyBytes);
            return new JSONObject(requestBodyString);
        }
    }
}
