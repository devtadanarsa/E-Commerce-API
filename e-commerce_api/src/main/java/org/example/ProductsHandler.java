package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.xml.crypto.Data;
import java.sql.*;

public class ProductsHandler {
    private Database database;

    public ProductsHandler(Database database){
        this.database = database;
    }

    public String getProductsMethod(String[] path, String query){
        String response = "";
        if(path.length == 2){
            response = getProducts(0, query);
        }else if(path.length == 3){
            response = getProducts(Integer.parseInt(path[2]), query);
        }
        return response;
    }

    public String getProducts(int productId, String addedQuery){
        JSONArray jsonArray = new JSONArray();
        String querySQL = "";

        if(addedQuery != null){
            String[] query = addedQuery.split("&");
            String queryField = "";
            String queryCondition = "";
            int queryValue = 0;
            for(int i = 0; i < query.length; i++){
                if(query[i].contains("field")){
                    queryField = query[i].substring(query[i].lastIndexOf("=") + 1);
                }else if(query[i].contains("val")){
                    queryValue = Integer.parseInt(query[i].substring(query[i].lastIndexOf("=") + 1));
                }else if(query[i].contains("cond")){
                    String cond = query[i].substring(query[i].lastIndexOf("=") + 1);
                    if(cond.equals("larger")){
                        queryCondition = ">";
                    }else if(cond.equals("largerEqual")){
                        queryCondition = ">=";
                    }else if(cond.equals("smaller")){
                        queryCondition = "<";
                    }else if(cond.equals("smallerEqual")){
                        queryCondition = "<=";
                    }
                }
            }
            querySQL = "SELECT * FROM products WHERE " + queryField + queryCondition + " " + queryValue + " ";
        }else{
            switch (productId){
                case 0 :
                    querySQL = "SELECT * FROM products";
                    break;
                default:
                    querySQL = "SELECT * FROM products WHERE id=" + productId;
                    break;
            }
        }

        try {
            Connection connection = database.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(querySQL);
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

    public String deleteMethod(String userId){
        PreparedStatement statement = null;
        int rowsAffected = 0;
        try {
            String query = "DELETE FROM products WHERE id=" + userId;
            statement = this.database.getConnection().prepareStatement(query);
            rowsAffected = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return rowsAffected + " rows deleted!";
    }

    public String postMethod(JSONObject requestBodyJson){
        int seller = requestBodyJson.optInt("seller");
        String title = requestBodyJson.optString("title");
        String description = requestBodyJson.optString("description");
        String price = requestBodyJson.optString("price");
        int stock = requestBodyJson.optInt("stock");
        PreparedStatement statement = null;
        int rowsAffected = 0;
        String query = "INSERT INTO products(seller, title, description, price, stock) VALUES(?,?,?,?,?)";
        try {
            statement = database.getConnection().prepareStatement(query);
            statement.setInt(1, seller);
            statement.setString(2, title);
            statement.setString(3, description);
            statement.setString(4, price);
            statement.setInt(5, stock);
            rowsAffected = statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return rowsAffected + " rows inserted!";
    }

    public String putMethod(String userId, JSONObject requestBodyJson){
        int seller = requestBodyJson.optInt("seller");
        String title = requestBodyJson.optString("title");
        String description = requestBodyJson.optString("description");
        String price = requestBodyJson.optString("price");
        int stock = requestBodyJson.optInt("stock");
        PreparedStatement statement = null;
        int rowsAffected = 0;

        String query = "UPDATE products SET seller = ?, title = ?, description = ?, price = ?, stock = ? WHERE id=" + userId;
        try {
            statement = database.getConnection().prepareStatement(query);
            statement.setInt(1, seller);
            statement.setString(2, title);
            statement.setString(3, description);
            statement.setString(4, price);
            statement.setInt(5, stock);
            rowsAffected = statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return rowsAffected + " rows updated!";
    }


//    public String getMethod(String path){
//        int userId;
//        if(Character.isDigit(path.charAt(path.length() - 1))){
//            userId = Integer.parseInt(path.substring(path.lastIndexOf("/") + 1));
//        }else{
//            userId = 0;
//        }
//        JSONArray jsonArray = this.getProducts(userId);
//        return jsonArray.toString();
//    }
}
