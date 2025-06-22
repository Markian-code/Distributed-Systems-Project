package at.technikum.producer;

import at.technikum.producer.model.EnergyMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

@SpringBootApplication
@EnableScheduling
public class EnergyProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnergyProducerApplication.class, args);
    }
}
