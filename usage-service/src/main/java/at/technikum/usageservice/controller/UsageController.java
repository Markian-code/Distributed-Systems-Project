package at.technikum.usageservice.controller;

import at.technikum.usageservice.model.UsageData;
import at.technikum.usageservice.service.UsageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usage")
public class UsageController {

    @Autowired
    private UsageService usageService;

    @GetMapping
    public List<UsageData> getAll() {
        return usageService.getAllUsageData();
    }

    @PostMapping
    public UsageData create(@RequestBody UsageData usageData) {
        return usageService.saveUsageData(usageData);
    }

    @DeleteMapping
    public void deleteAll() {
        usageService.deleteAllUsageData();
    }
}
