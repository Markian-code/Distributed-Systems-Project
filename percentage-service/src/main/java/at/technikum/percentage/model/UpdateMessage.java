package at.technikum.percentage.model;

import java.time.LocalDateTime;

public class UpdateMessage {
    private LocalDateTime hour;
    private double produced;
    private double used;
    private double grid;

    public UpdateMessage() {}  // Default constructor

    public UpdateMessage(LocalDateTime hour, double produced, double used, double grid) {
        this.hour = hour;
        this.produced = produced;
        this.used = used;
        this.grid = grid;
    }

    // Getters and setters
    public LocalDateTime getHour() { return hour; }
    public void setHour(LocalDateTime hour) { this.hour = hour; }

    public double getProduced() { return produced; }
    public void setProduced(double produced) { this.produced = produced; }

    public double getUsed() { return used; }
    public void setUsed(double used) { this.used = used; }

    public double getGrid() { return grid; }
    public void setGrid(double grid) { this.grid = grid; }

    @Override
    public String toString() {
        return "[hour=" + hour + ", produced=" + produced + ", used=" + used + ", grid=" + grid + "]";
    }
}
