/**module information for the projects functionality*/
module com.example.d {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.d to javafx.fxml;
    exports com.example.d;
    exports com.example.d.model;
    opens com.example.d.model to javafx.fxml;
}