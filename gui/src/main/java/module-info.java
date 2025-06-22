module at.technikum.gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;

    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;

    opens at.technikum.gui to javafx.fxml;
    exports at.technikum.gui;
}
