package at.technikum.api.model;

import java.io.Serializable;

public class EnergyData implements Serializable {

    private int hour;
    private double usage;
    private double production;

    public EnergyData() {
    }

    public EnergyData(int hour, double usage, double production) {
        this.hour = hour;
        this.usage = usage;
        this.production = production;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public double getUsage() {
        return usage;
    }

    public void setUsage(double usage) {
        this.usage = usage;
    }

    public double getProduction() {
        return production;
    }

    public void setProduction(double production) {
        this.production = production;
    }

    @Override
    public String toString() {
        return "EnergyData{" +
                "hour=" + hour +
                ", usage=" + usage +
                ", production=" + production +
                '}';
    }
}
