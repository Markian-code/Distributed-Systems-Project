package at.technikum.api.controller;

import at.technikum.api.config.RabbitMQConfig;
import at.technikum.api.model.EnergyData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producer")
public class EnergyDataProducer {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    public EnergyDataProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = new ObjectMapper();
    }

    @PostMapping("/send")
    public String sendEnergyData(@RequestBody EnergyData data) {
        try {
            String jsonMessage = objectMapper.writeValueAsString(data);
            rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, jsonMessage);
            return "Message sent: " + jsonMessage;
        } catch (JsonProcessingException e) {
            return "Failed to serialize message: " + e.getMessage();
        }
    }
}
