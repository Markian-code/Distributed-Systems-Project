package messagequeue;

import java.io.Serializable;

public class Message implements Serializable {

    public enum MessageType {
        PRODUCE,
        CONSUME
    }

    private String sender;
    private MessageType type;
    private double amount;

    // WICHTIG: Default-Konstruktor für Deserialisierung
    public Message() {}

    public Message(String sender, MessageType type, double amount) {
        this.sender = sender;
        this.type = type;
        this.amount = amount;
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

    @Override
    public String toString() {
        return "[" + type + "] from " + sender + ": " + amount + " kWh";
    }
}
