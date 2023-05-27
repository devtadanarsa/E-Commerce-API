package org.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class HttpConnection {
    public static void startServer() throws IOException {
        HttpServer httpServer = HttpServer.create(new InetSocketAddress("localhost", 8060), 0);
        httpServer.createContext("/api/greeting", new MovieHandler());
        httpServer.setExecutor(Executors.newSingleThreadExecutor());
        httpServer.start();

    }

    private static class MovieHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if("GET".equals(exchange.getRequestMethod())){
                OutputStream outputStream = exchange.getResponseBody();
                String responseToSentBack = "Halo Brow";
                exchange.sendResponseHeaders(200, responseToSentBack.length());
                outputStream.write(responseToSentBack.getBytes());
                outputStream.flush();
                outputStream.close();
            }
        }
    }
}
