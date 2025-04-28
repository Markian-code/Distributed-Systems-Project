package at.technikum.percentage.repository;

import at.technikum.percentage.model.CurrentPercentage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrentPercentageRepository extends JpaRepository<CurrentPercentage, Long> {
}
