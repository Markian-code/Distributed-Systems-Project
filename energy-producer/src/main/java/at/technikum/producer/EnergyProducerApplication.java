package at.technikum.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
<<<<<<< HEAD

@SpringBootApplication
public class EnergyProducerApplication {

=======
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EnergyProducerApplication {
>>>>>>> fe53726eedcbcfcb9c758e15599fc6cdb0edd032
    public static void main(String[] args) {
        SpringApplication.run(EnergyProducerApplication.class, args);
    }
}
