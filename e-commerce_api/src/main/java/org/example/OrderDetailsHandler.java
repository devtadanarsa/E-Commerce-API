package org.example;

import org.json.JSONObject;

import javax.xml.crypto.Data;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDetailsHandler {
    private Database database;

    public OrderDetailsHandler(Database database){
        this.database = database;
    }

    public String deleteMethod(String userId){
        PreparedStatement statement = null;
        int rowsAffected = 0;
        try {
            String query = "DELETE FROM order_details WHERE order=" + userId;
            statement = this.database.getConnection().prepareStatement(query);
            rowsAffected = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return rowsAffected + " rows deleted!";
    }

    public String postMethod(JSONObject requestBodyJson){
        int order = requestBodyJson.optInt("order");
        int product = requestBodyJson.optInt("product");
        int quantity = requestBodyJson.optInt("quantity");
        int price = requestBodyJson.optInt("price");
        PreparedStatement statement = null;
        int rowsAffected = 0;
        String query = "INSERT INTO order_details VALUES(?,?,?,?)";
        try {
            statement = database.getConnection().prepareStatement(query);
            statement.setInt(1, order);
            statement.setInt(2, product);
            statement.setInt(3, quantity);
            statement.setInt(4, price);
            rowsAffected = statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return rowsAffected + " rows inserted!";
    }

    public String putMethod(String userId, JSONObject requestBodyJson){
        int order = requestBodyJson.optInt("order");
        int product = requestBodyJson.optInt("product");
        int quantity = requestBodyJson.optInt("quantity");
        int price = requestBodyJson.optInt("price");
        PreparedStatement statement = null;
        int rowsAffected = 0;
        String query = "UPDATE order_details SET order = ?, product = ?, quantity = ?, price = ? WHERE order=" + userId;
        try {
            statement = database.getConnection().prepareStatement(query);
            statement.setInt(1, order);
            statement.setInt(2, product);
            statement.setInt(3, quantity);
            statement.setInt(4, price);
            rowsAffected = statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return rowsAffected + " rows updated!";
    }
}
