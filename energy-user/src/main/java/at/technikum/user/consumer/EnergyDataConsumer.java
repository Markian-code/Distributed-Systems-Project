package at.technikum.user.consumer;

import at.technikum.user.model.EnergyData;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class EnergyDataConsumer {

    @RabbitListener(queues = "energy-data-queue")
    public void consumeEnergyData(EnergyData energyData) {
        System.out.println("Empfangen: " + energyData.getProducerId() +
                " hat " + energyData.getKWh() + " kWh um " + energyData.getTimestamp());
    }
}
