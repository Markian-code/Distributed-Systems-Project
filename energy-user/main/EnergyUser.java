package at.technikum.user;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class EnergyUser {

    private final RabbitTemplate rabbitTemplate;

    @Value("${energy.queue.name}")
    private String queueName;

    public EnergyUser(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendUsage(String userId, double amount) {
        EnergyMessage message = new EnergyMessage(userId, amount, LocalDateTime.now());
        rabbitTemplate.convertAndSend(queueName, message);
        System.out.println("[EnergyUser] Sent: " + message.getAmount() + " kWh by user " + message.getUserId());
    }
}
