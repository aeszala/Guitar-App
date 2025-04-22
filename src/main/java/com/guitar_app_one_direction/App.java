package com.guitar_app_one_direction;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class App extends Application {
    private static Scene scene;
    private static PrimaryController primaryController;

    @Override
    public void start(Stage stage) throws IOException {
        // Load the primary screen
        FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
        Parent root = loader.load();
    
        // Store the primary controller reference
        primaryController = loader.getController();
    
        // Set initial content
        primaryController.setContent("primary");
    
        // Create scene
        scene = new Scene(root, 800, 600);
        stage.setWidth(400);
        stage.setHeight(900);
        stage.setResizable(true);  // Prevent resizing
        stage.setScene(scene);
        stage.setTitle("Guitar App");
        stage.show();
    }

    public static void setContent(String fxml) throws IOException {
        if (primaryController != null) {
            primaryController.setContent(fxml);
        }
    }
    
    public static Scene getScene() {
        return scene;
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}