module com.guitar_app_one_direction {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires jfugue;
    requires junit;
    requires javafx.graphics;

    opens com.guitar_app_one_direction to javafx.fxml;
    exports com.guitar_app_one_direction;

    opens com.model to javafx.fxml;
    exports com.model;

    opens com.program to javafx.fxml;
}
