package at.technikum.usageservice.service;

import at.technikum.usageservice.dto.EnergyDataDTO;
import at.technikum.usageservice.model.UsageData;
import at.technikum.usageservice.repository.UsageRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class UsageMessageConsumer {

    private final UsageRepository usageDataRepository;

    public UsageMessageConsumer(UsageRepository usageDataRepository) {
        this.usageDataRepository = usageDataRepository;
    }

    @RabbitListener(queues = "energy-data-queue")
    public void receiveMessage(String message) {
        System.out.println("Empfangen: " + message);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            EnergyDataDTO dto = objectMapper.readValue(message, EnergyDataDTO.class);

            ZonedDateTime zdt = Instant.ofEpochMilli(dto.getTimestamp()).atZone(ZoneId.systemDefault());
            int hour = zdt.getHour();

            UsageData usageData = new UsageData();
            usageData.setHour(hour);
            usageData.setCommunityProduced(dto.getKwh());
            usageData.setCommunityUsed(0.0);
            usageData.setGridUsed(0.0);

            System.out.println("Verarbeite Verbrauchsdaten: Stunde=" + hour +
                    ", erzeugt=" + usageData.getCommunityProduced() +
                    ", verbraucht=" + usageData.getCommunityUsed() +
                    ", Netz=" + usageData.getGridUsed());

            double totalUsed = usageData.getCommunityUsed() + usageData.getGridUsed();
            double percentage = totalUsed > 0 ? (usageData.getCommunityUsed() / totalUsed) * 100 : 0.0;
            System.out.printf("Aktuelle Selbstversorgungsquote: %.2f%%%n", percentage);

            usageDataRepository.save(usageData);

        } catch (Exception e) {
            System.err.println("Fehler beim Verarbeiten der Nachricht: " + e.getMessage());
        }
    }
}
