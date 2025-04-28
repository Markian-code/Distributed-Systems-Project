package at.technikum.producer.service;

import at.technikum.producer.model.EnergyData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EnergyGenerationService {

    private static final String QUEUE_NAME = "energy-data-queue";

    private final RabbitTemplate rabbitTemplate;
    private final Random random = new Random();

    public EnergyGenerationService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Scheduled(fixedDelayString = "#{T(java.util.concurrent.ThreadLocalRandom).current().nextInt(1000, 5000)}")
    public void generateEnergy() {
        double kWh = 1 + random.nextDouble() * 10; // zwischen 1.0 und 11.0
        long timestamp = System.currentTimeMillis();
        String producerId = "producer-1";

        EnergyData data = new EnergyData(producerId, kWh, timestamp);

        rabbitTemplate.convertAndSend(QUEUE_NAME, data);

        System.out.println("✅ Gesendet: " + kWh + " kWh von " + producerId + " um " + timestamp);
    }
}
