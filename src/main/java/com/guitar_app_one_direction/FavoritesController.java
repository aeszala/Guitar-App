package com.guitar_app_one_direction;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.model.MusicAppFACADE;
import com.model.Song;
import com.model.User;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class FavoritesController implements Initializable {

    @FXML
    private VBox vboxFavorites;

    private MusicAppFACADE facade;
    private User currentUser;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        facade = new MusicAppFACADE();
    }

    public void setUser(User user) {
        this.currentUser = user;
        loadFavoriteSongs();
    }

    private void loadFavoriteSongs() {
        if (currentUser == null) return;

        List<Song> favoriteSongs = facade.getFavoriteSongs(currentUser);
        vboxFavorites.getChildren().clear();

        for (Song song : favoriteSongs) {
            Label label = new Label(song.getTitle());
            vboxFavorites.getChildren().add(label);
        }
    }
}
