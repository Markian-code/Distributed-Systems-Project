package at.technikum.api.service;

import at.technikum.api.config.RabbitMQConfig;
import at.technikum.api.messagequeue.EnergyData;
import at.technikum.api.model.CurrentPercentageEntity;
import at.technikum.api.model.UsageEntity;
import at.technikum.api.repository.CurrentPercentageRepository;
import at.technikum.api.repository.UsageRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class EnergyDataConsumer {

    private final UsageRepository usageRepository;
    private final CurrentPercentageRepository currentPercentageRepository;

    public EnergyDataConsumer(UsageRepository usageRepository, CurrentPercentageRepository currentPercentageRepository) {
        this.usageRepository = usageRepository;
        this.currentPercentageRepository = currentPercentageRepository;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receiveMessage(EnergyData data) {
        System.out.println("📩 Received message: " + data);

        // Parse hour string to LocalDateTime
        LocalDateTime usageHour = LocalDateTime.parse(data.getHour(), DateTimeFormatter.ISO_DATE_TIME);

        // Save UsageEntity
        UsageEntity usageEntity = new UsageEntity();
        usageEntity.setUsageHour(usageHour);
        usageEntity.setCommunityProduced(data.getCommunityProduced());
        usageEntity.setCommunityUsed(data.getCommunityUsed());
        usageEntity.setGridUsed(data.getGridUsed());

        usageRepository.save(usageEntity);
        System.out.println("✅ Saved UsageEntity: " + usageEntity);

        // Calculate gridPortion
        double totalUsage = data.getCommunityUsed() + data.getGridUsed();
        double gridPortion = (totalUsage == 0) ? 0.0 : (data.getGridUsed() / totalUsage) * 100;

        // Save CurrentPercentageEntity
        CurrentPercentageEntity currentEntity = new CurrentPercentageEntity();
        currentEntity.setUsageHour(usageHour);
        currentEntity.setCommunityDepleted(100 - gridPortion);  // example calculation
        currentEntity.setGridPortion(gridPortion);

        currentPercentageRepository.save(currentEntity);
        System.out.println("✅ Saved CurrentPercentageEntity: " + currentEntity);
    }
}
