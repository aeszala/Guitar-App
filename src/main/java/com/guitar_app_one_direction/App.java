package com.guitar_app_one_direction;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Popup;
import javafx.stage.Stage;
import java.io.IOException;

import com.model.User;
import com.model.Song;

public class App extends Application {
    private static Stage primaryStage;  // Store the main stage
    private static Scene scene;        // Store the main scene
    private static String currentUsername;
    private static User currentUser;
    private static String currentSongName;
    private static Song currentSong;
    private static Popup currentPopup;

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static void setCurrentUsername(String username) {
        currentUsername = username;
    }

    public static String getCurrentUsername() {
        return currentUsername;
    }

    public static Song getCurrentSong() {
        return currentSong;
    }

    public static void setCurrentSong(Song song) {
        currentSong = song;
    }

    public static void setCurrentSongName(String songName) {
        currentSongName = songName;
    }

    public static String getCurrentSongName() {
        return currentSongName;
    }


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

    public static void setRoot(Parent root) {
        scene.setRoot(root);
    }

    // Method to show a popup overlay
    public static Popup showPopup(String fxml) throws IOException {
        Parent root = loadFXML(fxml);
        
        currentPopup = new Popup();
        currentPopup.getContent().add(root);
        currentPopup.setAutoHide(true); // Closes when clicking outside
        currentPopup.show(primaryStage);
        
        return currentPopup;
    }

    public static void closeCurrentPopup() {
        if (currentPopup != null) {
            currentPopup.hide();
            currentPopup = null;
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