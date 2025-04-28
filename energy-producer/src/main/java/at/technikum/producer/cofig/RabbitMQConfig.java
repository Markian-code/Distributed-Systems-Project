package at.technikum.producer.config;

import org.springframework.amqp.core.Queue;
<<<<<<< HEAD
=======
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
>>>>>>> fe53726eedcbcfcb9c758e15599fc6cdb0edd032
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

<<<<<<< HEAD
    public static final String QUEUE_NAME = "energy-data-queue"; // ✅ Diese Zeile hinzufügen!

    @Bean
    public Queue energyQueue() {
        return new Queue(QUEUE_NAME);
=======
    public static final String QUEUE_NAME = "energy-data-queue";

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME, false);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
>>>>>>> fe53726eedcbcfcb9c758e15599fc6cdb0edd032
    }
}
