package at.technikum.producer.service;

import at.technikum.producer.model.EnergyMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class EnergyGenerationService {

    private final RabbitTemplate rabbitTemplate;
    private final WeatherService weatherService;
    private final Random random = new Random();

    public EnergyGenerationService(RabbitTemplate rabbitTemplate, WeatherService weatherService) {
        this.rabbitTemplate = rabbitTemplate;
        this.weatherService = weatherService;
    }

    @Scheduled(fixedDelayString = "#{T(java.util.concurrent.ThreadLocalRandom).current().nextInt(1000, 5000)}")
    public void generateEnergy() {

        double cloudiness = weatherService.getCurrentCloudiness();
        double sunlightFactor = (100.0 - cloudiness) / 100.0;

        double kWh = 0.001 + (0.01 - 0.001) * random.nextDouble();
        kWh = kWh * sunlightFactor;

        String association = "COMMUNITY";
        String type = "PRODUCER";
        String datetime = LocalDateTime.now().toString();

        EnergyMessage message = new EnergyMessage(type, association, kWh, datetime);

        rabbitTemplate.convertAndSend("energy-data-queue", message);

        // Verbessertes Log
        System.out.printf(
                "✅ Sent EnergyMessage: [type=%s, association=%s, amount=%.4f kWh, cloudiness=%.1f%%, datetime=%s]%n",
                type, association, kWh, cloudiness, datetime
        );
    }
}
