package at.technikum.user.model;

public class EnergyData {
    private String userId;
    private double kWh;
    private long timestamp;

    public EnergyData() {}

    public EnergyData(String userId, double kWh, long timestamp) {
        this.userId = userId;
        this.kWh = kWh;
        this.timestamp = timestamp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getKWh() {
        return kWh;
    }

    public void setKWh(double kWh) {
        this.kWh = kWh;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}

