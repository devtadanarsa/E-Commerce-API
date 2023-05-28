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
//
//    public static void connect(){
//        Connection connection = null;
//        String rootPath = System.getProperty("user.dir");
//        try {
//            String url = "jdbc:sqlite:" + rootPath + "/shop_database.db";
//            connection = DriverManager.getConnection(url);
//            System.out.println("Connection to SQLite has been established");
//        } catch (SQLException e){
//            System.out.println(e.getMessage());
//        } finally {
//            try {
//                if(connection != null){
//                    connection.close();
//                }
//            }catch (SQLException exception){
//                System.out.println(exception.getMessage());
//            }
//        }
//    }
//
//    private Connection connection(){
//        String rootPath = System.getProperty("user.dir");
//        String url = "jdbc:sqlite:" + rootPath + "/shop_database.db";
//        Connection conn = null;
//        try {
//            conn = DriverManager.getConnection(url);
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//        return conn;
//    }
//
//    public void selectAll() throws SQLException {
//        String sql = "SELECT * FROM users";
//
//        try (Connection conn = connection()){
//            Statement statement = conn.createStatement();
//            ResultSet resultSet = statement.executeQuery(sql);
//
//            while (resultSet.next()){
//                System.out.println(resultSet.getInt("id") + "\t" +resultSet.getString("last_name"));
//            }
//        }catch (SQLException e){
//            System.out.println(e.getMessage());
//        }
//    }
//
}
