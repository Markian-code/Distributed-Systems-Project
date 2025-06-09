package at.technikum.usageservice.service;

import at.technikum.usageservice.model.UsageData;
import at.technikum.usageservice.repository.UsageRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class UsageMessageConsumer {

    private final UsageRepository usageDataRepository;

    public UsageMessageConsumer(UsageRepository usageDataRepository) {
        this.usageDataRepository = usageDataRepository;
    }

    @RabbitListener(queues = "energy-usage-queue")
    public void receiveMessage(String message) {
        System.out.println("Empfangen: " + message);

        try {
            String[] parts = message.split(",");
            int hour = Integer.parseInt(parts[0].split("=")[1]);
            double communityProduced = Double.parseDouble(parts[1].split("=")[1]);
            double communityUsed = Double.parseDouble(parts[2].split("=")[1]);
            double gridUsed = Double.parseDouble(parts[3].split("=")[1]);

            System.out.println("Verarbeite Verbrauchsdaten: Stunde=" + hour +
                    ", erzeugt=" + communityProduced +
                    ", verbraucht=" + communityUsed +
                    ", Netz=" + gridUsed);

            double totalUsed = communityUsed + gridUsed;
            double percentage;
            if (totalUsed > 0) {
                percentage = (communityUsed / totalUsed) * 100;
            } else {
                percentage = 0.0;
            }

            System.out.printf("Aktuelle Selbstversorgungsquote: %.2f%%%n", percentage);

            UsageData usageData = new UsageData(hour, communityProduced, communityUsed, gridUsed);
            usageDataRepository.save(usageData);

        } catch (Exception e) {
            System.err.println("Fehler beim Verarbeiten der Nachricht: " + e.getMessage());
        }
    }
}
