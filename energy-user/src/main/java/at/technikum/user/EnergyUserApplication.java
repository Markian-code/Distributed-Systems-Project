package at.technikum.user;

import messagequeue.Message;
import messagequeue.Message.MessageType;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.Random;

@SpringBootApplication
public class EnergyUserApplication implements CommandLineRunner {

    @Autowired
    private AmqpTemplate amqpTemplate;

    private static final String QUEUE_NAME = "energy-data-queue";

    public static void main(String[] args) {
        SpringApplication.run(EnergyUserApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Random random = new Random();

        while (true) {
            double kwhUsed = 0.001 + (0.01 - 0.001) * random.nextDouble();

            Message message = new Message();
            message.setType(MessageType.USER);
            message.setSender("community-user-1"); // <--- Important: add sender!
            message.setAssociation("COMMUNITY");
            message.setAmount(kwhUsed);
            message.setDatetime(LocalDateTime.now().toString());

            amqpTemplate.convertAndSend(QUEUE_NAME, message);
            System.out.println("✅ Sent USER message: " + message);

            Thread.sleep(2000);
        }
    }

}
