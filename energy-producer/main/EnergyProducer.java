package at.technikum.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;

@Component
public class EnergyProducer {

    private final RabbitTemplate rabbitTemplate;
    private final Random random = new Random();

    public EnergyProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Scheduled(fixedDelay = 5000)
    public void sendEnergy() {
        double kwh = 0.001 + (0.009 * random.nextDouble());
        EnergyMessage msg = new EnergyMessage("PRODUCER", "COMMUNITY", kwh, LocalDateTime.now());
        rabbitTemplate.convertAndSend("energy", msg);
        System.out.println("Sent: " + msg.kwh + " kWh at " + msg.datetime);
    }
}
