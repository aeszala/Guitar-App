package com.guitar_app_one_direction;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.model.MusicAppFACADE;
import com.model.Song;
import com.model.User;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ProfileController implements Initializable {

    @FXML
    private VBox vboxFavorites;

    @FXML
    private VBox vboxCompleted;

    @FXML
    private VBox vboxMySongs;

    @FXML
    private Button favoriteSongsBtn;

    @FXML
    private Button completedSongsBtn;

    @FXML
    private Button mySongsBtn;

    private MusicAppFACADE facade;
    private User currentUser;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        facade = new MusicAppFACADE();
        loadSongs();
    }

    @FXML
    private void goToMySongs() throws IOException {
        App.setRoot("mySongs");
    }

    @FXML
    private void goToSetSecurityQuestion() throws IOException {
        App.setRoot("setSecurity");
    }

    @FXML
    private void goToFavoriteSongs() throws IOException {
        App.setRoot("favorites");
    }

    @FXML
    private void goToCompletedSongs() throws IOException {
        App.setRoot("completed");
    }

    @FXML
    private void goToPrimary() throws IOException {
        App.setRoot("primary");
    }

    private void loadSongs() {
        if (currentUser == null) {
            return;
        }

        List<Song> favorites = facade.getFavoriteSongs(currentUser);
        for (Song song : favorites) {
            Label songLabel = new Label(song.getTitle());
            vboxFavorites.getChildren().add(songLabel);
        }

        List<Song> completed = facade.getCompletedSongs(currentUser);
        for (Song song : completed) {
            Label songLabel = new Label(song.getTitle());
            vboxCompleted.getChildren().add(songLabel);
        }

        List<Song> mySongs = facade.getMySongs(currentUser);
        for (Song song : mySongs) {
            Label songLabel = new Label(song.getTitle());
            vboxMySongs.getChildren().add(songLabel);
        }
    }

    public void setUser(User user) {
        this.currentUser = user;
    }
}
