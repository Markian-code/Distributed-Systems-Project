package at.technikum.user;

import java.io.Serializable;
import java.time.LocalDateTime;

public class EnergyMessage implements Serializable {
    private String userId;
    private double amount;
    private LocalDateTime timestamp;

    public EnergyMessage() {}

    public EnergyMessage(String userId, double amount, LocalDateTime timestamp) {
        this.userId = userId;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
