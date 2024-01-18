// Health Data Java Final Sprint
// Author: Dawson Murray
// Date: Dec 17, 2023

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class DoctorPortalDao {
    private UserDao userDao;
    private HealthDataDao healthDataDao;

   // Complete all these methods and add more as needed

   
    public DoctorPortalDao() {
        userDao = new UserDao();
        healthDataDao = new HealthDataDao();
    }

    public Doctor getDoctorById(int doctorId) {
        // Implement this method
        int user_id = 0;
        String firstName = null;
        String lastName = null;
        String email = null;
        String password = null;
        boolean is_doctor = false;
        int doctor_id = 0;
        String specialization = null;
        String medicalLicenseNumber = null;

        // Preparethe SQL query
        String query = "SELECT * FROM users WHERE id = ?";

        // Database logic to get data by ID Using Prepared Statement
        try {
            Connection con = DatabaseConnection.getCon();
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, doctorId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                user_id = rs.getInt("id");
                firstName = rs.getString("first_name");
                lastName = rs.getString("last_name");
                email = rs.getString("email");
                password = rs.getString("password");
                is_doctor = rs.getBoolean("is_doctor");
                doctor_id = rs.getInt("doctor_id");
                specialization = rs.getString("specialization");
                medicalLicenseNumber = rs.getString("medical_license_number");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Doctor(user_id, firstName, lastName, email, password, is_doctor, doctor_id, specialization, medicalLicenseNumber);
    }

    public List<User> getPatientsByDoctorId(int doctorId) {
        // Implement this method
        ArrayList<User> UserList = new ArrayList<>();
            String query = "SELECT * FROM users WHERE doctor_id = ?";
            try {
                Connection con = DatabaseConnection.getCon();
                PreparedStatement statement = con.prepareStatement(query);
                statement.setInt(1, doctorId);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) { 
                    User temp = new User();
                    temp.setId(rs.getInt("id"));
                    temp.setFirstName(rs.getString("first_name"));
                    temp.setLastName(rs.getString("last_name"));
                    temp.setEmail(rs.getString("email"));
                    temp.setPassword(rs.getString("password"));
                    temp.setDoctor(rs.getBoolean("is_doctor"));
                    temp.setDoctorId(rs.getInt("doctor_id"));
                    UserList.add(temp);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return UserList;
        }

    public List<HealthData> getHealthDataByPatientId(int patientId) {
        // Implement this method
        ArrayList<HealthData> HealthDataList = new ArrayList<>();
        String query = "SELECT * FROM health_data WHERE patient_id = ?";
        try {
            Connection con = DatabaseConnection.getCon();
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, patientId);
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return HealthDataList;
    }
    // Add more methods for other doctor-specific tasks
    
}

