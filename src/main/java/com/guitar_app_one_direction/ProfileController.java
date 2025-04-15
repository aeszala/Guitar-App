package com.guitar_app_one_direction;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.model.MusicAppFACADE;
import com.model.Song;
import com.model.User;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ProfileController implements Initializable {

    @FXML
    private VBox vboxFavorites;

    @FXML
    private VBox vboxCompleted;

    @FXML
    private VBox vboxMySongs;

    private MusicAppFACADE facade;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        facade = new MusicAppFACADE();

        // Load favorite songs
        List<Song> favorites = facade.getFavoriteSongs(null);
        for (Song song : favorites) {
            Label songLabel = new Label(song.getTitle());
            vboxFavorites.getChildren().add(songLabel);
        }

        // Load completed songs
        List<Song> completed = facade.getCompletedSongs(null);
        for (Song song : completed) {
            Label songLabel = new Label(song.getTitle());
            vboxCompleted.getChildren().add(songLabel);
        }
        
        // Load my songs
        List<Song> mySongs = facade.getMySongs(null);
        for (Song song : mySongs) {
            Label songLabel = new Label(song.getTitle());
            vboxMySongs.getChildren().add(songLabel);
        }
    }

    public void setUser(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setUser'");
    }
}
