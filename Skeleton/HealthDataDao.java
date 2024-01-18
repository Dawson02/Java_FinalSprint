// Health Data Java Final Sprint
// Authro: Dawson Murray
// Date: Dec 18, 2023

import java.util.List;
import java.util.ArrayList;
import java.sql.*;

public class HealthDataDao {
  public boolean createHealthData(HealthData healthData) { /* insert health data into database*/
    boolean bool = false;
    // insert health data into database
    String query = "INSERT INFO health_data ( user_id, weight, height, steps, heart_rate, date) " + "VALUES ( ?, ?, ?, ?, ?, ? )";
    // Database logic to insert data using PREPARED Statement
    try {
      Connection con = DatabaseConnection.getCon();
      PreparedStatement statement = con.prepareStatement(query);
      statement.setInt(1, healthData.getUserId());
      statement.setDouble(2, healthData.getWeight());
      statement.setDouble(3, healthData.getHeight());
      statement.setInt(4, healthData.getSteps());
      statement.setInt(5, healthData.getHeartRate());
      statement.setString(6, healthData.getDate());
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return bool;
  }


  public HealthData getHealthDataById(int id) { /* get health data by id from database */ 
    int userId = 0;
    double weight = 0;
    double height = 0;
    int steps = 0;
    int heartRate = 0;
    String date = null;

    // Prepare the SQL query
    String query = "SELECT * FROM health_data WHERE id = ?";

    // Database logic to get data by ID Using Prepared Statement
    try {
      Connection con = DatabaseConnection.getCon();
      PreparedStatement statement = con.prepareStatement(query);
      statement.setInt(1, id);
      ResultSet rs = statement.executeQuery();
      while (rs.next()) {
        id = rs.getInt("id");
        userId = rs.getInt("user_id");
        weight = rs.getDouble("weight");
        height = rs.getDouble("height");
        steps = rs.getInt("steps");
        heartRate = rs.getInt("heart_rate");
        date = rs.getString("date");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return new HealthData(id, userId, weight, height, steps, heartRate, date);
  }


  public List<HealthData> getHealthDataByUserId(int userId) { /* get health data by user id from database */ 
    ArrayList<HealthData> HealthDataList = new ArrayList<>();
    String query = "SELECT * FROM health_data WHERE user_id = ?";
    try {
      Connection con = DatabaseConnection.getCon();
      PreparedStatement statement = con.prepareStatement(query);
      statement.setInt(1, userId);
      ResultSet rs = statement.executeQuery();
      while (rs.next()) {
        HealthData temp = new HealthData();
        temp.setId(rs.getInt("id"));
        temp.setUserId(rs.getInt("user_id"));
        temp.setWeight(rs.getDouble("weight"));
        temp.setHeight(rs.getDouble("height"));
        temp.setSteps(rs.getInt("steps"));
        temp.setHeartRate(rs.getInt("heart_rate"));
        temp.setDate(rs.getString("date"));
        HealthDataList.add(temp);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return HealthDataList;
  }


  public boolean updateHealthData(HealthData healthData) { /* update health data in the database */ 
    boolean bool = false;

    // Prepare the SQL query
    String query = "UPDATE health_data" + "SET weight = ?, height = ?, steps = ?, heart_rate = ?, date = ? " + "WHERE id = ?";

    // Database logic to get update health data Using Prepared Statement
    try {
      Connection con = DatabaseConnection.getCon();
      PreparedStatement statement = con.prepareStatement(query);
      statement.setDouble(1, healthData.getWeight());
      statement.setDouble(2, healthData.getHeight());
      statement.setInt(3, healthData.getSteps());
      statement.setInt(4, healthData.getHeartRate());
      statement.setString(5, healthData.getDate());
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return bool;
  }


  public boolean deleteHealthData(int id) { /* delete health data from the database */ 
    boolean bool = false;

    // Prepare the SQL query
    String query = "DELETE FROM health_data WHERE id = ?";

    // Database logic to delete health data
    try {
      Connection con = DatabaseConnection.getCon();
      PreparedStatement statement = con.prepareStatement(query);
      statement.setInt(1, id);
      int rowsUpdated = statement.executeUpdate();
      if (rowsUpdated != 0) {
        bool = true;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return bool;
  }
}
