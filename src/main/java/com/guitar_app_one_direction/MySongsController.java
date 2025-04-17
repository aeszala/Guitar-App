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

public class MySongsController implements Initializable {

    @FXML
    private VBox vboxMySongs;

    private MusicAppFACADE facade;
    private User currentUser;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        facade = new MusicAppFACADE();
    }

    public void setUser(User user) {
        this.currentUser = user;
        loadMySongs();
    }

    private void loadMySongs() {
        if (currentUser == null) return;

        List<Song> mySongs = facade.getMySongs(currentUser);
        vboxMySongs.getChildren().clear();

        for (Song song : mySongs) {
            Label label = new Label(song.getTitle());
            vboxMySongs.getChildren().add(label);
        }
    }
}
