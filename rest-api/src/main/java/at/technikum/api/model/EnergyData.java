package at.technikum.api.model;

public class EnergyData {
    private int hour;
    private double usage;
    private double production;

    public EnergyData(int hour, double usage, double production) {
        this.hour = hour;
        this.usage = usage;
        this.production = production;
    }

    public int getHour() { return hour; }
    public double getUsage() { return usage; }
    public double getProduction() { return production; }
}
