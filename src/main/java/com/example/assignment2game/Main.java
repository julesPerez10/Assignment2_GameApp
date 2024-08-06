package com.example.assignment2game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file for the main view
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/assignment2game/hello-view.fxml"));
        Scene scene = new Scene(loader.load());

        // Add the stylesheet to the scene
        scene.getStylesheets().add(getClass().getResource("/com/example/assignment2game/styles.css").toExternalForm());

        // Set the title of the primary stage (main window)
        primaryStage.setTitle("Game Viewer");

        // Set the scene for the primary stage
        primaryStage.setScene(scene);

        // Set the minimum width and height for the window
        primaryStage.setMinWidth(800); // Minimum width
        primaryStage.setMinHeight(600); // Minimum height

        // Allow the window to be resized
        primaryStage.setResizable(true); // Allow resizing

        // Open the window in full screen mode
        primaryStage.setMaximized(true); // Open in full screen

        // Show the primary stage
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
