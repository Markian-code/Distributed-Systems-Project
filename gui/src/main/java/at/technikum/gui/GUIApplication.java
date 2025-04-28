package at.technikum.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// Hauptklasse der GUI-Anwendung
public class GUIApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Lädt die FXML-Datei aus dem Ressourcenverzeichnis
        Parent root = FXMLLoader.load(GUIApplication.class.getResource("/fxml/main-view.fxml"));

        // Titel des Fensters setzen
        stage.setTitle("Energy Dashboard");

        // Szene erstellen und anzeigen
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    // Einstiegspunkt der JavaFX-Anwendung
    public static void main(String[] args) {
        launch(args);
    }
}
