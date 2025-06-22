package at.technikum.api.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "usage_data")  // your table name
public class UsageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usage_hour")
    private LocalDateTime usageHour;

    @Column(name = "community_produced")
    private double communityProduced;

    @Column(name = "community_used")
    private double communityUsed;

    @Column(name = "grid_used")
    private double gridUsed;

    // Getters and Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getUsageHour() { return usageHour; }
    public void setUsageHour(LocalDateTime usageHour) { this.usageHour = usageHour; }

    public double getCommunityProduced() { return communityProduced; }
    public void setCommunityProduced(double communityProduced) { this.communityProduced = communityProduced; }

    public double getCommunityUsed() { return communityUsed; }
    public void setCommunityUsed(double communityUsed) { this.communityUsed = communityUsed; }

    public double getGridUsed() { return gridUsed; }
    public void setGridUsed(double gridUsed) { this.gridUsed = gridUsed; }
}
