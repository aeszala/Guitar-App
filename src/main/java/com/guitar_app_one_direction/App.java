package com.guitar_app_one_direction;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class App extends Application {
    private static Scene scene;
    private static TemplateController templateController;

    @Override
    public void start(Stage stage) throws IOException {
        // Load the template (which contains the nav bar and content container)
        FXMLLoader loader = new FXMLLoader(getClass().getResource("template.fxml"));
        Parent root = loader.load();
        
        // Store the template controller reference
        templateController = loader.getController();
        
        // Set initial content
        templateController.setContent("primary");
        
        // Create scene
        scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Guitar App");
        stage.show();
    }

    public static void setContent(String fxml) throws IOException {
        if (templateController != null) {
            templateController.setContent(fxml);
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