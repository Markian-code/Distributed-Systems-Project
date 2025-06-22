package at.technikum.usageservice.repository;

import at.technikum.usageservice.model.UsageData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface UsageRepository extends JpaRepository<UsageData, Long> {

    // Holt einen Eintrag zur gegebenen Stunde (falls vorhanden)
    Optional<UsageData> findByHour(LocalDateTime hour);
}