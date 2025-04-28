package at.technikum.usageservice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsageRepository extends JpaRepository<UsageData, Long> {
    UsageData findByHour(int hour);
}
