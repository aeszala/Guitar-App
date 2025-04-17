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

public class CompletedController implements Initializable {

    @FXML
    private VBox vboxCompleted;

    private MusicAppFACADE facade;
    private User currentUser;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        facade = new MusicAppFACADE();
    }

    public void setUser(User user) {
        this.currentUser = user;
        loadCompletedSongs();
    }

    private void loadCompletedSongs() {
        if (currentUser == null) return;

        List<Song> completedSongs = facade.getCompletedSongs(currentUser);
        vboxCompleted.getChildren().clear();

        for (Song song : completedSongs) {
            Label label = new Label(song.getTitle());
            vboxCompleted.getChildren().add(label);
        }
    }
}
