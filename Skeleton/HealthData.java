// Health Data Java project Final Sprint
// Author: Dawson Murray
// Date: Dec 16, 2023

//Imports
// import java.time.LocalDateTime;

public class HealthData {
    private int id;
    private int userId;
    private double weight;
    private double height;
    private int steps;
    private int heartRate;
    private String date;

    // Constructor
public HealthData (int id, int userId, double weight, double height, int steps, int heartRate, String date) {
    this.id = id;
    this.userId = userId;
    this.weight = weight;
    this.height = height;
    this.steps = steps;
    this.heartRate = heartRate;
    this.date = date;
}
    public HealthData() {
    }

	// Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public int getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
