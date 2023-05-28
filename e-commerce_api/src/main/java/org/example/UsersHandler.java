package org.example;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;

import javax.xml.crypto.Data;
import java.sql.*;

public class UsersHandler {
    private Database database;

    public UsersHandler(Database database){
            this.database = database;
    }

    private JSONArray getUsers(int usersId){
        JSONArray jsonArray = new JSONArray();
        String query = "";
        switch (usersId){
            case 0 :
                query = "SELECT * FROM users";
                break;
            case -1 :
                query = "SELECT * FROM users WHERE type='Buyer'";
                break;
            case -2 :
                query = "SELECT * FROM users WHERE type='Seller'";
                break;
            default:
                query = "SELECT * FROM users WHERE id=" + usersId;
                break;
        }

        try (Connection connection = database.getConnection()){
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
        return jsonArray;
    }

    public String getMethod(String path){
        int userId;
        if(Character.isDigit(path.charAt(path.length() - 1))){
            userId = Integer.parseInt(path.substring(path.lastIndexOf("/") + 1));
        }else if(path.contains("buyer")){
            userId = -1;
        }else if(path.contains("seller")){
            userId = -2;
        }else{
            userId = 0;
        }
        JSONArray jsonArray = this.getUsers(userId);
        return jsonArray.toString();
    }
    
}
