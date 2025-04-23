package at.technikum.producer.model;

public class EnergyData {
    private String producerId;
    private double kWh;
    private long timestamp;

    // Konstruktor
    public EnergyData(String producerId, double kWh, long timestamp) {
        this.producerId = producerId;
        this.kWh = kWh;
        this.timestamp = timestamp;
    }

    // Getter & Setter
    public String getProducerId() { return producerId; }
    public void setProducerId(String producerId) { this.producerId = producerId; }

    public double getKWh() { return kWh; }
    public void setKWh(double kWh) { this.kWh = kWh; }

    public long getTimestamp() { return timestamp; }
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }
}
