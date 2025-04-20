package at.technikum.api.repository;

import at.technikum.api.model.EnergyData;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class FakeEnergyDataRepository {

    private static final Map<Integer, EnergyData> fakeData = new HashMap<>();

    static {
        fakeData.put(9, new EnergyData(9, 12.5, 15.0));
        fakeData.put(10, new EnergyData(10, 10.3, 14.2));
        fakeData.put(11, new EnergyData(11, 13.1, 12.8));
    }

    public static EnergyData getByHour(int hour) {
        if (!fakeData.containsKey(hour)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No data found for hour " + hour
            );
        }
        return fakeData.get(hour);
    }

    public static EnergyData getCurrentHourData() {
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        return getByHour(hour);
    }
}
