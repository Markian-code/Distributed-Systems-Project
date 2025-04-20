package at.technikum.api.controller;

import at.technikum.api.repository.FakeEnergyDataRepository;
import at.technikum.api.model.EnergyData;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/energy")
public class EnergyDataController {

    @GetMapping("/current")
    public EnergyData getCurrentEnergyData() {
        return FakeEnergyDataRepository.getCurrentHourData();
    }

    @GetMapping("/history")
    public EnergyData getEnergyByHour(@RequestParam(name = "hour") int hour) {
        return FakeEnergyDataRepository.getByHour(hour);
    }
}
