package at.technikum.percentage.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class CurrentPercentage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usage_hour")
    private LocalDateTime hour;

    private double communityDepleted;
    private double gridPortion;

    // Default constructor required by JPA
    public CurrentPercentage() {}

    // Constructor for convenience
    public CurrentPercentage(LocalDateTime hour, double communityDepleted, double gridPortion) {
        this.hour = hour;
        this.communityDepleted = communityDepleted;
        this.gridPortion = gridPortion;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public LocalDateTime getHour() {
        return hour;
    }

    public void setHour(LocalDateTime hour) {
        this.hour = hour;
    }

    public double getCommunityDepleted() {
        return communityDepleted;
    }

    public void setCommunityDepleted(double communityDepleted) {
        this.communityDepleted = communityDepleted;
    }

    public double getGridPortion() {
        return gridPortion;
    }

    public void setGridPortion(double gridPortion) {
        this.gridPortion = gridPortion;
    }

    @Override
    public String toString() {
        return "[hour=" + hour +
                ", communityDepleted=" + communityDepleted +
                ", gridPortion=" + gridPortion + "]";
    }
}
