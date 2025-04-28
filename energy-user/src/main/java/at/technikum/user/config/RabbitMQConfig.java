package at.technikum.user.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String ENERGY_USAGE_QUEUE = "energy-usage-queue";
    public static final String ENERGY_DATA_QUEUE = "energy-data-queue";

    @Bean
    public Queue energyUsageQueue() {
        return new Queue(ENERGY_USAGE_QUEUE, false);
    }

    @Bean
    public Queue energyDataQueue() {
        return new Queue(ENERGY_DATA_QUEUE, false);
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter converter) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(converter);
        return template;
    }
}
