package com.guitar_app_one_direction;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class App extends Application {
    private static Stage primaryStage;  // Store the main stage
    private static Scene scene;        // Store the main scene

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;  // Store the stage for global access
        
        // Load the initial screen ("primary.fxml")
        setRoot("primary");  

        // Configure stage
        stage.setWidth(400);
        stage.setHeight(900);
        stage.setResizable(true);
        stage.setTitle("Guitar App");
        stage.show();
    }

    // Method to change screens (replaces setContent)
    public static void setRoot(String fxml) throws IOException {
        Parent root = loadFXML(fxml);
        if (scene == null) {
            scene = new Scene(root);
            primaryStage.setScene(scene);
        } else {
            scene.setRoot(root);
        }
    }

    // Helper to load FXML files
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return loader.load();
    }

    // Get the primary stage (for controllers to use)
    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch();
    }
}