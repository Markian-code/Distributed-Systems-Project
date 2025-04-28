package at.technikum.usage.controller;

import at.technikum.usage.model.UsageData;
import at.technikum.usage.repository.UsageRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsageController {

    private final UsageRepository usageRepository;

    public UsageController(UsageRepository usageRepository) {
        this.usageRepository = usageRepository;
    }

    @GetMapping("/usages")
    public List<UsageData> getAllUsages() {
        return usageRepository.findAll();
    }
}
