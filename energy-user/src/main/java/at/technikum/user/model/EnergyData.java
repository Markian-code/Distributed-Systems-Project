package at.technikum.user.model;

public class EnergyData {

    private String type;
    private String association;
    private double kwh;
    private String datetime;

    // Constructor
    public EnergyData(String type, String association, double kwh, String datetime) {
        this.type = type;
        this.association = association;
        this.kwh = kwh;
        this.datetime = datetime;
    }

    // Getters and setters
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getAssociation() { return association; }
    public void setAssociation(String association) { this.association = association; }

    public double getKwh() { return kwh; }
    public void setKwh(double kwh) { this.kwh = kwh; }

    public String getDatetime() { return datetime; }
    public void setDatetime(String datetime) { this.datetime = datetime; }

    @Override
    public String toString() {
        return "[USER], association=" + association
                + ", kwh=" + String.format("%.4f", kwh)
                + ", datetime=" + datetime;
    }
}
