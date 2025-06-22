package at.technikum.percentage.repository;

import at.technikum.percentage.model.CurrentPercentage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface CurrentPercentageRepository extends JpaRepository<CurrentPercentage, Long> {

    Optional<CurrentPercentage> findByHour(LocalDateTime usageHour);

}
