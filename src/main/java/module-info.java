module com.example.assignment2game {
    requires javafx.controls;
    requires javafx.fxml;
    requires okhttp3;
    requires com.google.gson;

    opens com.example.assignment2game to javafx.fxml;
    opens com.example.assignment2game.model to com.google.gson;

    exports com.example.assignment2game;
    exports com.example.assignment2game.model;
}
