package at.technikum.api.repository;

import at.technikum.api.model.EnergyData;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Repository
public class FakeEnergyDataRepository {

    private final Map<Integer, EnergyData> fakeData = new HashMap<>();

    public FakeEnergyDataRepository() {

        for (int i = 0; i < 24; i++) {
            double usage = 10 + Math.random() * 5;
            double production = 13 + Math.random() * 4;
            fakeData.put(i, new EnergyData(i, usage, production));
        }
    }

    public EnergyData getByHour(int hour) {
        return fakeData.get(hour);
    }

    public EnergyData getCurrentHourData() {
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        return getByHour(hour);
    }
}
