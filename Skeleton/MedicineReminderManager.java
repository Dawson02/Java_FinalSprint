// Health Data Java Final Sprint
// Author: Dawson Murray
// Date: Dec 18, 2023

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 * The MedicineReminderManager class should have methods to add reminders, get reminders
 *  1. for a specific user, and
 *  2. get reminders that are DUE for a specific user.
 *  You'll need to integrate this class with your application and database logic to
 *  1. store,
 *  2. update, and
 *  3. delete reminders as needed.
 */

public class MedicineReminderManager {
    private List<MedicineReminder> reminders;

    public MedicineReminderManager() {
        this.reminders = new ArrayList<>();
    }

    public void addReminder(MedicineReminder reminder) {
        reminders.add(reminder);
    }

    public List<MedicineReminder> getRemindersForUser(int userId) {
        List<MedicineReminder> userReminders = new ArrayList<>();
        // Write your logic here
        String query = "SELECT * FROM medicine_reminders WHERE user_id = ?";
        try {
            Connection con = DatabaseConnection.getCon();
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                MedicineReminder temp = new MedicineReminder();
                temp.setId(rs.getInt("id"));
                temp.setUserId(rs.getInt("user_id"));
                temp.setMedicineName(rs.getString("medicine_name"));
                temp.setDosage(rs.getString("dosage"));
                temp.setStartDate(rs.getString("start_date"));
                temp.setEndDate(rs.getString("end_date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userReminders;
    }

    public List<MedicineReminder> getDueReminders(int userId) {
        List<MedicineReminder> dueReminders = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        // Write your logic here
        String query = "SELECT * FROM medicine_reminders WHERE user_id = ? AND end_date < ?";
        try {
            Connection con = DatabaseConnection.getCon();
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, userId);
            statement.setString(2, now.format(formatter));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                MedicineReminder temp = new MedicineReminder();
                temp.setId(rs.getInt("id"));
                temp.setUserId(rs.getInt("user_id"));
                temp.setMedicineName(rs.getString("medicine_name"));
                temp.setDosage(rs.getString("dosage"));
                temp.setStartDate(rs.getString("start_date"));
                temp.setEndDate(rs.getString("end_date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dueReminders;
    }
}
