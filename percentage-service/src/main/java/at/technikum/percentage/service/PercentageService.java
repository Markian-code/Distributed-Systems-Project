package at.technikum.percentage.service;

import at.technikum.percentage.model.CurrentPercentage;
import at.technikum.percentage.repository.CurrentPercentageRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class PercentageService {

    private final CurrentPercentageRepository repository;

    @Autowired
    public PercentageService(CurrentPercentageRepository repository) {
        this.repository = repository;
    }

    @RabbitListener(queues = "current-percentage-queue")
    public void receiveMessage(CurrentPercentage currentPercentage) {
        System.out.println("Received message: " + currentPercentage);
        repository.save(currentPercentage);
    }
}
