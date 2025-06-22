package at.technikum.api.service;

import at.technikum.api.model.CurrentPercentageEntity;
import at.technikum.api.model.UsageData;
import at.technikum.api.model.UsageEntity;
import at.technikum.api.repository.CurrentPercentageRepository;
import at.technikum.api.repository.UsageRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnergyDataService {

    private final UsageRepository usageRepository;
    private final CurrentPercentageRepository currentPercentageRepository;

    public EnergyDataService(UsageRepository usageRepository, CurrentPercentageRepository currentPercentageRepository) {
        this.usageRepository = usageRepository;
        this.currentPercentageRepository = currentPercentageRepository;
    }

    public CurrentPercentageEntity getCurrentData() {
        return currentPercentageRepository.findTopByOrderByUsageHourDesc();
    }

    public UsageData getUsageForHour(int hour) {
        // Build LocalDateTime for today with the given hour
        LocalDateTime targetHour = LocalDate.now().atTime(hour, 0);

        // Find closest record for this hour (could be adjusted)
        UsageEntity entity = usageRepository.findTopByUsageHourLessThanEqualOrderByUsageHourDesc(targetHour);

        if (entity == null) {
            return new UsageData(targetHour.toString(), 0, 0, 0);
        }

        double totalUsage = entity.getCommunityUsed() + entity.getGridUsed();
        double gridPortion = totalUsage == 0 ? 0 : (entity.getGridUsed() / totalUsage) * 100;

        return new UsageData(
                entity.getUsageHour().toString(),
                entity.getCommunityUsed(),
                entity.getGridUsed(),
                gridPortion
        );
    }

    public List<UsageData> getHistoricalData(LocalDate start, LocalDate end) {
        LocalDateTime startDateTime = start.atStartOfDay();
        LocalDateTime endDateTime = end.atTime(LocalTime.MAX);

        List<UsageEntity> entities = usageRepository.findByUsageHourBetween(startDateTime, endDateTime);

        return entities.stream().map(entity -> {
            double totalUsage = entity.getCommunityUsed() + entity.getGridUsed();
            double gridPortion = totalUsage == 0 ? 0 : (entity.getGridUsed() / totalUsage) * 100;

            return new UsageData(
                    entity.getUsageHour().toString(),
                    entity.getCommunityUsed(),
                    entity.getGridUsed(),
                    gridPortion
            );
        }).collect(Collectors.toList());
    }
}
