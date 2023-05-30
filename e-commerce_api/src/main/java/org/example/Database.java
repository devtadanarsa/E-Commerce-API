package org.example;

import java.sql.*;

public class Database {
    private Connection connection;

    public Database(){
        try {
            this.connection = null;
            String rootPath = System.getProperty("user.dir");
            String url = "jdbc:sqlite:" + rootPath + "/shop_database.db";
            this.connection = DriverManager.getConnection(url);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
