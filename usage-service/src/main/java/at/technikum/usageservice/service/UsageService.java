package at.technikum.usageservice.service;

import at.technikum.usageservice.model.UsageData;
import at.technikum.usageservice.repository.UsageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsageService {

    @Autowired
    private UsageRepository usageRepository;

    public List<UsageData> getAllUsageData() {
        return usageRepository.findAll();
    }

    public UsageData saveUsageData(UsageData usageData) {
        return usageRepository.save(usageData);
    }

    public void deleteAllUsageData() {
        usageRepository.deleteAll();
    }
}
