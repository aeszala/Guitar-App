package com.guitar_app_one_direction;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.model.MusicAppFACADE;
import com.model.Song;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class HomeController implements Initializable {

    @FXML
    private Label lblWelcome;

    @FXML
    private VBox vboxFavorites;

    @FXML
    private VBox vboxCompleted;

    private MusicAppFACADE facade;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        facade = new MusicAppFACADE();

        // Get current logged-in user
        String username = facade.getLoggedInUsername();
        lblWelcome.setText("Welcome, " + username + "!");

        // Load favorite songs
        List<Song> favorites = facade.getFavoriteSongs(username);
        for (Song song : favorites) {
            Label songLabel = new Label(song.getTitle());
            vboxFavorites.getChildren().add(songLabel);
        }

        // Load completed songs
        List<Song> completed = facade.getCompletedSongs(username);
        for (Song song : completed) {
            Label songLabel = new Label(song.getTitle());
            vboxCompleted.getChildren().add(songLabel);
        }
    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
