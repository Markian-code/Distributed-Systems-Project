package at.technikum.usageservice.service;

import at.technikum.usageservice.model.UsageData;
import at.technikum.usageservice.repository.UsageRepository;
import messagequeue.Message;
import messagequeue.Message.MessageType;
import at.technikum.percentage.model.UpdateMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UsageMessageHandler {

    @Autowired
    private UsageRepository usageRepository;

    @Autowired
    private MyRabbitSender rabbitSender;

    @RabbitListener(queues = "energy-data-queue")
    public void handleMessage(Message message) {
        System.out.println("📥 Nachricht empfangen: " + message);

        LocalDateTime currentHour = LocalDateTime.now().withMinute(0).withSecond(0).withNano(0);

        double amount = message.getAmount();

        Optional<UsageData> optional = usageRepository.findByHour(currentHour);
        UsageData usageData = optional.orElse(new UsageData(currentHour, 0.0, 0.0, 0.0));

        if (message.getType() == MessageType.PRODUCER) {
            usageData.setCommunityProduced(usageData.getCommunityProduced() + amount);
            System.out.println("✅ Community-Produktion aktualisiert: +" + amount + " kWh in Stunde " + currentHour.getHour());
        }
        else if (message.getType() == MessageType.USER) {
            double availableProduction = usageData.getCommunityProduced();

            if (availableProduction >= amount) {
                usageData.setCommunityProduced(availableProduction - amount);
                usageData.setCommunityUsed(usageData.getCommunityUsed() + amount);
                System.out.println("✅ Community-Verbrauch aktualisiert: +" + amount + " kWh in Stunde " + currentHour.getHour() + " (voll gedeckt)");
            }
            else {
                double fromCommunity = availableProduction;
                double fromGrid = amount - availableProduction;

                usageData.setCommunityProduced(0.0);
                usageData.setCommunityUsed(usageData.getCommunityUsed() + fromCommunity);
                usageData.setGridUsed(usageData.getGridUsed() + fromGrid);

                System.out.println("✅ Verbrauch aktualisiert: +" + fromCommunity + " kWh aus Community, +" + fromGrid + " kWh aus Netz in Stunde " + currentHour.getHour());
            }
        }
        else {
            System.out.println("⚠️ Unbekannter Message-Typ – ignoriert.");
            return;
        }

        usageRepository.save(usageData);

        // Send update
        UpdateMessage update = new UpdateMessage(
                usageData.getHour(),
                usageData.getCommunityProduced(),
                usageData.getCommunityUsed(),
                usageData.getGridUsed()
        );

        rabbitSender.sendUpdate(update);
    }
}
