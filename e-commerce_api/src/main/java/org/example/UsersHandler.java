package org.example;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.HashMap;

public class UsersHandler {
    private Database database;

    public UsersHandler(Database database){
            this.database = database;
    }

    public String getUsers(int usersId){
        JSONArray jsonArray = new JSONArray();
        String query = "";
        if (usersId == 0) {
            query = "SELECT * FROM users";
        }else if(usersId == -1){
            query = "SELECT * FROM users WHERE type='Buyer'";
        }else if(usersId == -2){
            query = "SELECT * FROM users WHERE type='Seller'";
        }else{
            query = "SELECT * FROM users WHERE id=" + usersId;
        }

        try {
            Connection connection = database.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                JSONObject jsonUSer = new JSONObject();
                jsonUSer.put("id", resultSet.getInt("id"));
                jsonUSer.put("firstName", resultSet.getString("first_name"));
                jsonUSer.put("lastName", resultSet.getString("last_name"));
                jsonUSer.put("email", resultSet.getString("email"));
                jsonUSer.put("phoneNumber", resultSet.getString("phone_numer"));
                jsonUSer.put("type", resultSet.getString("type"));
                jsonArray.put(jsonUSer);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return jsonArray.toString();
    }

    public String getUsersMethod(String[] path){
        String response = "";
        if(path.length == 2){
            response = getUsers(0);
        }else if(path.length == 3){
            response = getUsers(Integer.parseInt(path[2]));
        }else if(path.length == 4){
            if(path[3].equals("products")){
                response = getUserProducts(Integer.parseInt(path[2]));
            }else if(path[3].equals("orders")){
                response = getUsersOrders(path[2]);
            }
        }
        return response;
    }

    public String getUsersOrders(String usersId){
        JSONArray jsonArray = new JSONArray();
        String query = "SELECT * FROM orders WHERE buyer=" + usersId;
        try{
            Connection connection = database.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                JSONObject jsonUSer = new JSONObject();
                jsonUSer.put("id", resultSet.getInt("id"));
                jsonUSer.put("buyer", resultSet.getInt("buyer"));
                jsonUSer.put("note", resultSet.getInt("note"));
                jsonUSer.put("total", resultSet.getInt("total"));
                jsonUSer.put("discount", resultSet.getInt("discount"));
                jsonUSer.put("is_paid", resultSet.getString("is_paid"));
                jsonArray.put(jsonUSer);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return jsonArray.toString();
    }

    public String getUserProducts(int userId){
        JSONArray jsonArray = new JSONArray();
        String query = "SELECT * FROM products WHERE seller=" + userId;
        try (Connection connection = database.getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                JSONObject jsonUSer = new JSONObject();
                jsonUSer.put("id", resultSet.getInt("id"));
                jsonUSer.put("seller", resultSet.getInt("seller"));
                jsonUSer.put("title", resultSet.getString("title"));
                jsonUSer.put("description", resultSet.getString("description"));
                jsonUSer.put("price", resultSet.getString("price"));
                jsonUSer.put("stock", resultSet.getInt("stock"));
                jsonArray.put(jsonUSer);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return jsonArray.toString();
    }

    public String deleteMethod(int userId){
        PreparedStatement statement = null;
        int rowsAffected = 0;
        try {
            String query = "DELETE FROM users WHERE id=" + userId;
            statement = this.database.getConnection().prepareStatement(query);
            rowsAffected = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return rowsAffected + " rows deleted!";
    }

    public String postMethod(JSONObject requestBodyJson){
        String firstName = requestBodyJson.optString("first_name");
        String lastName = requestBodyJson.optString("last_name");
        String email = requestBodyJson.optString("email");
        String phoneNumber = requestBodyJson.optString("phone_number");
        String type = requestBodyJson.optString("type");
        PreparedStatement statement = null;
        int rowsAffected = 0;

        String query = "INSERT INTO users(first_name, last_name, email, phone_numer, type) VALUES(?,?,?,?,?)";
        try {
            statement = database.getConnection().prepareStatement(query);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, email);
            statement.setString(4, phoneNumber);
            statement.setString(5, type);
            rowsAffected = statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return rowsAffected + " rows inserted!";
    }

    public String putMethod(String userId, JSONObject requestBodyJson){
        String firstName = requestBodyJson.optString("first_name");
        String lastName = requestBodyJson.optString("last_name");
        String email = requestBodyJson.optString("email");
        String phoneNumber = requestBodyJson.optString("phone_number");
        String type = requestBodyJson.optString("type");
        PreparedStatement statement = null;
        int rowsAffected = 0;

        String query = "UPDATE users SET first_name = ?, last_name = ?, email = ?, phone_numer = ?, type = ? WHERE id=" + userId;
        try {
            statement = database.getConnection().prepareStatement(query);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, email);
            statement.setString(4, phoneNumber);
            statement.setString(5, type);
            rowsAffected = statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return rowsAffected + " rows updated!";
    }
}
