package at.technikum.usageservice.service;

import at.technikum.usageservice.model.EnergyMessage;
import at.technikum.usageservice.model.UsageData;
import at.technikum.usageservice.repository.UsageRepository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class UsageConsumer {

    private final UsageRepository usageRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public UsageConsumer(UsageRepository usageRepository) {
        this.usageRepository = usageRepository;
    }

    @RabbitListener(queues = "energy_queue")
    public void receiveMessage(String messageJson) {
        try {
            EnergyMessage message = objectMapper.readValue(messageJson, EnergyMessage.class);

            int hour = Integer.parseInt(message.getDatetime().substring(11, 13));

            UsageData usageData = usageRepository.findByHour(hour);
            if (usageData == null) {
                usageData = new UsageData(hour, 0, 0, 0);
            }

            if (message.getType().equals("PRODUCER")) {
                usageData.setCommunityProduced(usageData.getCommunityProduced() + message.getKwh());
            } else if (message.getType().equals("USER")) {
                double communityLeft = usageData.getCommunityProduced() - usageData.getCommunityUsed();
                if (communityLeft >= message.getKwh()) {
                    usageData.setCommunityUsed(usageData.getCommunityUsed() + message.getKwh());
                } else {
                    usageData.setCommunityUsed(usageData.getCommunityProduced());
                    usageData.setGridUsed(usageData.getGridUsed() + (message.getKwh() - communityLeft));
                }
            }

            usageRepository.save(usageData);
            System.out.println("Updated usage for hour " + hour);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
