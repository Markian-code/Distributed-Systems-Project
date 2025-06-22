package at.technikum.user.service;

import at.technikum.user.model.EnergyData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Random;

@Service
public class EnergyUsageService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private final Random random = new Random();

    @Scheduled(fixedDelayString = "#{T(java.util.concurrent.ThreadLocalRandom).current().nextInt(2000, 5000)}")
    public void sendUsage() {

        double kWh = calculateUsageBasedOnTime();

        String type = "USER";
        String association = "COMMUNITY";
        String datetime = LocalDateTime.now().toString();

        EnergyData message = new EnergyData(type, association, kWh, datetime);

        // Send to queue
        rabbitTemplate.convertAndSend("energy-data-queue", message);

        // Print
        System.out.printf("✅ Sent USER message: [USER], association=%s, kwh=%.4f, datetime=%s%n",
                association, kWh, datetime);
    }

    private double calculateUsageBasedOnTime() {
        int hour = LocalTime.now().getHour();
        double base = 0.001 + (0.005 - 0.001) * random.nextDouble();

        // Peak hours (morning and evening)
        if ((hour >= 7 && hour <= 10) || (hour >= 18 && hour <= 22)) {
            base *= 2.5;
        }

        return base;
    }
}
