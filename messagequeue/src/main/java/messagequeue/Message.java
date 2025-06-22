package messagequeue;

import java.io.Serializable;

public class Message implements Serializable {

    public enum MessageType {
        PRODUCER,
        USER // <-- Added USER so your EnergyUserApplication works
    }

    private String sender;
    private String association;
    private MessageType type;
    private double amount;
    private String datetime;

    // WICHTIG: Default-Konstruktor für Deserialisierung
    public Message() {}

    public Message(String sender, MessageType type, double amount, String association, String datetime) {
        this.sender = sender;
        this.type = type;
        this.amount = amount;
        this.association = association;
        this.datetime = datetime;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getAssociation() {
        return association;
    }

    public void setAssociation(String association) {
        this.association = association;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return "[" + type + "] from " + sender +
                ", association=" + association +
                ", amount=" + amount + " kWh" +
                ", datetime=" + datetime;
    }
}
