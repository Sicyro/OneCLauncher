module src.launcheronec {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example to javafx.fxml;
    exports org.example;
    exports org.example.controller;
    opens org.example.controller to javafx.fxml;
    exports org.example.model;
    opens org.example.model to javafx.fxml;
}