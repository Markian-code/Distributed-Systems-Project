package at.technikum.api.controller;

import at.technikum.api.model.CurrentPercentageEntity;
import at.technikum.api.model.UsageData;
import at.technikum.api.service.EnergyDataService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/energy")
@CrossOrigin(origins = "*")
public class EnergyDataController {

    private final EnergyDataService energyDataService;

    public EnergyDataController(EnergyDataService energyDataService) {
        this.energyDataService = energyDataService;
    }

    @GetMapping("/current")
    public CurrentPercentageEntity getCurrentData() {
        return energyDataService.getCurrentData();
    }

    @GetMapping("/hourly")
    public UsageData getUsageForHour(@RequestParam("hour") int hour) {
        return energyDataService.getUsageForHour(hour);
    }

    @GetMapping("/history")
    public UsageData getDataForHour(@RequestParam("hour") int hour) {
        return energyDataService.getUsageForHour(hour);
    }

    @GetMapping("/historical")
    public List<UsageData> getHistoricalData(
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return energyDataService.getHistoricalData(start, end);
    }
}
