package at.technikum.user.consumer;

import at.technikum.user.config.RabbitMQConfig;
import at.technikum.user.model.EnergyData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class EnergyDataConsumer {

    private final RabbitTemplate rabbitTemplate;
    private final Random random = new Random();

    public EnergyDataConsumer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Scheduled(fixedDelayString = "#{T(java.util.concurrent.ThreadLocalRandom).current().nextInt(1000, 5000)}")
    public void sendUsageMessage() {
        double kWh = 0.5 + random.nextDouble() * 5.0; // z.B. 0.5 - 5.5 kWh
        EnergyData data = new EnergyData("user-1", kWh, System.currentTimeMillis());
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, data);
        System.out.println("Usage-Nachricht gesendet: " + kWh + " kWh");
    }
}
