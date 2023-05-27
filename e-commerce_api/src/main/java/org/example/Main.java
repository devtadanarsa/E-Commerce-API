package org.example;

import javax.xml.crypto.Data;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        HttpConnection.startServer();

        Database.connect();
    }
}