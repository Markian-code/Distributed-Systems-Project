package at.technikum.gui;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class FXMLController {

    @FXML
    private TextArea outputArea;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private Button btnLoadData;

    @FXML
    private TableView<UsageData> historyTable;

    @FXML
    private TableColumn<UsageData, String> colHour;

    @FXML
    private TableColumn<UsageData, Double> colCommunity;

    @FXML
    private TableColumn<UsageData, Double> colGrid;

    @FXML
    private TableColumn<UsageData, Double> colPortion;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @FXML
    public void initialize() {
        // Bind Table Columns to Data Fields
        colHour.setCellValueFactory(cellData -> cellData.getValue().hourProperty());
        colCommunity.setCellValueFactory(cellData -> cellData.getValue().communityUsageProperty().asObject());
        colGrid.setCellValueFactory(cellData -> cellData.getValue().gridUsageProperty().asObject());
        colPortion.setCellValueFactory(cellData -> cellData.getValue().gridPortionProperty().asObject());

        // Button Action for "Load History"
        btnLoadData.setOnAction(event -> {
            LocalDate start = startDatePicker.getValue();
            LocalDate end = endDatePicker.getValue();

            // Validate Dates
            if (start == null || end == null) {
                showError("Please select both a start and end date.");
                return;
            }
            if (start.isAfter(end)) {
                showError("Start date must be before the end date.");
                return;
            }

            // Build the endpoint URL
            String endpoint = String.format(
                    "http://localhost:8082/energy/historical?start=%s&end=%s",
                    start, end
            );

            // Fetch the data
            String json = fetchData(endpoint);
            if (json == null) {
                showError("Error fetching data from the server.");
                return;
            }

            // Show the raw JSON in the output area
            outputArea.setText("📊 Historical Data (JSON):\n" + json);

            // Deserialize JSON into List<UsageData>
            try {
                List<UsageData> dataList = objectMapper.readValue(json, new TypeReference<List<UsageData>>() {});
                historyTable.setItems(FXCollections.observableArrayList(dataList));
            } catch (IOException e) {
                showError("Error processing the data: " + e.getMessage());
            }
        });
    }

    // Handle fetching current data
    @FXML
    public void handleFetchCurrent() {
        String endpoint = "http://localhost:8082/energy/current";
        String result = fetchData(endpoint);
        if (result == null) {
            showError("Error fetching current data.");
            return;
        }
        outputArea.setText("⚡ Current Data:\n" + result);
    }

    // Handle fetching data for a specific hour
    @FXML
    public void handleFetchHour() {
        String endpoint = "http://localhost:8082/energy/history?hour=10";
        String result = fetchData(endpoint);
        if (result == null) {
            showError("Error fetching data for hour.");
            return;
        }
        outputArea.setText("⏱️ Data for Hour 10:\n" + result);
    }

    // Fetch data from the API
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
            return null;
        }
    }

    // Show error message in the output area (UI)
    private void showError(String message) {
        outputArea.setText("❌ " + message);
        outputArea.setStyle("-fx-text-fill: red;");
    }
}
