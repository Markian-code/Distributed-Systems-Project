package messagequeue;

public class Message {
    public enum MessageType { PRODUCE, CONSUME }

    private final String sender;
    private final MessageType type;
    private final double amount;

    public Message(String sender, MessageType type, double amount) {
        this.sender = sender;
        this.type = type;
        this.amount = amount;
    }

    public String getSender() {
        return sender;
    }

    public MessageType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "[" + type + "] from " + sender + ": " + amount + " kWh";
    }
}
