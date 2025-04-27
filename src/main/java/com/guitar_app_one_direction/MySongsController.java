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
import javafx.scene.control.ListView;

public class MySongsController implements Initializable {

    @FXML
    private ListView<String> mySongsListView;  // ListView to display the songs

    private MusicAppFACADE facade;
    private User currentUser;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        facade = new MusicAppFACADE();  // Initialize the facade to interact with music data
    }

    public void setUser(User user) {
        this.currentUser = user;  // Set the current user
        loadMySongs();  // Load the user's songs
    }

    private void loadMySongs() {
        if (currentUser == null) return;  // If no user is set, do nothing

        // Fetch the list of songs for the current user
        List<Song> mySongs = facade.getMySongs(currentUser);

        // Clear the existing items in the ListView
        mySongsListView.getItems().clear();

        // Add each song's title to the ListView
        for (Song song : mySongs) {
            mySongsListView.getItems().add(song.getTitle());  // Add song title to the ListView
        }
    }

    @FXML
    private void handleBack() throws IOException {
      App.setRoot("profile");
    }

    @FXML
    private void handleHome() throws IOException {
      App.setRoot("home");
    }
}
