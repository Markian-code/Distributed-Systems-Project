package at.technikum.api.model;

public class UsageData {
    private String hour;
    private double communityUsed;
    private double gridUsed;
    private double gridPortion;

    public UsageData() {}

    public UsageData(String hour, double communityUsed, double gridUsed, double gridPortion) {
        this.hour = hour;
        this.communityUsed = communityUsed;
        this.gridUsed = gridUsed;
        this.gridPortion = gridPortion;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
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

    public double getGridPortion() {
        return gridPortion;
    }

    public void setGridPortion(double gridPortion) {
        this.gridPortion = gridPortion;
    }
}
