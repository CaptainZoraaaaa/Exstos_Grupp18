module com.example.exstos_grupp18 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires eu.hansolo.tilesfx;

    opens com.example.exstos_grupp18 to javafx.fxml;
    exports com.example.exstos_grupp18;
    exports Controller;
    opens Controller to javafx.fxml;
}