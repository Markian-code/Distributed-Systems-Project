package at.technikum.api.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_NAME = "energy-data-queue";

    @Bean
    public Queue energyDataQueue() {
        return new Queue(QUEUE_NAME, false);
    }
}
