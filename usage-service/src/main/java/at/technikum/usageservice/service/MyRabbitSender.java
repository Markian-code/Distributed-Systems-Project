package at.technikum.usageservice.service;

import at.technikum.percentage.model.UpdateMessage;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyRabbitSender {

    private final AmqpTemplate amqpTemplate;

    @Autowired
    public MyRabbitSender(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void sendUpdate(UpdateMessage update) {
        amqpTemplate.convertAndSend("update-notification-queue", update);
        System.out.println("📤 UpdateMessage sent to update-notification-queue: " + update);
    }
}
