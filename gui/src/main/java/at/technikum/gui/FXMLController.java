package at.technikum.gui;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

// Diese Klasse steuert die Benutzerinteraktionen aus der FXML-Datei
public class FXMLController {

    @FXML
    private TextArea outputArea;

    // Ruft aktuelle Energiedaten vom REST API ab
    @FXML
    public void handleFetchCurrent() {
        String endpoint = "http://localhost:8082/energy/current";
        String result = fetchData(endpoint);
        outputArea.setText("Aktuelle Daten:\n" + result);
    }

    // Ruft Energiedaten für Stunde 10 vom REST API ab
    @FXML
    public void handleFetchHour() {
        String endpoint = "http://localhost:8082/energy/history?hour=10";
        String result = fetchData(endpoint);
        outputArea.setText("Daten für Stunde 10:\n" + result);
    }

    // Führt HTTP GET-Anfrage durch
    private String fetchData(String endpointUrl) {
        try {
            URL url = new URL(endpointUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            Scanner scanner = new Scanner(conn.getInputStream());
            StringBuilder response = new StringBuilder();

            while (scanner.hasNext()) {
                response.append(scanner.nextLine()).append("\n");
            }

            scanner.close();
            return response.toString();
        } catch (IOException e) {
            return "Fehler beim Abrufen der Daten: " + e.getMessage();
        }
    }
}
