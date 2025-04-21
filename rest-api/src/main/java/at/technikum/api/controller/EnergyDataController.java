package at.technikum.api.controller;

import at.technikum.api.repository.FakeEnergyDataRepository;
import at.technikum.api.model.EnergyData;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/energy")
public class EnergyDataController {

    private final FakeEnergyDataRepository repository;

    public EnergyDataController(FakeEnergyDataRepository repository) {
        this.repository = repository;
    }

    // /energy/current
    @GetMapping("/current")
    public EnergyData getCurrentEnergyData() {
        return repository.getCurrentHourData();
    }

    // /energy/history?hour=10
    @GetMapping("/history")
    public EnergyData getEnergyByHour(@RequestParam(name = "hour") int hour) {
        return repository.getByHour(hour);
    }

    // /energy/history-range?from=9&to=11
    @GetMapping("/history-range")
    public List<EnergyData> getEnergyByRange(@RequestParam(name = "from") int from,
                                             @RequestParam(name = "to") int to) {
        List<EnergyData> result = new ArrayList<>();
        for (int i = from; i <= to; i++) {
            try {
                result.add(repository.getByHour(i));
            } catch (Exception ignored) {

            }
        }
        return result;
    }
}
