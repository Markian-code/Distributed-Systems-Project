package at.technikum.producer.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    private final String API_KEY = "93e30e5f4bedbbb61320a0e587a158a8";  // << HIER DEIN API KEY EINFÜGEN
    private final String CITY = "Vienna";                // oder deine Stadt

    public double getCurrentCloudiness() {
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + CITY + "&appid=" + API_KEY + "&units=metric";
        RestTemplate restTemplate = new RestTemplate();
        try {
            String json = restTemplate.getForObject(url, String.class);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(json);
            JsonNode clouds = root.path("clouds").path("all");  // % Bewölkung
            return clouds.asDouble();  // 0..100 %
        } catch (Exception e) {
            System.out.println("⚠️ Fehler beim Abruf der Weather API: " + e.getMessage());
            return 50.0;  // fallback 50% falls Fehler
        }
    }
}
