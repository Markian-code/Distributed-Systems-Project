package at.technikum.api.repository;

import at.technikum.api.model.UsageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UsageRepository extends JpaRepository<UsageEntity, Long> {

    // For /historical
    List<UsageEntity> findByUsageHourBetween(LocalDateTime start, LocalDateTime end);

    // For /hourly
    UsageEntity findTopByUsageHourLessThanEqualOrderByUsageHourDesc(LocalDateTime targetHour);

}
