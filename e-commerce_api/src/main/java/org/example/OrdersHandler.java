package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;

public class OrdersHandler {
    private Database database;

    public OrdersHandler(Database database){
        this.database = database;
    }

    public String getOrders(String orderId){
        JSONArray jsonArray = new JSONArray();
        String query = "SELECT * FROM orders INNER JOIN users ON orders.buyer=users.id WHERE orders.id =" + orderId;
        String queryDetail = "SELECT * FROM order_details INNER JOIN products ON products.id=order_details.'product' WHERE order_details.'order'=" + orderId;
        String queryReview = "SELECT * FROM reviews WHERE reviews.'order'=" + orderId;
        try {
            Connection connection = database.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                JSONObject jsonOrder = new JSONObject();
                jsonOrder.put("id", resultSet.getInt("id"));
                jsonOrder.put("buyer", resultSet.getInt("buyer"));
                jsonOrder.put("note", resultSet.getInt("note"));
                jsonOrder.put("total", resultSet.getInt("total"));
                jsonOrder.put("discount", resultSet.getInt("discount"));
                jsonOrder.put("is_paid", resultSet.getString("is_paid"));
                JSONArray jsonDetail = new JSONArray();
                try{
                    Statement statementDetail = connection.createStatement();
                    ResultSet resultSetDetail = statementDetail.executeQuery(queryDetail);
                    while (resultSetDetail.next()){
                        JSONObject jsonOrderDetail = new JSONObject();
                        jsonOrderDetail.put("product", resultSetDetail.getString("title"));
                        jsonOrderDetail.put("quantity", resultSetDetail.getInt("quantity"));
                        jsonOrderDetail.put("price", resultSetDetail.getInt("price"));
                        jsonDetail.put(jsonOrderDetail);
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
                jsonOrder.put("order_detail", jsonDetail);
                jsonArray.put(jsonOrder);
                JSONArray jsonReviewArray = new JSONArray();
                try {
                    Statement statementReview = connection.createStatement();
                    ResultSet resultSetReview = statementReview.executeQuery(queryReview);
                    while (resultSetReview.next()){
                        JSONObject jsonReview = new JSONObject();
                        jsonReview.put("star", resultSetReview.getInt("star"));
                        jsonReview.put("description", resultSetReview.getString("description"));
                        jsonReviewArray.put(jsonReview);
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
                jsonOrder.put("reviews", jsonReviewArray);
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

    public String deleteMethod(int userId){
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
}

