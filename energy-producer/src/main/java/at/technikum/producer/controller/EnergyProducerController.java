package at.technikum.producer.controller;

import at.technikum.producer.service.EnergyGenerationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnergyProducerController {

    private final EnergyGenerationService energyGenerationService;

    public EnergyProducerController(EnergyGenerationService energyGenerationService) {
        this.energyGenerationService = energyGenerationService;
    }

    @GetMapping("/generate")
    public String triggerEnergyGeneration() {
        energyGenerationService.generateEnergy();
        return "Energie wurde erfolgreich generiert und gesendet!";
    }
}
