package at.technikum.gui;

import com.fasterxml.jackson.annotation.JsonProperty;
import javafx.beans.property.*;

public class UsageData {
    private final StringProperty hour = new SimpleStringProperty();
    private final DoubleProperty communityUsage = new SimpleDoubleProperty();
    private final DoubleProperty gridUsage = new SimpleDoubleProperty();
    private final DoubleProperty gridPortion = new SimpleDoubleProperty();

    @JsonProperty("hour")
    public String getHour() {
        return hour.get();
    }

    public void setHour(String hour) {
        this.hour.set(hour);
    }

    public StringProperty hourProperty() {
        return hour;
    }

    @JsonProperty("communityUsed")
    public double getCommunityUsage() {
        return communityUsage.get();
    }

    public void setCommunityUsage(double value) {
        this.communityUsage.set(value);
    }

    public DoubleProperty communityUsageProperty() {
        return communityUsage;
    }

    @JsonProperty("gridUsed")
    public double getGridUsage() {
        return gridUsage.get();
    }

    public void setGridUsage(double value) {
        this.gridUsage.set(value);
    }

    public DoubleProperty gridUsageProperty() {
        return gridUsage;
    }

    @JsonProperty("gridPortion")
    public double getGridPortion() {
        return gridPortion.get();
    }

    public void setGridPortion(double value) {
        this.gridPortion.set(value);
    }

    public DoubleProperty gridPortionProperty() {
        return gridPortion;
    }
}
