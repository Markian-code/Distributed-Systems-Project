package at.technikum.user.model;

public class EnergyData {
    private String userId;
<<<<<<< HEAD
    private String producerId;
=======
>>>>>>> fe53726eedcbcfcb9c758e15599fc6cdb0edd032
    private double kWh;
    private long timestamp;

    public EnergyData() {}

<<<<<<< HEAD
    public EnergyData(String userId, String producerId, double kWh, long timestamp) {
        this.userId = userId;
        this.producerId = producerId;
=======
    public EnergyData(String userId, double kWh, long timestamp) {
        this.userId = userId;
>>>>>>> fe53726eedcbcfcb9c758e15599fc6cdb0edd032
        this.kWh = kWh;
        this.timestamp = timestamp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

<<<<<<< HEAD
    public String getProducerId() {
        return producerId;
    }

    public void setProducerId(String producerId) {
        this.producerId = producerId;
    }

=======
>>>>>>> fe53726eedcbcfcb9c758e15599fc6cdb0edd032
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
<<<<<<< HEAD
=======

>>>>>>> fe53726eedcbcfcb9c758e15599fc6cdb0edd032
