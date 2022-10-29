module com.example.rockpaperscissors {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.media;
    requires java.desktop;

    opens com.example.rockpaperscissors to javafx.fxml;
    exports com.example.rockpaperscissors;
}