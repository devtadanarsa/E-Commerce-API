package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    public static void connect(){
        Connection connection = null;
        String rootPath = System.getProperty("user.dir");
        try {
            String url = "jdbc:sqlite:" + rootPath + "/shop_database.db";
            connection = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established");
        } catch (SQLException e){
            System.out.println(e.getMessage());
        } finally {
            try {
                if(connection != null){
                    connection.close();
                }
            }catch (SQLException exception){
                System.out.println(exception.getMessage());
            }
        }
    }
}
