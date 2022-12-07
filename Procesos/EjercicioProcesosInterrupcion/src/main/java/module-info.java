module com.example.ejercicio1procesos {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens com.example.ejercicio1procesos to javafx.fxml;
    exports com.example.ejercicio1procesos;
}