package org.example;

import org.json.JSONObject;

import javax.xml.crypto.Data;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddressesHandler {
    private Database database;

    public AddressesHandler(Database database){
        this.database = database;
    }

    public String deleteMethod(String userId){
        PreparedStatement statement = null;
        int rowsAffected = 0;
        try {
            String query = "DELETE FROM addresses WHERE id=" + userId;
            statement = this.database.getConnection().prepareStatement(query);
            rowsAffected = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return rowsAffected + " rows deleted!";
    }

    public String postMethod(JSONObject requestBodyJson){
        int users = requestBodyJson.optInt("users");
        String type = requestBodyJson.optString("type");
        String line1 = requestBodyJson.optString("line1");
        String line2 = requestBodyJson.optString("line2");
        String city = requestBodyJson.optString("city");
        String province = requestBodyJson.optString("province");
        String postcode = requestBodyJson.optString("postcode");
        PreparedStatement statement = null;
        int rowsAffected = 0;
        String query = "INSERT INTO addresses VALUES(?,?,?,?,?,?,?)";
        try {
            statement = database.getConnection().prepareStatement(query);
            statement.setInt(1, users);
            statement.setString(2, type);
            statement.setString(3, line1);
            statement.setString(4, line2);
            statement.setString(5, city);
            statement.setString(6, province);
            statement.setString(7, postcode);
            rowsAffected = statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return rowsAffected + " rows inserted!";
    }

    public String putMethod(String userId, JSONObject requestBodyJson){
        int users = requestBodyJson.optInt("users");
        String type = requestBodyJson.optString("type");
        String line1 = requestBodyJson.optString("line1");
        String line2 = requestBodyJson.optString("line2");
        String city = requestBodyJson.optString("city");
        String province = requestBodyJson.optString("province");
        String postcode = requestBodyJson.optString("postcode");
        PreparedStatement statement = null;
        int rowsAffected = 0;
        String query = "UPDATE addresses SET users = ?, type = ?, line1 = ?, line2 = ?, city = ?, province = ?, postcode = ? WHERE users=" + userId;
        try {
            statement = database.getConnection().prepareStatement(query);
            statement = database.getConnection().prepareStatement(query);
            statement.setInt(1, users);
            statement.setString(2, type);
            statement.setString(3, line1);
            statement.setString(4, line2);
            statement.setString(5, city);
            statement.setString(6, province);
            statement.setString(7, postcode);
            rowsAffected = statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return rowsAffected + " rows updated!";
    }
}
