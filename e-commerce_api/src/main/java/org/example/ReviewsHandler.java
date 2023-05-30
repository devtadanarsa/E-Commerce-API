package org.example;

import org.json.JSONObject;

import javax.xml.crypto.Data;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReviewsHandler {
    private Database database;

    public ReviewsHandler(Database database){
        this.database = database;
    }

    public String deleteMethod(String userId){
        PreparedStatement statement = null;
        int rowsAffected = 0;
        try {
            String query = "DELETE FROM reviews WHERE order=" + userId;
            statement = this.database.getConnection().prepareStatement(query);
            rowsAffected = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return rowsAffected + " rows deleted!";
    }

    public String postMethod(JSONObject requestBodyJson){
        int order = requestBodyJson.optInt("order");
        int star = requestBodyJson.optInt("star");
        int description = requestBodyJson.optInt("description");
        PreparedStatement statement = null;
        int rowsAffected = 0;
        String query = "INSERT INTO reviews VALUES(?,?,?)";
        try {
            statement = database.getConnection().prepareStatement(query);
            statement.setInt(1, order);
            statement.setInt(2, star);
            statement.setInt(3, description);
            rowsAffected = statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return rowsAffected + " rows inserted!";
    }

    public String putMethod(String userId, JSONObject requestBodyJson){
        int order = requestBodyJson.optInt("order");
        int star = requestBodyJson.optInt("star");
        int description = requestBodyJson.optInt("description");
        PreparedStatement statement = null;
        int rowsAffected = 0;
        String query = "UPDATE reviews SET order=?, star=?, description=? WHERE order=" + userId;
        try {
            statement = database.getConnection().prepareStatement(query);
            statement.setInt(1, order);
            statement.setInt(2, star);
            statement.setInt(3, description);
            rowsAffected = statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return rowsAffected + " rows updated!";
    }
}
