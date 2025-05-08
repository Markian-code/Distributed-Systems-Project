package at.technikum.usageservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class UsageData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int hour;
    private double communityProduced;
    private double communityUsed;
    private double gridUsed;

    public UsageData() {}

    public UsageData(int hour, double communityProduced, double communityUsed, double gridUsed) {
        this.hour = hour;
        this.communityProduced = communityProduced;
        this.communityUsed = communityUsed;
        this.gridUsed = gridUsed;
    }

    public Long getId() {
        return id;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public double getCommunityProduced() {
        return communityProduced;
    }

    public void setCommunityProduced(double communityProduced) {
        this.communityProduced = communityProduced;
    }

    public double getCommunityUsed() {
        return communityUsed;
    }

    public void setCommunityUsed(double communityUsed) {
        this.communityUsed = communityUsed;
    }

    public double getGridUsed() {
        return gridUsed;
    }

    public void setGridUsed(double gridUsed) {
        this.gridUsed = gridUsed;
    }
}
