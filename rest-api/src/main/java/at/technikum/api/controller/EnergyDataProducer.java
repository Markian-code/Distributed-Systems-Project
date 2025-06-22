package at.technikum.api.controller;

import at.technikum.api.config.RabbitMQConfig;
import at.technikum.api.messagequeue.EnergyData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producer")
@CrossOrigin(origins = "*")
public class EnergyDataProducer {

    private final RabbitTemplate rabbitTemplate;

    public EnergyDataProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("/send")
    public String sendEnergyData(@RequestBody EnergyData data) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, data);
        return "✅ Message sent to queue: " + data;
    }
}
