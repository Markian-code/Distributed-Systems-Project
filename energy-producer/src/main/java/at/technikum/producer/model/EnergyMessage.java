package at.technikum.producer.model;

public class EnergyMessage {

    private String type;          // "PRODUCER" or "USER"
    private String association;   // "COMMUNITY"
    private double amount;        // amount of energy
    private String datetime;      // ISO format, e.g. "2025-06-17T22:30:00"

    public EnergyMessage() {}

    public EnergyMessage(String type, String association, double amount, String datetime) {
        this.type = type;
        this.association = association;
        this.amount = amount;
        this.datetime = datetime;
    }

    // Getters and Setters
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getAssociation() { return association; }
    public void setAssociation(String association) { this.association = association; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getDatetime() { return datetime; }
    public void setDatetime(String datetime) { this.datetime = datetime; }

    @Override
    public String toString() {
        return "EnergyMessage{" +
                "type='" + type + '\'' +
                ", association='" + association + '\'' +
                ", amount=" + amount +
                ", datetime='" + datetime + '\'' +
                '}';
    }
}
