module com.example.wesfritzsample {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.base;


    opens com.example.wesfritzsample to javafx.fxml, javafx.base;
    exports com.example.wesfritzsample;

    opens com.example.wesfritzsample.controllers to javafx.fxml, javafx.base;
    exports com.example.wesfritzsample.controllers;

    opens com.example.wesfritzsample.classes to javafx.fxml, javafx.base, javafx.controls;
    exports com.example.wesfritzsample.classes;

    opens com.example.wesfritzsample.SQLqueries to javafx.fxml, javafx.base;
    exports com.example.wesfritzsample.SQLqueries;

    opens com.example.wesfritzsample.JDBC to javafx.fxml, javafx.base;
    exports com.example.wesfritzsample.JDBC;
}