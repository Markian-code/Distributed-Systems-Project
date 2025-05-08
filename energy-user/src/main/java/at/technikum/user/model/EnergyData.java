package at.technikum.user.model;

public class EnergyData {
    private String userId;
    private String producerId;
    private double kWh;
    private long timestamp;

    public EnergyData() {}

    public EnergyData(String userId, String producerId, double kWh, long timestamp) {
        this.userId = userId;
        this.producerId = producerId;
        this.kWh = kWh;
        this.timestamp = timestamp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProducerId() {
        return producerId;
    }

    public void setProducerId(String producerId) {
        this.producerId = producerId;
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
