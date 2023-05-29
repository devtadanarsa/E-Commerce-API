package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;

public class OrdersHandler {
    private Database database;

    public OrdersHandler(Database database){
        this.database = database;
    }

    public String getProduct(String usersId){
        JSONArray jsonArray = new JSONArray();
        String query = "SELECT * FROM orders WHERE buyer=" + usersId;
        try (Connection connection = database.getConnection()){
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

    public String deleteMethod(String userId){
        PreparedStatement statement = null;
        int rowsAffected = 0;
        try {
            String query = "DELETE FROM orders WHERE id=" + userId;
            statement = this.database.getConnection().prepareStatement(query);
            rowsAffected = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return rowsAffected + " rows deleted!";
    }

    public String postMethod(JSONObject requestBodyJson){
        int buyer = requestBodyJson.optInt("buyer");
        int note = requestBodyJson.optInt("note");
        int total = requestBodyJson.optInt("total");
        int discount = requestBodyJson.optInt("discount");
        String is_paid = requestBodyJson.optString("is_paid");
        PreparedStatement statement = null;
        int rowsAffected = 0;
        String query = "INSERT INTO orders(buyer, note, total, discount, is_paid) VALUES(?,?,?,?,?)";
        try {
            statement = database.getConnection().prepareStatement(query);
            statement.setInt(1, buyer);
            statement.setInt(2, note);
            statement.setInt(3, total);
            statement.setInt(4, discount);
            statement.setString(5, is_paid);
            rowsAffected = statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return rowsAffected + " rows inserted!";
    }

    public String putMethod(String userId, JSONObject requestBodyJson){
        int buyer = requestBodyJson.optInt("buyer");
        int note = requestBodyJson.optInt("note");
        int total = requestBodyJson.optInt("total");
        int discount = requestBodyJson.optInt("discount");
        String is_paid = requestBodyJson.optString("is_paid");
        PreparedStatement statement = null;
        int rowsAffected = 0;
        String query = "UPDATE orders SET buyer = ?, note = ?, total = ?, discount = ?, is_paid = ? WHERE id=" + userId;
        try {
            statement = database.getConnection().prepareStatement(query);
            statement.setInt(1, buyer);
            statement.setInt(2, note);
            statement.setInt(3, total);
            statement.setInt(4, discount);
            statement.setString(5, is_paid);
            rowsAffected = statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return rowsAffected + " rows updated!";
    }
}
