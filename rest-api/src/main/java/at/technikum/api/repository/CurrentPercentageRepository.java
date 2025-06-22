package at.technikum.api.repository;

import at.technikum.api.model.CurrentPercentageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrentPercentageRepository extends JpaRepository<CurrentPercentageEntity, Long> {
    CurrentPercentageEntity findTopByOrderByUsageHourDesc();
}
