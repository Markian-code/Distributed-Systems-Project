package at.technikum.percentage;

import at.technikum.percentage.model.CurrentPercentage;
import at.technikum.percentage.model.UpdateMessage;
import at.technikum.percentage.repository.CurrentPercentageRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Optional;

@SpringBootApplication
@EnableScheduling
public class CurrentPercentageApplication {

    @Autowired
    private CurrentPercentageRepository currentPercentageRepository;

    public static void main(String[] args) {
        SpringApplication.run(CurrentPercentageApplication.class, args);
    }

    @RabbitListener(queues = "update-notification-queue")
    public void handleUpdateMessage(UpdateMessage msg) {
        System.out.println("📩 Received update message from update-notification-queue: " + msg);

        double produced = msg.getProduced();
        double used = msg.getUsed();
        double grid = msg.getGrid();

        double communityDepleted = (produced == 0) ? 100.0 : Math.min((used / produced) * 100.0, 100.0);
        double gridPortion = (produced + grid == 0) ? 0.0 : (grid / (produced + grid)) * 100.0;

        Optional<CurrentPercentage> optional = currentPercentageRepository.findByHour(msg.getHour());
        CurrentPercentage data = optional.orElse(new CurrentPercentage(msg.getHour(), 0.0, 0.0));

        data.setCommunityDepleted(communityDepleted);
        data.setGridPortion(gridPortion);

        currentPercentageRepository.save(data);

        System.out.println("✅ Percentage data saved: " + data);
    }

    @Bean
    public Queue updateQueue() {
        return new Queue("update-notification-queue", true);
    }

    @Bean
    public MessageConverter messageConverter() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return new Jackson2JsonMessageConverter(mapper);
    }
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }
}
