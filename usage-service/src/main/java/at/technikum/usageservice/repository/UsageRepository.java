package at.technikum.usageservice.repository;

import at.technikum.usageservice.model.UsageData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsageRepository extends JpaRepository<UsageData, Long> {
    UsageData findByHour(int hour);
}